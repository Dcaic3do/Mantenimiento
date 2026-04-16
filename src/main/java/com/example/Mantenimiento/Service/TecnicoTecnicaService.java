package com.example.Mantenimiento.Service;

import com.example.Mantenimiento.Model.Flota;
import com.example.Mantenimiento.Model.TecnicoTecnica;
import com.example.Mantenimiento.Repository.TecnicoTecnicaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TecnicoTecnicaService {
    private final TecnicoTecnicaRepository tecnicoTecnicaRepository;

    public TecnicoTecnicaService(TecnicoTecnicaRepository tecnicoTecnicaRepository) {
        this.tecnicoTecnicaRepository = tecnicoTecnicaRepository;
    }

    public TecnicoTecnica guardar(TecnicoTecnica tecnicoTecnica) {
        try {
            return tecnicoTecnicaRepository.save(tecnicoTecnica);
        } catch (Exception e) {
            throw new RuntimeException("Error al guardar las técnicas de técnico " + e.getMessage(), e);
        }
    }

    public List<TecnicoTecnica> listar(){
        try {
            return tecnicoTecnicaRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Error al listar las técnicas de los técnicos " + e.getMessage(), e);
        }
    }

    public void eliminar(Long id_tecnicoTecnica) {
        try {
            if (!tecnicoTecnicaRepository.existsById(id_tecnicoTecnica)) {
                throw new IllegalArgumentException("No se encontró una técnica de técnico con el ID " + id_tecnicoTecnica);
            }
            tecnicoTecnicaRepository.deleteById(id_tecnicoTecnica);
        } catch (Exception e) {
            throw new RuntimeException("Error al eliminar la técnica del técnico " + e.getMessage(), e);
        }
    }

    public Optional<TecnicoTecnica> listarPorId(long id_tecnicoTecnica) {
        try {
            Optional<TecnicoTecnica> tecnicoTecnica = tecnicoTecnicaRepository.findById(id_tecnicoTecnica);
            if (tecnicoTecnica.isEmpty()) {
                throw new IllegalArgumentException("técnica de técnico con ID " + id_tecnicoTecnica + " no encontrado.");
            }
            return tecnicoTecnica;
        } catch (Exception e) {
            throw new RuntimeException("Error al buscar la técnica de técnico con ID " + id_tecnicoTecnica + e.getMessage(), e);
        }
    }
    public TecnicoTecnica actualizar(Long id_tecnicoTecnica, TecnicoTecnica tecnicoTecnica) {
        if (!tecnicoTecnicaRepository.existsById(id_tecnicoTecnica)) {
            throw new IllegalArgumentException("No se encontró una técnica de técnico con el ID " + id_tecnicoTecnica);
        }
        return tecnicoTecnicaRepository.save(tecnicoTecnica);
    }
}
