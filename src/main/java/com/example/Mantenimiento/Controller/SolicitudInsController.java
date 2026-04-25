package com.example.Mantenimiento.Controller;

import com.example.Mantenimiento.DTO.EstadoRequestDTO;
import com.example.Mantenimiento.DTO.SolicitudInspeccionDTO;
import com.example.Mantenimiento.Model.SolicitudInspeccion;
import com.example.Mantenimiento.Repository.*;
import com.example.Mantenimiento.Service.EquipoService;
import com.example.Mantenimiento.Service.SolicitudInspeccionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/solicitudInspeccion")
@CrossOrigin(originPatterns = "*", allowCredentials = "true")
public class SolicitudInsController {
    private final SolicitudInspeccionService solicitudInspeccionService;
    private final SolicitudInsRepository solicitudInsRepository;
    private final EquipoRepository equipoRepository;
    private final FlotaRepository flotaRepository;
    private final TareaRepository tareaRepository;
    private final TecnicaRepository tecnicaRepository;
    private final ZonaRepository zonaRepository;


    public SolicitudInsController(SolicitudInspeccionService solicitudInspeccionService, SolicitudInsRepository solicitudInsRepository, EquipoService equipoService, EquipoRepository equipoRepository, FlotaRepository flotaRepository, TareaRepository tareaRepository, TecnicaRepository tecnicaRepository, ZonaRepository zonaRepository) {
        this.solicitudInspeccionService = solicitudInspeccionService;
        this.solicitudInsRepository = solicitudInsRepository;
        this.equipoRepository = equipoRepository;
        this.flotaRepository = flotaRepository;
        this.tareaRepository = tareaRepository;
        this.tecnicaRepository = tecnicaRepository;
        this.zonaRepository = zonaRepository;
    }

    @PostMapping("/guardar")
    public ResponseEntity<?> guardar(@RequestBody SolicitudInspeccionDTO dto) {

        SolicitudInspeccion s = new SolicitudInspeccion();

        s.setOt(dto.getOt());
        s.setEstado(dto.getEstado());
        s.setDuracionEstimadaHrs(dto.getDuracionEstimadaHrs());
        s.setFechaSolicitada(dto.getFechaSolicitada());

        s.setEquipo(equipoRepository.findById(dto.getIdEquipo()).orElseThrow());
        s.setFlota(flotaRepository.findById(dto.getIdFlota()).orElseThrow());
        s.setTarea(tareaRepository.findById(dto.getIdTarea()).orElseThrow());
        s.setTecnica(tecnicaRepository.findById(dto.getIdTecnica()).orElseThrow());
        s.setZona(zonaRepository.findById(dto.getIdZona()).orElseThrow());

        return ResponseEntity.ok(solicitudInspeccionService.guardar(s));
    }

    @GetMapping("/listar")
    public ResponseEntity<List<SolicitudInspeccionDTO>> listar() {
        return ResponseEntity.ok(solicitudInspeccionService.listar());
    }

    @GetMapping("/listar/{id_solicitudInspeccion}")
    public ResponseEntity<SolicitudInspeccionDTO> obtenerPorId(@PathVariable long id_solicitudInspeccion) {
        return ResponseEntity.ok(solicitudInspeccionService.listarPorId(id_solicitudInspeccion));
    }

    @DeleteMapping("/eliminar/{id_solicitudInspeccion}")
    public ResponseEntity<Void> eliminar(@PathVariable long id_solicitudInspeccion) {
        solicitudInspeccionService.eliminar(id_solicitudInspeccion);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/actualizar/{id_solicitudInspeccion}")
    public ResponseEntity<?> actualizarEstado(
            @PathVariable Long id_solicitudInspeccion,
            @RequestBody EstadoRequestDTO request
    ) {
        solicitudInspeccionService.actualizarEstado(id_solicitudInspeccion, request.getEstado());
        return ResponseEntity.ok().build();
    }
}
