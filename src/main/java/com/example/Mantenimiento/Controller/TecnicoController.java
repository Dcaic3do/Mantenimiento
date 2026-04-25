package com.example.Mantenimiento.Controller;

import com.example.Mantenimiento.DTO.TecnicoDTO;
import com.example.Mantenimiento.Model.Tecnico;
import com.example.Mantenimiento.Service.TecnicoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tecnico")
public class TecnicoController {

    private final TecnicoService tecnicoService;

    public TecnicoController(TecnicoService tecnicoService) {
        this.tecnicoService = tecnicoService;
    }

    @PostMapping("/guardar")
    public ResponseEntity<TecnicoDTO> guardar(@RequestBody Tecnico tecnico) {
        return ResponseEntity.ok(tecnicoService.guardar(tecnico));
    }

    @GetMapping("/listar")
    public ResponseEntity<List<TecnicoDTO>> listar() {
        return ResponseEntity.ok(tecnicoService.listar());
    }

    @GetMapping("/listar/{id_tecnico}")
    public ResponseEntity<TecnicoDTO> obtenerPorId(@PathVariable long id_tecnico) {
        return ResponseEntity.ok(tecnicoService.listarPorId(id_tecnico));
    }

    @DeleteMapping("/eliminar/{id_tecnico}")
    public ResponseEntity<Void> eliminar(@PathVariable long id_tecnico) {
        tecnicoService.eliminar(id_tecnico);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/actualizar/{id_tecnico}")
    public ResponseEntity<TecnicoDTO> actualizar(
            @PathVariable Long id_tecnico,
            @RequestBody Tecnico tecnico
    ) {
        return ResponseEntity.ok(tecnicoService.actualizar(id_tecnico, tecnico));
    }
}
