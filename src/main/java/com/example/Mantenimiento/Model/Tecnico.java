package com.example.Mantenimiento.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

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
}
