package com.example.Mantenimiento.Repository;

import com.example.Mantenimiento.Model.Equipo;
import com.example.Mantenimiento.Model.Flota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EquipoRepository extends JpaRepository<Equipo, Long> {
}
