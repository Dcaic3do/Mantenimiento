package com.example.Mantenimiento.Repository;

import com.example.Mantenimiento.Model.SolicitudInspeccion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SolicitudInsRepository extends JpaRepository<SolicitudInspeccion, Long> {
}
