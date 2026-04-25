package com.example.Mantenimiento.Controller;

import com.example.Mantenimiento.Model.Asignacion;
import com.example.Mantenimiento.Service.AsignacionAutomaticaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/asignaciones")
public class AsignacionAutomaticaController {

    @Autowired
    private AsignacionAutomaticaService service;

    @PostMapping("/asignar_automaticamente")
    public ResponseEntity<?> ejecutarAsignacion() {
        // Ahora 'procesarPlanSemanal' devuelve List<Asignacion>, por lo que ok() ya no está vacío
        List<Asignacion> resultado = service.procesarPlanSemanal();

        if (resultado.isEmpty()) {
            return ResponseEntity.ok("No se pudieron generar nuevas asignaciones con los recursos disponibles.");
        }

        return ResponseEntity.ok(resultado);
    }
}
