package com.example.Mantenimiento.Repository;

import com.example.Mantenimiento.Model.Asignacion;
import com.example.Mantenimiento.Model.Flota;
import com.example.Mantenimiento.Model.Tecnico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AsignacionRepository extends JpaRepository<Asignacion, Long> {
    // Busca asignaciones de un técnico específico en una fecha para evitar traslapes
    @Query("SELECT a FROM Asignacion a WHERE (a.tecnico_lider = :tecnico OR a.tecnico_acompanante = :tecnico)")
    List<Asignacion> findAllByTecnico(@Param("tecnico") Tecnico tecnico);

    @Modifying
    @Transactional
    @Query("""
        DELETE FROM Asignacion a
        WHERE a.solicitud.estado NOT IN ('Programada', 'En proceso')
    """)
    void eliminarAsignacionesNoActivas();
}
