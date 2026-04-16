package com.example.Mantenimiento.Controller;

import com.example.Mantenimiento.Model.Tarea;
import com.example.Mantenimiento.Model.Zona;
import com.example.Mantenimiento.Repository.ZonaRepository;
import com.example.Mantenimiento.Service.ZonaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/zona")
public class ZonaController {
    private final ZonaService zonaService;
    private final ZonaRepository zonaRepository;

    public ZonaController(ZonaService zonaService, ZonaRepository zonaRepository) {
        this.zonaService = zonaService;
        this.zonaRepository = zonaRepository;
    }

    @PostMapping("/guardar")
    public ResponseEntity<Zona> guardar(@RequestBody Zona zona) {
        return ResponseEntity.ok(zonaService.guardar(zona));
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Zona>> listar() {
        return ResponseEntity.ok(zonaService.listar());
    }

    @GetMapping("/listar/{id_zona}")
    public ResponseEntity<Zona> obtenerPorId(@PathVariable long id_zona) {
        return zonaService.listarPorId(id_zona)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/eliminar/{id_zona}")
    public ResponseEntity<Void> eliminar(@PathVariable long id_zona) {
        zonaService.eliminar(id_zona);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/actualizar/{id_zona}")
    public ResponseEntity<Zona> actualizar(@PathVariable Long id_zona, @RequestBody Zona zona) {
        zona.setId_zona(id_zona);
        Zona zonaActualizado = zonaService.actualizar(id_zona, zona);
        return ResponseEntity.ok(zonaActualizado);
    }
}
