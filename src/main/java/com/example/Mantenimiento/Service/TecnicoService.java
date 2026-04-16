package com.example.Mantenimiento.Service;

import com.example.Mantenimiento.Model.Flota;
import com.example.Mantenimiento.Model.Tecnico;
import com.example.Mantenimiento.Repository.TecnicoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TecnicoService {
    private final TecnicoRepository tecnicoRepository;

    public TecnicoService(TecnicoRepository tecnicoRepository) {
        this.tecnicoRepository = tecnicoRepository;
    }

    public Tecnico guardar(Tecnico tecnico) {
        try {
            return tecnicoRepository.save(tecnico);
        } catch (Exception e) {
            throw new RuntimeException("Error al guardar el técnico " + e.getMessage(), e);
        }
    }

    public List<Tecnico> listar(){
        try {
            return tecnicoRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Error al listar los técnicos " + e.getMessage(), e);
        }
    }

    public void eliminar(Long id_tecnico) {
        try {
            if (!tecnicoRepository.existsById(id_tecnico)) {
                throw new IllegalArgumentException("No se encontró un técnico con el ID " + id_tecnico);
            }
            tecnicoRepository.deleteById(id_tecnico);
        } catch (Exception e) {
            throw new RuntimeException("Error al eliminar el técnico " + e.getMessage(), e);
        }
    }

    public Optional<Tecnico> listarPorId(long id_tecnico) {
        try {
            Optional<Tecnico> tecnico = tecnicoRepository.findById(id_tecnico);
            if (tecnico.isEmpty()) {
                throw new IllegalArgumentException("Técnico con ID " + id_tecnico + " no encontrado.");
            }
            return tecnico;
        } catch (Exception e) {
            throw new RuntimeException("Error al buscar el técnico con ID " + id_tecnico + e.getMessage(), e);
        }
    }
    public Tecnico actualizar(Long id_tecnico, Tecnico tecnico) {
        if (!tecnicoRepository.existsById(id_tecnico)) {
            throw new IllegalArgumentException("No se encontró un técnico con el ID " + id_tecnico);
        }
        return tecnicoRepository.save(tecnico);
    }
}
