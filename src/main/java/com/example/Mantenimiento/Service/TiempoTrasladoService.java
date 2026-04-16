package com.example.Mantenimiento.Service;

import com.example.Mantenimiento.Model.Flota;
import com.example.Mantenimiento.Model.TiempoTraslado;
import com.example.Mantenimiento.Repository.TiempoTrasladoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TiempoTrasladoService {
    private final TiempoTrasladoRepository tiempoTrasladoRepository;

    public TiempoTrasladoService(TiempoTrasladoRepository tiempoTrasladoRepository) {
        this.tiempoTrasladoRepository = tiempoTrasladoRepository;
    }

    public TiempoTraslado guardar(TiempoTraslado tiempoTraslado) {
        try {
            return tiempoTrasladoRepository.save(tiempoTraslado);
        } catch (Exception e) {
            throw new RuntimeException("Error al guardar el tiempo de traslado " + e.getMessage(), e);
        }
    }

    public List<TiempoTraslado> listar(){
        try {
            return tiempoTrasladoRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Error al listar las tiempos de traslado " + e.getMessage(), e);
        }
    }

    public void eliminar(Long id_tiempoTraslado) {
        try {
            if (!tiempoTrasladoRepository.existsById(id_tiempoTraslado)) {
                throw new IllegalArgumentException("No se encontró un tiempo de traslado con el ID " + id_tiempoTraslado);
            }
            tiempoTrasladoRepository.deleteById(id_tiempoTraslado);
        } catch (Exception e) {
            throw new RuntimeException("Error al eliminar el tiempo de traslado " + e.getMessage(), e);
        }
    }

    public Optional<TiempoTraslado> listarPorId(long id_tiempoTraslado) {
        try {
            Optional<TiempoTraslado> tiempoTraslado = tiempoTrasladoRepository.findById(id_tiempoTraslado);
            if (tiempoTraslado.isEmpty()) {
                throw new IllegalArgumentException("Tiempo de traslado con ID " + id_tiempoTraslado + " no encontrado.");
            }
            return tiempoTraslado;
        } catch (Exception e) {
            throw new RuntimeException("Error al buscar el tiempo de traslado con ID " + id_tiempoTraslado + e.getMessage(), e);
        }
    }
    public TiempoTraslado actualizar(Long id_tiempoTraslado, TiempoTraslado tiempoTraslado) {
        if (!tiempoTrasladoRepository.existsById(id_tiempoTraslado)) {
            throw new IllegalArgumentException("No se encontró un tiempo de traslado con el ID " + id_tiempoTraslado);
        }
        return tiempoTrasladoRepository.save(tiempoTraslado);
    }
}
