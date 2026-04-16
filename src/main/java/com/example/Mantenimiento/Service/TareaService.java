package com.example.Mantenimiento.Service;

import com.example.Mantenimiento.Model.Flota;
import com.example.Mantenimiento.Model.Tarea;
import com.example.Mantenimiento.Repository.TareaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TareaService {
    private final TareaRepository tareaRepository;

    public TareaService(TareaRepository tareaRepository) {
        this.tareaRepository = tareaRepository;
    }

    public Tarea guardar(Tarea tarea) {
        try {
            return tareaRepository.save(tarea);
        } catch (Exception e) {
            throw new RuntimeException("Error al guardar la tarea " + e.getMessage(), e);
        }
    }

    public List<Tarea> listar(){
        try {
            return tareaRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Error al listar las tareas " + e.getMessage(), e);
        }
    }

    public void eliminar(Long id_tarea) {
        try {
            if (!tareaRepository.existsById(id_tarea)) {
                throw new IllegalArgumentException("No se encontró una tarea con el ID " + id_tarea);
            }
            tareaRepository.deleteById(id_tarea);
        } catch (Exception e) {
            throw new RuntimeException("Error al eliminar la tarea " + e.getMessage(), e);
        }
    }

    public Optional<Tarea> listarPorId(long id_tarea) {
        try {
            Optional<Tarea> tarea = tareaRepository.findById(id_tarea);
            if (tarea.isEmpty()) {
                throw new IllegalArgumentException("Tarea con ID " + id_tarea + " no encontrado.");
            }
            return tarea;
        } catch (Exception e) {
            throw new RuntimeException("Error al buscar la tarea con ID " + id_tarea + e.getMessage(), e);
        }
    }
    public Tarea actualizar(Long id_tarea, Tarea tarea) {
        if (!tareaRepository.existsById(id_tarea)) {
            throw new IllegalArgumentException("No se encontró una tarea con el ID " + id_tarea);
        }
        return tareaRepository.save(tarea);
    }
}
