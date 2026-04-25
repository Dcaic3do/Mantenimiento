package com.example.Mantenimiento.Mapper;

import com.example.Mantenimiento.DTO.TecnicoDTO;
import com.example.Mantenimiento.Model.Grupo;
import com.example.Mantenimiento.Model.Tecnico;

public class TecnicoMapper {

    // ENTITY → DTO
    public static TecnicoDTO toDTO(Tecnico t) {

        if (t == null) return null;

        TecnicoDTO dto = new TecnicoDTO();

        dto.setIdTecnico(t.getId_tecnico());
        dto.setNombre(t.getNombre());

        dto.setActivo(t.isActivo());

        // 🔥 IMPORTANTE: solo ID del grupo (no objeto completo)
        dto.setIdGrupo(
                t.getGrupo() != null ? t.getGrupo().getId_grupo() : null
        );

        return dto;
    }

    // DTO → ENTITY
    public static Tecnico toEntity(TecnicoDTO dto) {

        if (dto == null) return null;

        Tecnico t = new Tecnico();

        t.setId_tecnico(dto.getIdTecnico());
        t.setNombre(dto.getNombre());
        t.setActivo(dto.isActivo());

        if (dto.getIdGrupo() != null) {
            Grupo grupo = new Grupo();
            grupo.setId_grupo(dto.getIdGrupo());
            t.setGrupo(grupo);
        }

        return t;
    }
}

