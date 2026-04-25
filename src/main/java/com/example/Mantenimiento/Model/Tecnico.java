package com.example.Mantenimiento.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Tecnico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_tecnico;

    private String nombre;

    @ManyToOne
    @JoinColumn(name = "id_grupo", nullable = false)
    private Grupo grupo;

    private boolean activo;

    @OneToMany(mappedBy = "tecnico", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<TecnicoTecnica> tecnicoTecnicas;

    @OneToMany(mappedBy = "tecnico_lider", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Asignacion> tecnicoLider;

    @OneToMany(mappedBy = "tecnico_acompanante", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Asignacion> tecnicoAcompanante;

    // En Tecnico.java

    /**
     * Une las listas de asignaciones donde el técnico es líder y donde es acompañante.
     * Esto es vital para calcular su ubicación actual y su carga horaria total.
     */
    public List<Asignacion> getTodasMisAsignaciones() {
        List<Asignacion> todas = new ArrayList<>();

        if (this.tecnicoLider != null) {
            todas.addAll(this.tecnicoLider);
        }

        if (this.tecnicoAcompanante != null) {
            todas.addAll(this.tecnicoAcompanante);
        }

        return todas;
    }
}
