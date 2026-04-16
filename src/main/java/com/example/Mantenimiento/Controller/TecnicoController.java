package com.example.Mantenimiento.Controller;

import com.example.Mantenimiento.Model.Flota;
import com.example.Mantenimiento.Model.Tecnico;
import com.example.Mantenimiento.Repository.TecnicoRepository;
import com.example.Mantenimiento.Service.TecnicoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tecnico")
public class TecnicoController {
    private final TecnicoService tecnicoService;
    private final TecnicoRepository tecnicoRepository;

    public TecnicoController(TecnicoService tecnicoService, TecnicoRepository tecnicoRepository) {
        this.tecnicoService = tecnicoService;
        this.tecnicoRepository = tecnicoRepository;
    }

    @PostMapping("/guardar")
    public ResponseEntity<Tecnico> guardar(@RequestBody Tecnico tecnico) {
        return ResponseEntity.ok(tecnicoService.guardar(tecnico));
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Tecnico>> listar() {
        return ResponseEntity.ok(tecnicoService.listar());
    }

    @GetMapping("/listar/{id_tecnico}")
    public ResponseEntity<Tecnico> obtenerPorId(@PathVariable long id_tecnico) {
        return tecnicoService.listarPorId(id_tecnico)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/eliminar/{id_tecnico}")
    public ResponseEntity<Void> eliminar(@PathVariable long id_tecnico) {
        tecnicoService.eliminar(id_tecnico);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/actualizar/{id_tecnico}")
    public ResponseEntity<Tecnico> actualizar(@PathVariable Long id_tecnico, @RequestBody Tecnico tecnico) {
        tecnico.setId_tecnico(id_tecnico);
        Tecnico tecnicoActualizado = tecnicoService.actualizar(id_tecnico, tecnico);
        return ResponseEntity.ok(tecnicoActualizado);
    }
}
