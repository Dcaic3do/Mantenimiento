package com.example.Mantenimiento.Service;

import com.example.Mantenimiento.DTO.AsignacionDTO;
import com.example.Mantenimiento.Mapper.AsignacionMapper;
import com.example.Mantenimiento.Model.Asignacion;
import com.example.Mantenimiento.Repository.AsignacionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AsignacionService {
    private final AsignacionRepository asignacionRepository;

    public AsignacionService(AsignacionRepository asignacionRepository) {
        this.asignacionRepository = asignacionRepository;
    }

    public Asignacion guardar(Asignacion asignacion) {
        try {
            return asignacionRepository.save(asignacion);
        } catch (Exception e) {
            throw new RuntimeException("Error al guardar la asiganación " + e.getMessage(), e);
        }
    }

    public List<AsignacionDTO> listar() {
        try {
            return asignacionRepository.findAll()
                    .stream()
                    .map(AsignacionMapper::toDTO)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Error al listar las asignaciones " + e.getMessage(), e);
        }
    }

    public void eliminar(Long id_asignacion) {
        try {
            if (!asignacionRepository.existsById(id_asignacion)) {
                throw new IllegalArgumentException("No se encontró una asignación con el ID " + id_asignacion);
            }
            asignacionRepository.deleteById(id_asignacion);
        } catch (Exception e) {
            throw new RuntimeException("Error al eliminar la asignación " + id_asignacion + e.getMessage(), e);
        }
    }

    public AsignacionDTO listarPorId(long id_asignacion) {
        try {
            Asignacion asignacion = asignacionRepository.findById(id_asignacion)
                    .orElseThrow(() ->
                            new IllegalArgumentException("Asignación con ID " + id_asignacion + " no encontrada.")
                    );

            return AsignacionMapper.toDTO(asignacion);

        } catch (Exception e) {
            throw new RuntimeException("Error al buscar la asignación con ID " + id_asignacion + e.getMessage(), e);
        }
    }
    public Asignacion actualizar(Long id_asignacion, Asignacion asignacion) {

        if (!asignacionRepository.existsById(id_asignacion)) {
            throw new IllegalArgumentException("No se encontró una asignación con el ID " + id_asignacion);
        }

        asignacion.setId_asignacion(id_asignacion);
        return asignacionRepository.save(asignacion);
    }
}
