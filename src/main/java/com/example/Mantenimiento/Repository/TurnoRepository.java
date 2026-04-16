package com.example.Mantenimiento.Repository;

import com.example.Mantenimiento.Model.Flota;
import com.example.Mantenimiento.Model.Turno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TurnoRepository extends JpaRepository<Turno, Long> {
}
