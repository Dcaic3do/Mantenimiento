package com.example.Mantenimiento.Mapper;

import com.example.Mantenimiento.DTO.AsignacionDTO;
import com.example.Mantenimiento.DTO.SolicitudInspeccionDTO;
import com.example.Mantenimiento.Model.Asignacion;
import com.example.Mantenimiento.Model.SolicitudInspeccion;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class SolicitudInspeccionMapper {
    public static SolicitudInspeccionDTO toDTO(SolicitudInspeccion s) {

        if (s == null) return null;

        SolicitudInspeccionDTO dto = new SolicitudInspeccionDTO();

        dto.setIdSolicitudInspeccion(s.getId_solicitudInspeccion());
        dto.setOt(s.getOt());

        // Relaciones como IDs (esto evita JSON gigante)
        dto.setIdEquipo(
                s.getEquipo() != null ? s.getEquipo().getId_equipo() : null
        );

        dto.setIdFlota(
                s.getFlota() != null ? s.getFlota().getId_flota() : null
        );

        dto.setIdTarea(
                s.getTarea() != null ? s.getTarea().getId_tarea() : null
        );

        dto.setIdTecnica(
                s.getTecnica() != null ? s.getTecnica().getId_tecnica() : null
        );

        dto.setIdZona(
                s.getZona() != null ? s.getZona().getId_zona() : null
        );

        // Datos simples
        dto.setDuracionEstimadaHrs(s.getDuracionEstimadaHrs());

        dto.setFechaSolicitada(s.getFechaSolicitada());

        dto.setEstado(s.getEstado());

        return dto;
    }

}
