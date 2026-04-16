package com.example.Mantenimiento.Service;

import com.example.Mantenimiento.Model.Equipo;
import com.example.Mantenimiento.Repository.EquipoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EquipoService {
    private final EquipoRepository equipoRepository;

    public EquipoService(EquipoRepository equipoRepository) {
        this.equipoRepository = equipoRepository;
    }

    public Equipo guardar(Equipo equipo) {
        try {
            return equipoRepository.save(equipo);
        } catch (Exception e) {
            throw new RuntimeException("Error al guardar el equipo " + e.getMessage(), e);
        }
    }

    public List<Equipo> listar(){
        try {
            return equipoRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Error al listar los Equipos " + e.getMessage(), e);
        }
    }

    public void eliminar(Long id_equipo) {
        try {
            if (!equipoRepository.existsById(id_equipo)) {
                throw new IllegalArgumentException("No se encontró un equipo con el ID " + id_equipo);
            }
            equipoRepository.deleteById(id_equipo);
        } catch (Exception e) {
            throw new RuntimeException("Error al eliminar el equipo " + e.getMessage(), e);
        }
    }

    public Optional<Equipo> listarPorId(long id_equipo) {
        try {
            Optional<Equipo> equipo = equipoRepository.findById(id_equipo);
            if (equipo.isEmpty()) {
                throw new IllegalArgumentException("Equipo con ID " + id_equipo + " no encontrado.");
            }
            return equipo;
        } catch (Exception e) {
            throw new RuntimeException("Error al buscar el equipo con ID " + id_equipo + e.getMessage(), e);
        }
    }
    public Equipo actualizar(Long id_equipo, Equipo equipo) {
        if (!equipoRepository.existsById(id_equipo)) {
            throw new IllegalArgumentException("No se encontró un equipo con el ID " + id_equipo);
        }
        return equipoRepository.save(equipo);
    }
}
