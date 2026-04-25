package com.example.Mantenimiento.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TecnicoDTO {
    private Long idTecnico;
    private String nombre;

    private Long idGrupo;

    private boolean activo;
}
