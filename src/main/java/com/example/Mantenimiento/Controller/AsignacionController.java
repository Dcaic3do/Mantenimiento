package com.example.Mantenimiento.Controller;

import com.example.Mantenimiento.DTO.AsignacionDTO;
import com.example.Mantenimiento.Model.Asignacion;
import com.example.Mantenimiento.Repository.AsignacionRepository;
import com.example.Mantenimiento.Service.AsignacionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/asignacion")
public class AsignacionController {
    private final AsignacionService asignacionService;
    private final AsignacionRepository asignacionRepository;

    public AsignacionController(AsignacionService asignacionService, AsignacionRepository asignacionRepository) {
        this.asignacionService = asignacionService;
        this.asignacionRepository = asignacionRepository;
    }

    @PostMapping("/guardar")
    public ResponseEntity<Asignacion> guardar(@RequestBody Asignacion asignacion) {
        return ResponseEntity.ok(asignacionService.guardar(asignacion));
    }

    @GetMapping("/listar")
    public ResponseEntity<List<AsignacionDTO>> listar() {
        return ResponseEntity.ok(asignacionService.listar());
    }

    @GetMapping("/listar/{id_asignacion}")
    public ResponseEntity<AsignacionDTO> obtenerPorId(@PathVariable long id_asignacion) {
        return ResponseEntity.ok(asignacionService.listarPorId(id_asignacion));
    }

    @DeleteMapping("/eliminar/{id_asignacion}")
    public ResponseEntity<Void> eliminar(@PathVariable long id_asignacion) {
        asignacionService.eliminar(id_asignacion);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/actualizar/{id_asignacion}")
    public ResponseEntity<Asignacion> actualizar(
            @PathVariable Long id_asignacion,
            @RequestBody Asignacion asignacion) {

        asignacion.setId_asignacion(id_asignacion);
        return ResponseEntity.ok(asignacionService.actualizar(id_asignacion, asignacion));
    }
}
