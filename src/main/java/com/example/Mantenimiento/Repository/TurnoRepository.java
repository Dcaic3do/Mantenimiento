package com.example.Mantenimiento.Repository;

import com.example.Mantenimiento.Model.Flota;
import com.example.Mantenimiento.Model.Turno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalTime;
import java.util.Date;
import java.util.Optional;

@Repository
public interface TurnoRepository extends JpaRepository<Turno, Long> {
    @Query("""
    SELECT DISTINCT t
    FROM Turno t
    JOIN t.asignaciones a
    JOIN t.tipo_turno tt
    WHERE a.tecnico_lider.id_tecnico = :idTecnico
    AND t.fecha = :fecha
    AND :hora BETWEEN tt.horaInicio AND tt.horaFin
    """)
    Optional<Turno> findTurnoPorTecnicoFechaYHora(
            @Param("idTecnico") Long idTecnico,
            @Param("fecha") Date fecha,
            @Param("hora") LocalTime hora
    );
}
