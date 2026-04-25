package com.example.Mantenimiento.Controller;

import com.example.Mantenimiento.DTO.EquipoDTO;
import com.example.Mantenimiento.Service.EquipoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/equipo")
public class EquipoController {

    private final EquipoService equipoService;

    public EquipoController(EquipoService equipoService) {
        this.equipoService = equipoService;
    }

    // 🔥 GUARDAR
    @PostMapping("/guardar")
    public ResponseEntity<EquipoDTO> guardar(@RequestBody EquipoDTO dto) {
        return ResponseEntity.ok(equipoService.guardar(dto));
    }

    // 🔥 LISTAR
    @GetMapping("/listar")
    public ResponseEntity<List<EquipoDTO>> listar() {
        return ResponseEntity.ok(equipoService.listar());
    }

    // 🔥 LISTAR POR ID
    @GetMapping("/listar/{id_equipo}")
    public ResponseEntity<EquipoDTO> obtenerPorId(@PathVariable Long id_equipo) {
        try {
            return ResponseEntity.ok(equipoService.listarPorId(id_equipo));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    // 🔥 ELIMINAR
    @DeleteMapping("/eliminar/{id_equipo}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id_equipo) {
        equipoService.eliminar(id_equipo);
        return ResponseEntity.noContent().build();
    }

    // 🔥 ACTUALIZAR
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<EquipoDTO> actualizar(
            @PathVariable Long id,
            @RequestBody EquipoDTO dto
    ) {
        return ResponseEntity.ok(equipoService.actualizar(id, dto));
    }
}
