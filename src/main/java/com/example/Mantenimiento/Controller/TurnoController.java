package com.example.Mantenimiento.Controller;

import com.example.Mantenimiento.Model.Flota;
import com.example.Mantenimiento.Model.Turno;
import com.example.Mantenimiento.Repository.TurnoRepository;
import com.example.Mantenimiento.Service.TurnoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/turno")
public class TurnoController {
    private final TurnoService turnoService;
    private final TurnoRepository turnoRepository;

    public TurnoController(TurnoService turnoService, TurnoRepository turnoRepository) {
        this.turnoService = turnoService;
        this.turnoRepository = turnoRepository;
    }

    @PostMapping("/guardar")
    public ResponseEntity<Turno> guardar(@RequestBody Turno turno) {
        return ResponseEntity.ok(turnoService.guardar(turno));
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Turno>> listar() {
        return ResponseEntity.ok(turnoService.listar());
    }

    @GetMapping("/listar/{id_turno}")
    public ResponseEntity<Turno> obtenerPorId(@PathVariable long id_turno) {
        return turnoService.listarPorId(id_turno)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/eliminar/{id_turno}")
    public ResponseEntity<Void> eliminar(@PathVariable long id_turno) {
        turnoService.eliminar(id_turno);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/actualizar/{id_turno}")
    public ResponseEntity<Turno> actualizar(@PathVariable Long id_turno, @RequestBody Turno turno) {
        turno.setId_turno(id_turno);
        Turno turnoActualizado = turnoService.actualizar(id_turno, turno);
        return ResponseEntity.ok(turnoActualizado);
    }
}
