package com.example.Mantenimiento.Controller;

import com.example.Mantenimiento.Model.SolicitudInspeccion;
import com.example.Mantenimiento.Repository.SolicitudInsRepository;
import com.example.Mantenimiento.Service.SolicitudInspeccionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/solicitudInspecion")
public class SolicitudInsController {
    private final SolicitudInspeccionService solicitudInspeccionService;
    private final SolicitudInsRepository solicitudInsRepository;

    public SolicitudInsController(SolicitudInspeccionService solicitudInspeccionService, SolicitudInsRepository solicitudInsRepository) {
        this.solicitudInspeccionService = solicitudInspeccionService;
        this.solicitudInsRepository = solicitudInsRepository;
    }

    @PostMapping("/guardar")
    public ResponseEntity<SolicitudInspeccion> guardar(@RequestBody SolicitudInspeccion solicitudInspeccion) {
        return ResponseEntity.ok(solicitudInspeccionService.guardar(solicitudInspeccion));
    }

    @GetMapping("/listar")
    public ResponseEntity<List<SolicitudInspeccion>> listar() {
        return ResponseEntity.ok(solicitudInspeccionService.listar());
    }

    @GetMapping("/listar/{id_solicitudInspeccion}")
    public ResponseEntity<SolicitudInspeccion> obtenerPorId(@PathVariable long id_solicitudInspeccion) {
        return solicitudInspeccionService.listarPorId(id_solicitudInspeccion)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/eliminar/{id_solicitudInspeccion}")
    public ResponseEntity<Void> eliminar(@PathVariable long id_solicitudInspeccion) {
        solicitudInspeccionService.eliminar(id_solicitudInspeccion);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/actualizar/{id_solicitudInspeccion}")
    public ResponseEntity<SolicitudInspeccion> actualizar(@PathVariable Long id_solicitudInspeccion, @RequestBody SolicitudInspeccion solicitudInspeccion) {
        solicitudInspeccion.setId_solicitudInspeccion(id_solicitudInspeccion);
        SolicitudInspeccion solicitudInspeccionActualizado = solicitudInspeccionService.actualizar(id_solicitudInspeccion, solicitudInspeccion);
        return ResponseEntity.ok(solicitudInspeccionActualizado);
    }
}
