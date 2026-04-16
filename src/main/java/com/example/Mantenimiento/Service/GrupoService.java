package com.example.Mantenimiento.Service;

import com.example.Mantenimiento.Model.Grupo;
import com.example.Mantenimiento.Repository.GrupoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GrupoService {
    private final GrupoRepository grupoRepository;

    public GrupoService(GrupoRepository grupoRepository) {
        this.grupoRepository = grupoRepository;
    }

    public Grupo guardar(Grupo grupo) {
        try {
            return grupoRepository.save(grupo);
        } catch (Exception e) {
            throw new RuntimeException("Error al guardar el grupo " + e.getMessage(), e);
        }
    }

    public List<Grupo> listar(){
        try {
            return grupoRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Error al listar los grupos " + e.getMessage(), e);
        }
    }

    public void eliminar(Long id_grupo) {
        try {
            if (!grupoRepository.existsById(id_grupo)) {
                throw new IllegalArgumentException("No se encontró un grupo con el ID " + id_grupo);
            }
            grupoRepository.deleteById(id_grupo);
        } catch (Exception e) {
            throw new RuntimeException("Error al eliminar el grupo " + e.getMessage(), e);
        }
    }

    public Optional<Grupo> listarPorId(long id_grupo) {
        try {
            Optional<Grupo> grupo = grupoRepository.findById(id_grupo);
            if (grupo.isEmpty()) {
                throw new IllegalArgumentException("Grupo con ID " + id_grupo + " no encontrado.");
            }
            return grupo;
        } catch (Exception e) {
            throw new RuntimeException("Error al buscar el grupo con ID " + id_grupo + e.getMessage(), e);
        }
    }
    public Grupo actualizar(Long id_grupo, Grupo grupo) {
        if (!grupoRepository.existsById(id_grupo)) {
            throw new IllegalArgumentException("No se encontró un tipo de turno con el ID " + id_grupo);
        }
        return grupoRepository.save(grupo);
    }
}
