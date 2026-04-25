package com.example.Mantenimiento.Mapper;

import com.example.Mantenimiento.DTO.AsignacionDTO;
import com.example.Mantenimiento.Model.Asignacion;

public class AsignacionMapper {

    public static AsignacionDTO toDTO(Asignacion a) {

        AsignacionDTO dto = new AsignacionDTO();

        dto.setIdAsignacion(a.getId_asignacion());

        dto.setIdSolicitud(a.getSolicitud().getId_solicitudInspeccion());

        dto.setIdTecnicoLider(a.getTecnico_lider().getId_tecnico());

        dto.setIdTecnicoAcompanante(
                a.getTecnico_acompanante() != null
                        ? a.getTecnico_acompanante().getId_tecnico()
                        : null
        );

        dto.setHoraInicioProgramada(a.getHora_inicio_programada());
        dto.setHoraFinProgramada(a.getHora_fin_programada());
        dto.setTiempoTrasladoMin(a.getTiempoTrasladoMin());
        dto.setEstado(a.getEstado());
        dto.setFechaProgramada(a.getFechaProgramada());

        return dto;
    }
}
