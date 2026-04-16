package com.example.Mantenimiento.Service;

import com.example.Mantenimiento.Model.TipoTurno;
import com.example.Mantenimiento.Repository.TipoTurnoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TipoTurnoService {
    private final TipoTurnoRepository tipoTurnoRepository;

    public TipoTurnoService(TipoTurnoRepository tipoTurnoRepository) {
        this.tipoTurnoRepository = tipoTurnoRepository;
    }

    public TipoTurno guardar(TipoTurno tipoTurno) {
        try {
            return tipoTurnoRepository.save(tipoTurno);
        } catch (Exception e) {
            throw new RuntimeException("Error al guardar el tipo de turno " + e.getMessage(), e);
        }
    }

    public List<TipoTurno> listar(){
        try {
            return tipoTurnoRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Error al listar los tipos de turno " + e.getMessage(), e);
        }
    }

    public void eliminar(Long id_tipoTurno) {
        try {
            if (!tipoTurnoRepository.existsById(id_tipoTurno)) {
                throw new IllegalArgumentException("No se encontró un tipo de turno con el ID " + id_tipoTurno);
            }
            tipoTurnoRepository.deleteById(id_tipoTurno);
        } catch (Exception e) {
            throw new RuntimeException("Error al eliminar el tipo de turno " + e.getMessage(), e);
        }
    }

    public Optional<TipoTurno> listarPorId(long id_tipoTurno) {
        try {
            Optional<TipoTurno> tipoTurno = tipoTurnoRepository.findById(id_tipoTurno);
            if (tipoTurno.isEmpty()) {
                throw new IllegalArgumentException("Tipo de turno con ID " + id_tipoTurno + " no encontrado.");
            }
            return tipoTurno;
        } catch (Exception e) {
            throw new RuntimeException("Error al buscar el tipo de turno con ID " + id_tipoTurno + e.getMessage(), e);
        }
    }
    public TipoTurno actualizar(Long id_tipoTurno, TipoTurno tipoTurno) {
        if (!tipoTurnoRepository.existsById(id_tipoTurno)) {
            throw new IllegalArgumentException("No se encontró un tipo de turno con el ID " + id_tipoTurno);
        }
        return tipoTurnoRepository.save(tipoTurno);
    }
}
