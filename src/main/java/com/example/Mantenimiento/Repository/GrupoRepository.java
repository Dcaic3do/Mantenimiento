package com.example.Mantenimiento.Repository;

import com.example.Mantenimiento.Model.Grupo;
import com.example.Mantenimiento.Model.TipoTurno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GrupoRepository extends JpaRepository<Grupo, Long> {
}
