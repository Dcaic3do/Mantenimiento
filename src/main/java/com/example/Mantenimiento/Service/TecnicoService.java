package com.example.Mantenimiento.Service;

import com.example.Mantenimiento.DTO.TecnicoDTO;
import com.example.Mantenimiento.Mapper.TecnicoMapper;
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

    public TecnicoDTO guardar(Tecnico tecnico) {
        try {
            Tecnico saved = tecnicoRepository.save(tecnico);
            return TecnicoMapper.toDTO(saved);
        } catch (Exception e) {
            throw new RuntimeException("Error al guardar el técnico: " + e.getMessage(), e);
        }
    }

    public List<TecnicoDTO> listar() {
        try {
            return tecnicoRepository.findAll()
                    .stream()
                    .map(TecnicoMapper::toDTO)
                    .toList();
        } catch (Exception e) {
            throw new RuntimeException("Error al listar los técnicos: " + e.getMessage(), e);
        }
    }

    public void eliminar(Long id_tecnico) {
        try {
            if (!tecnicoRepository.existsById(id_tecnico)) {
                throw new IllegalArgumentException("No se encontró un técnico con el ID " + id_tecnico);
            }
            tecnicoRepository.deleteById(id_tecnico);
        } catch (Exception e) {
            throw new RuntimeException("Error al eliminar el técnico: " + e.getMessage(), e);
        }
    }

    public TecnicoDTO listarPorId(long id_tecnico) {
        try {
            Tecnico tecnico = tecnicoRepository.findById(id_tecnico)
                    .orElseThrow(() ->
                            new IllegalArgumentException("Técnico con ID " + id_tecnico + " no encontrado.")
                    );

            return TecnicoMapper.toDTO(tecnico);

        } catch (Exception e) {
            throw new RuntimeException("Error al buscar el técnico con ID " + id_tecnico + ": " + e.getMessage(), e);
        }
    }

    public TecnicoDTO actualizar(Long id_tecnico, Tecnico tecnico) {
        try {
            if (!tecnicoRepository.existsById(id_tecnico)) {
                throw new IllegalArgumentException("No se encontró un técnico con el ID " + id_tecnico);
            }

            tecnico.setId_tecnico(id_tecnico);
            Tecnico updated = tecnicoRepository.save(tecnico);

            return TecnicoMapper.toDTO(updated);

        } catch (Exception e) {
            throw new RuntimeException("Error al actualizar el técnico: " + e.getMessage(), e);
        }
    }
}
