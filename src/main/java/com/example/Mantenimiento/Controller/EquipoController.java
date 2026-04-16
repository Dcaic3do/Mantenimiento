package com.example.Mantenimiento.Controller;

import com.example.Mantenimiento.Model.Equipo;
import com.example.Mantenimiento.Repository.EquipoRepository;
import com.example.Mantenimiento.Service.EquipoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/equipo")
public class EquipoController {
    private final EquipoService equipoService;
    private final EquipoRepository equipoRepository;

    public EquipoController(EquipoService equipoService, EquipoRepository equipoRepository) {
        this.equipoService = equipoService;
        this.equipoRepository = equipoRepository;
    }

    @PostMapping("/guardar")
    public ResponseEntity<Equipo> guardar(@RequestBody Equipo equipo) {
        return ResponseEntity.ok(equipoService.guardar(equipo));
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Equipo>> listar() {
        return ResponseEntity.ok(equipoService.listar());
    }

    @GetMapping("/listar/{id_equipo}")
    public ResponseEntity<Equipo> obtenerPorId(@PathVariable long id_equipo) {
        return equipoService.listarPorId(id_equipo)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/eliminar/{id_equipo}")
    public ResponseEntity<Void> eliminar(@PathVariable long id_equipo) {
        equipoService.eliminar(id_equipo);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/actualizar/{id_equipo}")
    public ResponseEntity<Equipo> actualizar(@PathVariable Long id_equipo, @RequestBody Equipo equipo) {
        equipo.setId_equipo(id_equipo);
        Equipo equipoActualizado = equipoService.actualizar(id_equipo, equipo);
        return ResponseEntity.ok(equipoActualizado);
    }
}
