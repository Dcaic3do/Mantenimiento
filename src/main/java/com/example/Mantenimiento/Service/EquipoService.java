package com.example.Mantenimiento.Service;

import com.example.Mantenimiento.DTO.EquipoDTO;
import com.example.Mantenimiento.Mapper.EquipoMapper;
import com.example.Mantenimiento.Model.Equipo;
import com.example.Mantenimiento.Model.Flota;
import com.example.Mantenimiento.Repository.EquipoRepository;
import com.example.Mantenimiento.Repository.FlotaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EquipoService {

    private final EquipoRepository equipoRepository;
    private final FlotaRepository flotaRepository;

    public EquipoService(EquipoRepository equipoRepository, FlotaRepository flotaRepository) {
        this.equipoRepository = equipoRepository;
        this.flotaRepository = flotaRepository;
    }

    // 🔥 GUARDAR CON DTO
    public EquipoDTO guardar(EquipoDTO dto) {
        try {
            Flota flota = flotaRepository.findById(dto.getIdFlota())
                    .orElseThrow(() -> new RuntimeException("Flota no encontrada"));

            Equipo equipo = EquipoMapper.toEntity(dto, flota);

            return EquipoMapper.toDTO(equipoRepository.save(equipo));

        } catch (Exception e) {
            throw new RuntimeException("Error al guardar el equipo " + e.getMessage(), e);
        }
    }

    // 🔥 LISTAR COMO DTO
    public List<EquipoDTO> listar() {
        try {
            return equipoRepository.findAll()
                    .stream()
                    .map(EquipoMapper::toDTO)
                    .toList();

        } catch (Exception e) {
            throw new RuntimeException("Error al listar los equipos " + e.getMessage(), e);
        }
    }

    // 🔥 ELIMINAR
    public void eliminar(Long idEquipo) {
        try {
            if (!equipoRepository.existsById(idEquipo)) {
                throw new IllegalArgumentException("No se encontró un equipo con ID " + idEquipo);
            }
            equipoRepository.deleteById(idEquipo);

        } catch (Exception e) {
            throw new RuntimeException("Error al eliminar el equipo " + e.getMessage(), e);
        }
    }

    // 🔥 LISTAR POR ID
    public EquipoDTO listarPorId(Long idEquipo) {
        try {
            Equipo equipo = equipoRepository.findById(idEquipo)
                    .orElseThrow(() -> new RuntimeException("Equipo no encontrado"));

            return EquipoMapper.toDTO(equipo);

        } catch (Exception e) {
            throw new RuntimeException("Error al buscar el equipo " + e.getMessage(), e);
        }
    }

    // 🔥 ACTUALIZAR
    public EquipoDTO actualizar(Long idEquipo, EquipoDTO dto) {

        if (!equipoRepository.existsById(idEquipo)) {
            throw new IllegalArgumentException("No se encontró un equipo con el ID " + idEquipo);
        }

        Flota flota = flotaRepository.findById(dto.getIdFlota())
                .orElseThrow(() -> new RuntimeException("Flota no encontrada"));

        Equipo equipo = EquipoMapper.toEntity(dto, flota);
        equipo.setId_equipo(idEquipo);

        return EquipoMapper.toDTO(equipoRepository.save(equipo));
    }
}
