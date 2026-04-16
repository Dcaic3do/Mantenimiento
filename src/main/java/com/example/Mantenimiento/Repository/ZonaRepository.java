package com.example.Mantenimiento.Repository;

import com.example.Mantenimiento.Model.Grupo;
import com.example.Mantenimiento.Model.Zona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ZonaRepository extends JpaRepository<Zona, Long> {
}
