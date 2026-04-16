package com.example.Mantenimiento.Service;

import com.example.Mantenimiento.Model.Zona;
import com.example.Mantenimiento.Repository.ZonaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ZonaService {
    private final ZonaRepository zonaRepository;

    public ZonaService(ZonaRepository zonaRepository) {
        this.zonaRepository = zonaRepository;
    }

    public Zona guardar(Zona zona) {
        try {
            return zonaRepository.save(zona);
        } catch (Exception e) {
            throw new RuntimeException("Error al guardar la zona " + e.getMessage(), e);
        }
    }

    public List<Zona> listar(){
        try {
            return zonaRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Error al listar las zonas " + e.getMessage(), e);
        }
    }

    public void eliminar(Long id_zona) {
        try {
            if (!zonaRepository.existsById(id_zona)) {
                throw new IllegalArgumentException("No se encontró una zona con el ID " + id_zona);
            }
            zonaRepository.deleteById(id_zona);
        } catch (Exception e) {
            throw new RuntimeException("Error al eliminar la zona " + e.getMessage(), e);
        }
    }

    public Optional<Zona> listarPorId(long id_zona) {
        try {
            Optional<Zona> zona = zonaRepository.findById(id_zona);
            if (zona.isEmpty()) {
                throw new IllegalArgumentException("Zona con ID " + id_zona + " no encontrado.");
            }
            return zona;
        } catch (Exception e) {
            throw new RuntimeException("Error al buscar la zona con ID " + id_zona + e.getMessage(), e);
        }
    }
    public Zona actualizar(Long id_zona, Zona zona) {
        if (!zonaRepository.existsById(id_zona)) {
            throw new IllegalArgumentException("No se encontró una zona con el ID " + id_zona);
        }
        return zonaRepository.save(zona);
    }
}
