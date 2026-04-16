package com.example.Mantenimiento.Service;

import com.example.Mantenimiento.Model.Flota;
import com.example.Mantenimiento.Model.SolicitudInspeccion;
import com.example.Mantenimiento.Repository.SolicitudInsRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SolicitudInspeccionService {
    private final SolicitudInsRepository solicitudInsRepository;

    public SolicitudInspeccionService(SolicitudInsRepository solicitudInsRepository) {
        this.solicitudInsRepository = solicitudInsRepository;
    }

    public SolicitudInspeccion guardar(SolicitudInspeccion solicitudInspeccion) {
        try {
            return solicitudInsRepository.save(solicitudInspeccion);
        } catch (Exception e) {
            throw new RuntimeException("Error al guardar la solicitud de inspección " + e.getMessage(), e);
        }
    }

    public List<SolicitudInspeccion> listar(){
        try {
            return solicitudInsRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Error al listar las solicitudes de inspección " + e.getMessage(), e);
        }
    }

    public void eliminar(Long id_solicitudInspeccion) {
        try {
            if (!solicitudInsRepository.existsById(id_solicitudInspeccion)) {
                throw new IllegalArgumentException("No se encontró una solicitud de inspección con el ID " + id_solicitudInspeccion);
            }
            solicitudInsRepository.deleteById(id_solicitudInspeccion);
        } catch (Exception e) {
            throw new RuntimeException("Error al eliminar la solicitu de inspección " + e.getMessage(), e);
        }
    }

    public Optional<SolicitudInspeccion> listarPorId(long id_solicitudInspeccion) {
        try {
            Optional<SolicitudInspeccion> solicitudInspeccion = solicitudInsRepository.findById(id_solicitudInspeccion);
            if (solicitudInspeccion.isEmpty()) {
                throw new IllegalArgumentException("Solicitud de inspección con ID " + id_solicitudInspeccion + " no encontrado.");
            }
            return solicitudInspeccion;
        } catch (Exception e) {
            throw new RuntimeException("Error al buscar la solicitud de inspección con ID " + id_solicitudInspeccion + e.getMessage(), e);
        }
    }
    public SolicitudInspeccion actualizar(Long id_solicitudInspeccion, SolicitudInspeccion solicitudInspeccion) {
        if (!solicitudInsRepository.existsById(id_solicitudInspeccion)) {
            throw new IllegalArgumentException("No se encontró una solicitud de inspección con el ID " + id_solicitudInspeccion);
        }
        return solicitudInsRepository.save(solicitudInspeccion);
    }
}
