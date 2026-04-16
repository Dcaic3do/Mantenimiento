package com.example.Mantenimiento.Service;

import com.example.Mantenimiento.Model.Tecnica;
import com.example.Mantenimiento.Repository.TecnicaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TecnicaService {
    private final TecnicaRepository tecnicaRepository;

    public TecnicaService(TecnicaRepository tecnicaRepository) {
        this.tecnicaRepository = tecnicaRepository;
    }

    public Tecnica guardar(Tecnica tecnica) {
        try {
            return tecnicaRepository.save(tecnica);
        } catch (Exception e) {
            throw new RuntimeException("Error al guardar la técnica " + e.getMessage(), e);
        }
    }

    public List<Tecnica> listar(){
        try {
            return tecnicaRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Error al listar las técnicas " + e.getMessage(), e);
        }
    }

    public void eliminar(Long id_tecnica) {
        try {
            if (!tecnicaRepository.existsById(id_tecnica)) {
                throw new IllegalArgumentException("No se encontró una técnica con el ID " + id_tecnica);
            }
            tecnicaRepository.deleteById(id_tecnica);
        } catch (Exception e) {
            throw new RuntimeException("Error al eliminar la técnica " + e.getMessage(), e);
        }
    }

    public Optional<Tecnica> listarPorId(long id_tecnica) {
        try {
            Optional<Tecnica> tecnica = tecnicaRepository.findById(id_tecnica);
            if (tecnica.isEmpty()) {
                throw new IllegalArgumentException("Técnica con ID " + id_tecnica + " no encontrado.");
            }
            return tecnica;
        } catch (Exception e) {
            throw new RuntimeException("Error al buscar la técnica con ID " + id_tecnica + e.getMessage(), e);
        }
    }
    public Tecnica actualizar(Long id_tecnica, Tecnica tecnica) {
        if (!tecnicaRepository.existsById(id_tecnica)) {
            throw new IllegalArgumentException("No se encontró una técnica con el ID " + id_tecnica);
        }
        return tecnicaRepository.save(tecnica);
    }
}
