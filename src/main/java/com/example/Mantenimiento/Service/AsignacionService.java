package com.example.Mantenimiento.Service;

import com.example.Mantenimiento.Model.Asignacion;
import com.example.Mantenimiento.Repository.AsignacionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public List<Asignacion> listar(){
        try {
            return asignacionRepository.findAll();
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

    public Optional<Asignacion> listarPorId(long id_asigancion) {
        try {
            Optional<Asignacion> asignacion = asignacionRepository.findById(id_asigancion);
            if (asignacion.isEmpty()) {
                throw new IllegalArgumentException("Asignación con ID " + id_asigancion + " no encontrada.");
            }
            return asignacion;
        } catch (Exception e) {
            throw new RuntimeException("Error al buscar la asignación con ID " + id_asigancion + e.getMessage(), e);
        }
    }
    public Asignacion actualizar(Long id_asignacion, Asignacion asignacion) {
        if (!asignacionRepository.existsById(id_asignacion)) {
            throw new IllegalArgumentException("No se encontró una asignación con el ID " + id_asignacion);
        }
        return asignacionRepository.save(asignacion);
    }
}
