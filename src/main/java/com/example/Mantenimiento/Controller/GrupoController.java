package com.example.Mantenimiento.Controller;

import com.example.Mantenimiento.Model.Grupo;
import com.example.Mantenimiento.Model.TipoTurno;
import com.example.Mantenimiento.Repository.GrupoRepository;
import com.example.Mantenimiento.Service.GrupoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/grupo")
public class GrupoController {
    private final GrupoService grupoService;
    private final GrupoRepository grupoRepository;

    public GrupoController(GrupoService grupoService, GrupoRepository grupoRepository) {
        this.grupoService = grupoService;
        this.grupoRepository = grupoRepository;
    }

    @PostMapping("/guardar")
    public ResponseEntity<Grupo> guardar(@RequestBody Grupo grupo) {
        return ResponseEntity.ok(grupoService.guardar(grupo));
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Grupo>> listar() {
        return ResponseEntity.ok(grupoService.listar());
    }

    @GetMapping("/listar/{id_grupo}")
    public ResponseEntity<Grupo> obtenerPorId(@PathVariable long id_grupo) {
        return grupoService.listarPorId(id_grupo)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/eliminar/{id_grupo}")
    public ResponseEntity<Void> eliminar(@PathVariable long id_grupo) {
        grupoService.eliminar(id_grupo);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/actualizar/{id_grupo}")
    public ResponseEntity<Grupo> actualizar(@PathVariable Long id_grupo, @RequestBody Grupo grupo) {
        grupo.setId_grupo(id_grupo);
        Grupo grupoActualizado = grupoService.actualizar(id_grupo, grupo);
        return ResponseEntity.ok(grupoActualizado);
    }
}
