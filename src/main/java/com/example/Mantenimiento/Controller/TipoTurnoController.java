package com.example.Mantenimiento.Controller;

import com.example.Mantenimiento.Model.TipoTurno;
import com.example.Mantenimiento.Repository.TipoTurnoRepository;
import com.example.Mantenimiento.Service.TipoTurnoService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tipoTurno")
public class TipoTurnoController {
    private final TipoTurnoService tipoTurnoService;
    private final TipoTurnoRepository tipoTurnoRepository;

    public TipoTurnoController(TipoTurnoService tipoTurnoService, TipoTurnoRepository tipoTurnoRepository) {
        this.tipoTurnoService = tipoTurnoService;
        this.tipoTurnoRepository = tipoTurnoRepository;
    }

    @PostMapping("/guardar")
    public ResponseEntity<TipoTurno> guardar(@RequestBody TipoTurno tipoTurno) {
        return ResponseEntity.ok(tipoTurnoService.guardar(tipoTurno));
    }

    @GetMapping("/listar")
    public ResponseEntity<List<TipoTurno>> listar() {
        return ResponseEntity.ok(tipoTurnoService.listar());
    }

    @GetMapping("/listar/{id_tipoTurno}")
    public ResponseEntity<TipoTurno> obtenerPorId(@PathVariable long id_tipoTurno) {
        return tipoTurnoService.listarPorId(id_tipoTurno)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/eliminar/{id_tipoTurno}")
    public ResponseEntity<Void> eliminar(@PathVariable long id_tipoTurno) {
        tipoTurnoService.eliminar(id_tipoTurno);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/actualizar/{id_tipoTurno}")
    public ResponseEntity<TipoTurno> actualizar(@PathVariable Long id_tipoTurno, @RequestBody TipoTurno tipoTurno) {
        tipoTurno.setId_tipoTurno(id_tipoTurno);
        TipoTurno tipoTurnoActualizado = tipoTurnoService.actualizar(id_tipoTurno, tipoTurno);
        return ResponseEntity.ok(tipoTurnoActualizado);
    }
}
