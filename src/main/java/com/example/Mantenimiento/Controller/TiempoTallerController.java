package com.example.Mantenimiento.Controller;

import com.example.Mantenimiento.Model.Flota;
import com.example.Mantenimiento.Model.TiempoTaller;
import com.example.Mantenimiento.Repository.TiempoTallerRepository;
import com.example.Mantenimiento.Service.TiempoTallerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tiempoTaller")
public class TiempoTallerController {
    private final TiempoTallerService tiempoTallerService;
    private final TiempoTallerRepository tiempoTallerRepository;

    public TiempoTallerController(TiempoTallerService tiempoTallerService, TiempoTallerRepository tiempoTallerRepository) {
        this.tiempoTallerService = tiempoTallerService;
        this.tiempoTallerRepository = tiempoTallerRepository;
    }

    @PostMapping("/guardar")
    public ResponseEntity<TiempoTaller> guardar(@RequestBody TiempoTaller tiempoTaller) {
        return ResponseEntity.ok(tiempoTallerService.guardar(tiempoTaller));
    }

    @GetMapping("/listar")
    public ResponseEntity<List<TiempoTaller>> listar() {
        return ResponseEntity.ok(tiempoTallerService.listar());
    }

    @GetMapping("/listar/{id_tiempoTaller}")
    public ResponseEntity<TiempoTaller> obtenerPorId(@PathVariable long id_tiempoTaller) {
        return tiempoTallerService.listarPorId(id_tiempoTaller)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/eliminar/{id_tiempoTaller}")
    public ResponseEntity<Void> eliminar(@PathVariable long id_tiempoTaller) {
        tiempoTallerService.eliminar(id_tiempoTaller);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/actualizar/{id_tiempoTaller}")
    public ResponseEntity<TiempoTaller> actualizar(@PathVariable Long id_tiempoTaller, @RequestBody TiempoTaller tiempoTaller) {
        tiempoTaller.setId_tiempoTaller(id_tiempoTaller);
        TiempoTaller tiempoTallerActualizado = tiempoTallerService.actualizar(id_tiempoTaller, tiempoTaller);
        return ResponseEntity.ok(tiempoTallerActualizado);
    }
}
