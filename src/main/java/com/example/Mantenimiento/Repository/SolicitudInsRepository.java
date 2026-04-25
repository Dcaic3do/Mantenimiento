package com.example.Mantenimiento.Repository;

import com.example.Mantenimiento.Model.SolicitudInspeccion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SolicitudInsRepository extends JpaRepository<SolicitudInspeccion, Long> {
    // Ajuste para buscar por múltiples estados (Programada, Pendiente, Reprogramada)
    @Query("SELECT s FROM SolicitudInspeccion s WHERE s.estado IN ('Pendiente')")
    List<SolicitudInspeccion> findAllParaProcesar();
}
