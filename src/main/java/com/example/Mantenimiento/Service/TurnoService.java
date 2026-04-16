package com.example.Mantenimiento.Service;

import com.example.Mantenimiento.Model.Turno;
import com.example.Mantenimiento.Repository.TurnoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TurnoService {
    private final TurnoRepository turnoRepository;

    public TurnoService(TurnoRepository turnoRepository) {
        this.turnoRepository = turnoRepository;
    }

    public Turno guardar(Turno turno) {
        try {
            return turnoRepository.save(turno);
        } catch (Exception e) {
            throw new RuntimeException("Error al guardar el turno " + e.getMessage(), e);
        }
    }

    public List<Turno> listar(){
        try {
            return turnoRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Error al listar los turnos " + e.getMessage(), e);
        }
    }

    public void eliminar(Long id_turno) {
        try {
            if (!turnoRepository.existsById(id_turno)) {
                throw new IllegalArgumentException("No se encontró un turno con el ID " + id_turno);
            }
            turnoRepository.deleteById(id_turno);
        } catch (Exception e) {
            throw new RuntimeException("Error al eliminar el turno " + e.getMessage(), e);
        }
    }

    public Optional<Turno> listarPorId(long id_turno) {
        try {
            Optional<Turno> turno = turnoRepository.findById(id_turno);
            if (turno.isEmpty()) {
                throw new IllegalArgumentException("Turno con ID " + id_turno + " no encontrado.");
            }
            return turno;
        } catch (Exception e) {
            throw new RuntimeException("Error al buscar el turno con ID " + id_turno + e.getMessage(), e);
        }
    }
    public Turno actualizar(Long id_turno, Turno turno) {
        if (!turnoRepository.existsById(id_turno)) {
            throw new IllegalArgumentException("No se encontró un turno con el ID " + id_turno);
        }
        return turnoRepository.save(turno);
    }
}
