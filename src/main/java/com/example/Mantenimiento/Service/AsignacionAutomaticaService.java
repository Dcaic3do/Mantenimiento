package com.example.Mantenimiento.Service;

import com.example.Mantenimiento.Model.*;
import com.example.Mantenimiento.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.*;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class AsignacionAutomaticaService {

    @Autowired private TecnicoRepository tecnicoRepository;
    @Autowired private SolicitudInsRepository solicitudInsRepository;
    @Autowired private AsignacionRepository asignacionRepository;
    @Autowired private TiempoTrasladoRepository tiempoTrasladoRepository;
    @Autowired private TurnoRepository turnoRepository;
    @Autowired private ZonaRepository zonaRepository;

    private static final int BLOQUEO_INICIO_FIN_MIN = 60;
    private static final float MAX_HORAS_EFECTIVAS = 9.0f;
    private static final int VENTANA_TOLERANCIA_MIN = 120;

    private LocalDateTime ahora() {
        return LocalDateTime.now(ZoneId.of("America/Bogota"));
    }

    @Transactional
    public List<Asignacion> procesarPlanSemanal() {

        LocalDateTime ahora = ahora();
        LocalDate fechaActual = ahora.toLocalDate();

        List<Asignacion> asignacionesDelProceso = new ArrayList<>();

        // 🔥 Filtrar solicitudes en pasado
        List<SolicitudInspeccion> solicitudes = solicitudInsRepository.findAllParaProcesar()
                .stream()
                .filter(s -> !s.getFechaSolicitada().toLocalDate().isBefore(fechaActual))
                .collect(Collectors.toList());

        for (SolicitudInspeccion solicitud : solicitudes) {

            List<Tecnico> candidatos = obtenerCandidatosPorTurnoFecha(solicitud);

            boolean requierePareja = verificarSiRequierePareja(solicitud);
            boolean exito = false;

            if (!candidatos.isEmpty()) {
                exito = requierePareja ?
                        intentarAsignacionPareja(solicitud, candidatos, asignacionesDelProceso) :
                        intentarAsignacionIndividual(solicitud, candidatos, asignacionesDelProceso);
            }

            solicitud.setEstado(exito ? "Programada" : "Pendiente");
            solicitudInsRepository.saveAndFlush(solicitud);
        }

        return asignacionesDelProceso;
    }

    private List<Tecnico> obtenerCandidatosPorTurnoFecha(SolicitudInspeccion s) {
        LocalDate fechaSolicitada = s.getFechaSolicitada().toLocalDate();

        return tecnicoRepository.findByActivoTrue().stream()
                .filter(t -> t.getTecnicoTecnicas().stream()
                        .anyMatch(tt -> tt.getTecnica().getId_tecnica() == s.getTecnica().getId_tecnica()))
                .filter(t -> t.getGrupo() != null)
                // Validación de calendario: El técnico debe tener un Turno creado para ese día exacto
                .filter(t -> t.getGrupo().getTurnos().stream()
                        .anyMatch(turno -> {
                            LocalDate fechaTurno = turno.getFecha().toInstant()
                                    .atZone(ZoneId.systemDefault()).toLocalDate();
                            return fechaTurno.equals(fechaSolicitada);
                        }))
                .sorted(Comparator.comparingDouble(t -> calcularCargaHorariaTotal(t, new ArrayList<>())))
                .collect(Collectors.toList());
    }

    private LocalTime encontrarSiguienteSlotDisponible(Tecnico t, SolicitudInspeccion s, List<Asignacion> recientes) {

        LocalDateTime ahora = ahora();
        LocalDate fechaActual = ahora.toLocalDate();
        LocalTime horaActual = ahora.toLocalTime();

        LocalDate fechaSolicitud = s.getFechaSolicitada().toLocalDate();
        LocalTime horaSolicitada = s.getFechaSolicitada().toLocalTime();

        // 🔥 Ajuste si es hoy
        if (fechaSolicitud.isEqual(fechaActual)) {
            if (horaSolicitada.isBefore(horaActual)) {
                horaSolicitada = horaActual;
            }
        }

        Turno turnoDelDia = t.getGrupo().getTurnos().stream()
                .filter(turno -> {
                    LocalDate f = turno.getFecha().toInstant()
                            .atZone(ZoneId.systemDefault()).toLocalDate();
                    return f.equals(fechaSolicitud);
                })
                .findFirst().orElse(null);

        if (turnoDelDia == null) return null;

        TipoTurno tipoTurno = turnoDelDia.getTipo_turno();

        LocalTime inicioTurno = tipoTurno.getHoraInicio().plusMinutes(BLOQUEO_INICIO_FIN_MIN);
        LocalTime finTurno = tipoTurno.getHoraFin().minusMinutes(BLOQUEO_INICIO_FIN_MIN);

        List<Asignacion> agendaCompleta = obtenerAgendaConsolidada(t, recientes);

        LocalTime horaCandidata = Collections.max(
                Arrays.asList(horaSolicitada, inicioTurno)
        );

        LocalTime limiteTolerancia = horaCandidata.plusMinutes(VENTANA_TOLERANCIA_MIN);

        while (horaCandidata.isBefore(finTurno) && !horaCandidata.isAfter(limiteTolerancia)) {

            int traslado = calcularTrasladoDinamico(t, s.getZona(), agendaCompleta);

            LocalTime inicioPropuesto = horaCandidata.plusMinutes(traslado);
            LocalTime finPropuesto = inicioPropuesto.plusMinutes((long)(s.getDuracionEstimadaHrs() * 60));

            // 🔥 Evitar pasado
            if (fechaSolicitud.isEqual(fechaActual) && inicioPropuesto.isBefore(horaActual)) {
                horaCandidata = horaActual.plusMinutes(15);
                continue;
            }

            if (finPropuesto.isAfter(finTurno)) break;

            boolean solapado = agendaCompleta.stream().anyMatch(a ->
                    haySolapamiento(inicioPropuesto, finPropuesto,
                            a.getHora_inicio_programada(), a.getHora_fin_programada())
            );

            if (!solapado && !cruzaAlmuerzo(inicioPropuesto, finPropuesto, tipoTurno)) {
                return inicioPropuesto;
            }

            horaCandidata = horaCandidata.plusMinutes(15);
        }

        return null;
    }

    private Asignacion registrarAsignacion(SolicitudInspeccion s, Tecnico l, Tecnico a, LocalTime inicio, List<Asignacion> recientes) {

        Asignacion asig = new Asignacion();

        asig.setSolicitud(s);
        asig.setTecnico_lider(l);
        asig.setTecnico_acompanante(a);

        asig.setFechaProgramada(s.getFechaSolicitada().toLocalDate());
        asig.setHora_inicio_programada(inicio);
        asig.setHora_fin_programada(inicio.plusMinutes((long)(s.getDuracionEstimadaHrs() * 60)));

        asig.setEstado("Programada");

        asig.setTiempoTrasladoMin(
                calcularTrasladoDinamico(l, s.getZona(), obtenerAgendaConsolidada(l, recientes))
        );

        LocalDate fechaBusqueda = s.getFechaSolicitada().toLocalDate();

        Turno turnoCorrecto = l.getGrupo().getTurnos().stream()
                .filter(turno -> {
                    LocalDate f = turno.getFecha().toInstant()
                            .atZone(ZoneId.systemDefault()).toLocalDate();
                    return f.equals(fechaBusqueda);
                })
                .findFirst().get();

        asig.setTurno(turnoCorrecto);

        return asignacionRepository.saveAndFlush(asig);
    }

    private boolean verificarSiRequierePareja(SolicitudInspeccion s) {
        return !s.getZona().getNombre().toUpperCase().contains("TALLER");
    }

    private List<Asignacion> obtenerAgendaConsolidada(Tecnico t, List<Asignacion> recientes) {

        List<Asignacion> agenda = new ArrayList<>(t.getTodasMisAsignaciones());

        agenda.addAll(recientes.stream()
                .filter(a ->
                        (a.getTecnico_lider() != null && a.getTecnico_lider().getId_tecnico() == t.getId_tecnico()) ||
                                (a.getTecnico_acompanante() != null && a.getTecnico_acompanante().getId_tecnico() == t.getId_tecnico())
                )
                .collect(Collectors.toList()));

        return agenda;
    }

    private boolean haySolapamiento(LocalTime ini1, LocalTime fin1, LocalTime ini2, LocalTime fin2) {
        return ini1.isBefore(fin2) && fin1.isAfter(ini2);
    }

    private int calcularTrasladoDinamico(Tecnico t, Zona destino, List<Asignacion> agenda) {

        Zona zonaActual = agenda.stream()
                .sorted(Comparator.comparing(Asignacion::getHora_fin_programada).reversed())
                .map(a -> a.getSolicitud().getZona())
                .findFirst()
                .orElse(null);

        if (zonaActual == null) {
            zonaActual = zonaRepository.findAll().stream()
                    .filter(z -> z.getNombre().toUpperCase().contains("TALLER"))
                    .findFirst().orElse(destino);
        }

        if (zonaActual.getId_zona() == destino.getId_zona()) return 0;

        return tiempoTrasladoRepository
                .findByZonaOrigenAndZonaDestino(zonaActual, destino)
                .map(TiempoTraslado::getMinutos)
                .orElse(20);
    }

    private boolean intentarAsignacionIndividual(SolicitudInspeccion s, List<Tecnico> candidatos, List<Asignacion> recientes) {

        for (Tecnico t : candidatos) {

            if (tieneCapacidad(t, s.getDuracionEstimadaHrs(), recientes)) {

                LocalTime inicio = encontrarSiguienteSlotDisponible(t, s, recientes);

                if (inicio != null) {
                    recientes.add(registrarAsignacion(s, t, null, inicio, recientes));
                    return true;
                }
            }
        }

        return false;
    }

    private boolean intentarAsignacionPareja(SolicitudInspeccion s, List<Tecnico> candidatos, List<Asignacion> recientes) {

        Map<Long, List<Tecnico>> grupos = candidatos.stream()
                .filter(t -> t.getGrupo() != null)
                .collect(Collectors.groupingBy(t -> t.getGrupo().getId_grupo()));

        for (List<Tecnico> cuadrilla : grupos.values()) {

            if (cuadrilla.size() < 2) continue;

            Tecnico t1 = cuadrilla.get(0);
            Tecnico t2 = cuadrilla.get(1);

            if (tieneCapacidad(t1, s.getDuracionEstimadaHrs(), recientes) &&
                    tieneCapacidad(t2, s.getDuracionEstimadaHrs(), recientes)) {

                LocalTime h1 = encontrarSiguienteSlotDisponible(t1, s, recientes);
                LocalTime h2 = encontrarSiguienteSlotDisponible(t2, s, recientes);

                if (h1 != null && h2 != null) {

                    LocalTime inicio = h1.isAfter(h2) ? h1 : h2;

                    recientes.add(registrarAsignacion(s, t1, t2, inicio, recientes));
                    return true;
                }
            }
        }

        return false;
    }

    private double calcularCargaHorariaTotal(Tecnico t, List<Asignacion> recientes) {
        return obtenerAgendaConsolidada(t, recientes)
                .stream()
                .mapToDouble(a -> a.getSolicitud().getDuracionEstimadaHrs())
                .sum();
    }

    private boolean tieneCapacidad(Tecnico t, float duracion, List<Asignacion> recientes) {
        return (calcularCargaHorariaTotal(t, recientes) + duracion) <= MAX_HORAS_EFECTIVAS;
    }

    private boolean cruzaAlmuerzo(LocalTime inicio, LocalTime fin, TipoTurno turno) {

        LocalTime almIni = turno.getHoraInicio().plusHours(5);
        LocalTime almFin = almIni.plusHours(1);

        return inicio.isBefore(almFin) && fin.isAfter(almIni);
    }
}

