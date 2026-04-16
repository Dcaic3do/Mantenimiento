package com.example.Mantenimiento.Controller;

import com.example.Mantenimiento.Model.Tarea;
import com.example.Mantenimiento.Repository.TareaRepository;
import com.example.Mantenimiento.Service.TareaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tarea")
public class TareaController {
    private final TareaService tareaService;
    private final TareaRepository tareaRepository;

    public TareaController(TareaService tareaService, TareaRepository tareaRepository) {
        this.tareaService = tareaService;
        this.tareaRepository = tareaRepository;
    }

    @PostMapping("/guardar")
    public ResponseEntity<Tarea> guardar(@RequestBody Tarea tarea) {
        return ResponseEntity.ok(tareaService.guardar(tarea));
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Tarea>> listar() {
        return ResponseEntity.ok(tareaService.listar());
    }

    @GetMapping("/listar/{id_tarea}")
    public ResponseEntity<Tarea> obtenerPorId(@PathVariable long id_tarea) {
        return tareaService.listarPorId(id_tarea)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/eliminar/{id_tarea}")
    public ResponseEntity<Void> eliminar(@PathVariable long id_tarea) {
        tareaService.eliminar(id_tarea);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/actualizar/{id_tarea}")
    public ResponseEntity<Tarea> actualizar(@PathVariable Long id_tarea, @RequestBody Tarea tarea) {
        tarea.setId_tarea(id_tarea);
        Tarea tareaActualizado = tareaService.actualizar(id_tarea, tarea);
        return ResponseEntity.ok(tareaActualizado);
    }
}
