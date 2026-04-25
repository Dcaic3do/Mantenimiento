package com.example.Mantenimiento.Service;

import com.example.Mantenimiento.DTO.SolicitudInspeccionDTO;
import com.example.Mantenimiento.Mapper.SolicitudInspeccionMapper;
import com.example.Mantenimiento.Model.SolicitudInspeccion;
import com.example.Mantenimiento.Repository.AsignacionRepository;
import com.example.Mantenimiento.Repository.SolicitudInsRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class SolicitudInspeccionService {
    private final SolicitudInsRepository solicitudInsRepository;
    private final AsignacionRepository asignacionRepository;

    public SolicitudInspeccionService(SolicitudInsRepository solicitudInsRepository, AsignacionRepository asignacionRepository) {
        this.solicitudInsRepository = solicitudInsRepository;
        this.asignacionRepository = asignacionRepository;
    }

    public SolicitudInspeccion guardar(SolicitudInspeccion solicitudInspeccion) {
        try {
            return solicitudInsRepository.save(solicitudInspeccion);
        } catch (Exception e) {
            throw new RuntimeException("Error al guardar la solicitud de inspección " + e.getMessage(), e);
        }
    }

    public List<SolicitudInspeccionDTO> listar() {
        return solicitudInsRepository.findAll()
                .stream()
                .map(SolicitudInspeccionMapper::toDTO)
                .toList();
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

    public SolicitudInspeccionDTO listarPorId(long id) {
        return solicitudInsRepository.findById(id)
                .map(SolicitudInspeccionMapper::toDTO)
                .orElseThrow(() ->
                        new RuntimeException("Solicitud no encontrada")
                );
    }

    public SolicitudInspeccionDTO actualizar(Long id, String estado) {

        SolicitudInspeccion solicitud = solicitudInsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No existe"));

        solicitud.setEstado(estado);

        return SolicitudInspeccionMapper.toDTO(
                solicitudInsRepository.save(solicitud)
        );
    }

    @Transactional
    public void actualizarEstado(Long id, String estado) {
        SolicitudInspeccion solicitud = solicitudInsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No encontrada"));

        solicitud.setEstado(estado);
        solicitudInsRepository.save(solicitud);

        solicitud.setEstado(estado);
        solicitudInsRepository.save(solicitud);
        asignacionRepository.eliminarAsignacionesNoActivas();
    }
}
