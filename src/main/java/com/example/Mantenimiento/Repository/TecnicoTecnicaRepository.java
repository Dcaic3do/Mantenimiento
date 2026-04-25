package com.example.Mantenimiento.Repository;

import com.example.Mantenimiento.Model.TecnicoTecnica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TecnicoTecnicaRepository extends JpaRepository<TecnicoTecnica, Long> {
    @Query("SELECT COUNT(tt) > 0 FROM TecnicoTecnica tt " +
            "WHERE tt.tecnico.id_tecnico = :idTecnico " +
            "AND tt.tecnica.id_tecnica = :idTecnica")
    boolean existsRelation(@Param("idTecnico") Long idTecnico, @Param("idTecnica") Long idTecnica);
}
