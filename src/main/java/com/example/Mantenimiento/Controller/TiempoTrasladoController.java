package com.example.Mantenimiento.Controller;

import com.example.Mantenimiento.Model.TiempoTraslado;
import com.example.Mantenimiento.Repository.TiempoTrasladoRepository;
import com.example.Mantenimiento.Service.TiempoTrasladoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tiempoTraslado")
public class TiempoTrasladoController {
    private final TiempoTrasladoService tiempoTrasladoService;
    private final TiempoTrasladoRepository tiempoTrasladoRepository;

    public TiempoTrasladoController(TiempoTrasladoService tiempoTrasladoService, TiempoTrasladoRepository tiempoTrasladoRepository) {
        this.tiempoTrasladoService = tiempoTrasladoService;
        this.tiempoTrasladoRepository = tiempoTrasladoRepository;
    }

    @PostMapping("/guardar")
    public ResponseEntity<TiempoTraslado> guardar(@RequestBody TiempoTraslado tiempoTraslado) {
        return ResponseEntity.ok(tiempoTrasladoService.guardar(tiempoTraslado));
    }

    @GetMapping("/listar")
    public ResponseEntity<List<TiempoTraslado>> listar() {
        return ResponseEntity.ok(tiempoTrasladoService.listar());
    }

    @GetMapping("/listar/{id_tiempoTraslado}")
    public ResponseEntity<TiempoTraslado> obtenerPorId(@PathVariable long id_tiempoTraslado) {
        return tiempoTrasladoService.listarPorId(id_tiempoTraslado)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/eliminar/{id_tiempoTraslado}")
    public ResponseEntity<Void> eliminar(@PathVariable long id_tiempoTraslado) {
        tiempoTrasladoService.eliminar(id_tiempoTraslado);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/actualizar/{id_tiempoTraslado}")
    public ResponseEntity<TiempoTraslado> actualizar(@PathVariable Long id_tiempoTraslado, @RequestBody TiempoTraslado tiempoTraslado) {
        tiempoTraslado.setId_tiempoTraslado(id_tiempoTraslado);
        TiempoTraslado tiempoTrasladoActualizado = tiempoTrasladoService.actualizar(id_tiempoTraslado, tiempoTraslado);
        return ResponseEntity.ok(tiempoTrasladoActualizado);
    }
}
