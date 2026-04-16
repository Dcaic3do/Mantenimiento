package com.example.Mantenimiento.Controller;

import com.example.Mantenimiento.Model.Flota;
import com.example.Mantenimiento.Model.TecnicoTecnica;
import com.example.Mantenimiento.Repository.TecnicoTecnicaRepository;
import com.example.Mantenimiento.Service.TecnicoTecnicaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tecnicoTecnica")
public class TecnicoTecnicaController {
    private final TecnicoTecnicaService tecnicoTecnicaService;
    private final TecnicoTecnicaRepository tecnicoTecnicaRepository;

    public TecnicoTecnicaController(TecnicoTecnicaService tecnicoTecnicaService, TecnicoTecnicaRepository tecnicoTecnicaRepository) {
        this.tecnicoTecnicaService = tecnicoTecnicaService;
        this.tecnicoTecnicaRepository = tecnicoTecnicaRepository;
    }

    @PostMapping("/guardar")
    public ResponseEntity<TecnicoTecnica> guardar(@RequestBody TecnicoTecnica tecnicoTecnica) {
        return ResponseEntity.ok(tecnicoTecnicaService.guardar(tecnicoTecnica));
    }

    @GetMapping("/listar")
    public ResponseEntity<List<TecnicoTecnica>> listar() {
        return ResponseEntity.ok(tecnicoTecnicaService.listar());
    }

    @GetMapping("/listar/{id_tecnicoTecnica}")
    public ResponseEntity<TecnicoTecnica> obtenerPorId(@PathVariable long id_tecnicoTecnica) {
        return tecnicoTecnicaService.listarPorId(id_tecnicoTecnica)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/eliminar/{id_tecnicoTecnica}")
    public ResponseEntity<Void> eliminar(@PathVariable long id_tecnicoTecnica) {
        tecnicoTecnicaService.eliminar(id_tecnicoTecnica);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/actualizar/{id_tecnicoTecnica}")
    public ResponseEntity<TecnicoTecnica> actualizar(@PathVariable Long id_tecnicoTecnica, @RequestBody TecnicoTecnica tecnicoTecnica) {
        tecnicoTecnica.setId_tecnicoTecnica(id_tecnicoTecnica);
        TecnicoTecnica tecnicoTecnicaActualizado = tecnicoTecnicaService.actualizar(id_tecnicoTecnica, tecnicoTecnica);
        return ResponseEntity.ok(tecnicoTecnicaActualizado);
    }
}
