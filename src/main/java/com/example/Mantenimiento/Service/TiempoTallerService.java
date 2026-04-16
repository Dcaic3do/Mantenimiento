package com.example.Mantenimiento.Service;

import com.example.Mantenimiento.Model.TiempoTaller;
import com.example.Mantenimiento.Repository.TiempoTallerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TiempoTallerService {
    private final TiempoTallerRepository tiempoTallerRepository;

    public TiempoTallerService(TiempoTallerRepository tiempoTallerRepository) {
        this.tiempoTallerRepository = tiempoTallerRepository;
    }

    public TiempoTaller guardar(TiempoTaller tiempoTaller) {
        try {
            return tiempoTallerRepository.save(tiempoTaller);
        } catch (Exception e) {
            throw new RuntimeException("Error al guardar el tiempo desde el taller " + e.getMessage(), e);
        }
    }

    public List<TiempoTaller> listar(){
        try {
            return tiempoTallerRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Error al listar los tiempos desde el taller " + e.getMessage(), e);
        }
    }

    public void eliminar(Long id_tiempoTaller) {
        try {
            if (!tiempoTallerRepository.existsById(id_tiempoTaller)) {
                throw new IllegalArgumentException("No se encontró un tiempo desde el taller con el ID " + id_tiempoTaller);
            }
            tiempoTallerRepository.deleteById(id_tiempoTaller);
        } catch (Exception e) {
            throw new RuntimeException("Error al eliminar el tiempo desde el taller " + e.getMessage(), e);
        }
    }

    public Optional<TiempoTaller> listarPorId(long id_tiempoTaller) {
        try {
            Optional<TiempoTaller> tiempoTaller = tiempoTallerRepository.findById(id_tiempoTaller);
            if (tiempoTaller.isEmpty()) {
                throw new IllegalArgumentException("Tiempo desde el taller con ID " + id_tiempoTaller + " no encontrado.");
            }
            return tiempoTaller;
        } catch (Exception e) {
            throw new RuntimeException("Error al buscar el tiempo desde el taller con ID " + id_tiempoTaller + e.getMessage(), e);
        }
    }
    public TiempoTaller actualizar(Long id_tiempoTaller, TiempoTaller tiempoTaller) {
        if (!tiempoTallerRepository.existsById(id_tiempoTaller)) {
            throw new IllegalArgumentException("No se encontró un tiempo desde el taller con el ID " + id_tiempoTaller);
        }
        return tiempoTallerRepository.save(tiempoTaller);
    }
}
