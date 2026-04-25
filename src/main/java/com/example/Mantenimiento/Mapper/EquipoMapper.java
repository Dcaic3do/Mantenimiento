package com.example.Mantenimiento.Mapper;

import com.example.Mantenimiento.DTO.EquipoDTO;
import com.example.Mantenimiento.Model.Equipo;
import com.example.Mantenimiento.Model.Flota;

public class EquipoMapper {
    // 🔹 Entity → DTO
    public static EquipoDTO toDTO(Equipo entity) {
        if (entity == null) return null;

        return new EquipoDTO(
                entity.getId_equipo(),
                entity.getFlota() != null ? entity.getFlota().getId_flota() : null,
                entity.getNombre()
        );
    }

    // 🔹 DTO → Entity
    public static Equipo toEntity(EquipoDTO dto, Flota flota) {
        if (dto == null) return null;

        Equipo entity = new Equipo();
        entity.setId_equipo(dto.getIdEquipo());
        entity.setFlota(flota); // ⚠️ se pasa desde el service
        entity.setNombre(dto.getNombre());

        return entity;
    }
}
