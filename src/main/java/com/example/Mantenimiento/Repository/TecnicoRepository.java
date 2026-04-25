package com.example.Mantenimiento.Repository;

import com.example.Mantenimiento.Model.Flota;
import com.example.Mantenimiento.Model.Tecnica;
import com.example.Mantenimiento.Model.Tecnico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TecnicoRepository extends JpaRepository<Tecnico, Long> {
    // Spring interpretará esto como: SELECT * FROM tecnico WHERE activo = true
    List<Tecnico> findByActivoTrue();

    @Query("SELECT t FROM Tecnico t JOIN t.tecnicoTecnicas tt WHERE t.activo = true AND tt.tecnica = :tecnica")
    List<Tecnico> findCalificadosByTecnica(@Param("tecnica") Tecnica tecnica);
}
