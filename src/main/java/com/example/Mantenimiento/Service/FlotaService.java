package com.example.Mantenimiento.Service;

import com.example.Mantenimiento.Model.Flota;
import com.example.Mantenimiento.Repository.FlotaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FlotaService {
    private final FlotaRepository flotaRepository;

    public FlotaService(FlotaRepository flotaRepository) {
        this.flotaRepository = flotaRepository;
    }

    public Flota guardar(Flota flota) {
        try {
            return flotaRepository.save(flota);
        } catch (Exception e) {
            throw new RuntimeException("Error al guardar la flota " + e.getMessage(), e);
        }
    }

    public List<Flota> listar(){
        try {
            return flotaRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Error al listar las flotas " + e.getMessage(), e);
        }
    }

    public void eliminar(Long id_flota) {
        try {
            if (!flotaRepository.existsById(id_flota)) {
                throw new IllegalArgumentException("No se encontró una flota con el ID " + id_flota);
            }
            flotaRepository.deleteById(id_flota);
        } catch (Exception e) {
            throw new RuntimeException("Error al eliminar la flota " + e.getMessage(), e);
        }
    }

    public Optional<Flota> listarPorId(long id_flota) {
        try {
            Optional<Flota> flota = flotaRepository.findById(id_flota);
            if (flota.isEmpty()) {
                throw new IllegalArgumentException("Flota con ID " + id_flota + " no encontrado.");
            }
            return flota;
        } catch (Exception e) {
            throw new RuntimeException("Error al buscar la flota con ID " + id_flota + e.getMessage(), e);
        }
    }
    public Flota actualizar(Long id_flota, Flota flota) {
        if (!flotaRepository.existsById(id_flota)) {
            throw new IllegalArgumentException("No se encontró una flota con el ID " + id_flota);
        }
        return flotaRepository.save(flota);
    }
}
