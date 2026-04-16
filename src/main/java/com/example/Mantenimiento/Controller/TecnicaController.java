package com.example.Mantenimiento.Controller;

import com.example.Mantenimiento.Model.Grupo;
import com.example.Mantenimiento.Model.Tecnica;
import com.example.Mantenimiento.Repository.TecnicaRepository;
import com.example.Mantenimiento.Service.TecnicaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tecnica")
public class TecnicaController {
    private final TecnicaService tecnicaService;
    private final TecnicaRepository tecnicaRepository;

    public TecnicaController(TecnicaService tecnicaService, TecnicaRepository tecnicaRepository) {
        this.tecnicaService = tecnicaService;
        this.tecnicaRepository = tecnicaRepository;
    }

    @PostMapping("/guardar")
    public ResponseEntity<Tecnica> guardar(@RequestBody Tecnica tecnica) {
        return ResponseEntity.ok(tecnicaService.guardar(tecnica));
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Tecnica>> listar() {
        return ResponseEntity.ok(tecnicaService.listar());
    }

    @GetMapping("/listar/{id_tecnica}")
    public ResponseEntity<Tecnica> obtenerPorId(@PathVariable long id_tecnica) {
        return tecnicaService.listarPorId(id_tecnica)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/eliminar/{id_tecnica}")
    public ResponseEntity<Void> eliminar(@PathVariable long id_tecnica) {
        tecnicaService.eliminar(id_tecnica);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/actualizar/{id_tecnica}")
    public ResponseEntity<Tecnica> actualizar(@PathVariable Long id_tecnica, @RequestBody Tecnica tecnica) {
        tecnica.setId_tecnica(id_tecnica);
        Tecnica tecnicaActualizado = tecnicaService.actualizar(id_tecnica, tecnica);
        return ResponseEntity.ok(tecnicaActualizado);
    }
}
