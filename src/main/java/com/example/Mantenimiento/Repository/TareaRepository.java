package com.example.Mantenimiento.Repository;

import com.example.Mantenimiento.Model.Grupo;
import com.example.Mantenimiento.Model.Tarea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TareaRepository extends JpaRepository<Tarea, Long> {
}
