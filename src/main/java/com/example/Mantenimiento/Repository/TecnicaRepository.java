package com.example.Mantenimiento.Repository;

import com.example.Mantenimiento.Model.Tecnica;
import com.example.Mantenimiento.Model.TipoTurno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TecnicaRepository extends JpaRepository<Tecnica, Long> {
}
