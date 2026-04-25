package com.example.Mantenimiento.DTO;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Data
public class SolicitudInspeccionDTO {
    private Long idSolicitudInspeccion;
    private String ot;

    private Long idEquipo;
    private Long idFlota;
    private Long idTarea;
    private Long idTecnica;
    private Long idZona;

    private float duracionEstimadaHrs;
    private LocalDateTime fechaSolicitada;
    private String estado;
}
