package com.example.Mantenimiento.Controller;

import com.example.Mantenimiento.Model.Flota;
import com.example.Mantenimiento.Model.Grupo;
import com.example.Mantenimiento.Repository.FlotaRepository;
import com.example.Mantenimiento.Service.FlotaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/flota")
public class FlotaController {
    private final FlotaService flotaService;
    private final FlotaRepository flotaRepository;

    public FlotaController(FlotaService flotaService, FlotaRepository flotaRepository) {
        this.flotaService = flotaService;
        this.flotaRepository = flotaRepository;
    }

    @PostMapping("/guardar")
    public ResponseEntity<Flota> guardar(@RequestBody Flota flota) {
        return ResponseEntity.ok(flotaService.guardar(flota));
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Flota>> listar() {
        return ResponseEntity.ok(flotaService.listar());
    }

    @GetMapping("/listar/{id_flota}")
    public ResponseEntity<Flota> obtenerPorId(@PathVariable long id_flota) {
        return flotaService.listarPorId(id_flota)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/eliminar/{id_flota}")
    public ResponseEntity<Void> eliminar(@PathVariable long id_flota) {
        flotaService.eliminar(id_flota);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/actualizar/{id_flota}")
    public ResponseEntity<Flota> actualizar(@PathVariable Long id_flota, @RequestBody Flota flota) {
        flota.setId_flota(id_flota);
        Flota flotaActualizado = flotaService.actualizar(id_flota, flota);
        return ResponseEntity.ok(flotaActualizado);
    }
}
