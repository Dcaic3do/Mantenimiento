package com.example.Mantenimiento.DTO;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
public class AsignacionDTO {
    private Long idAsignacion;

    private Long idSolicitud;
    private Long idTecnicoLider;
    private Long idTecnicoAcompanante;

    private LocalTime horaInicioProgramada;
    private LocalTime horaFinProgramada;

    private float tiempoTrasladoMin;
    private String estado;
    private LocalDate fechaProgramada;
}
