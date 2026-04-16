package com.example.Mantenimiento.Repository;

import com.example.Mantenimiento.Model.Flota;
import com.example.Mantenimiento.Model.Tecnico;
import com.example.Mantenimiento.Model.TecnicoTecnica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TecnicoTecnicaRepository extends JpaRepository<TecnicoTecnica, Long> {
}
