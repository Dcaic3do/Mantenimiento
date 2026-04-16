package com.example.Mantenimiento.Repository;

import com.example.Mantenimiento.Model.Asignacion;
import com.example.Mantenimiento.Model.Flota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AsignacionRepository extends JpaRepository<Asignacion, Long> {
}
