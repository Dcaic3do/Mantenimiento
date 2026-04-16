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
public class Tecnica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id_tecnica;

    private String nombre;
    private String descripcion;

    @OneToMany(mappedBy = "tecnica", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<SolicitudInspeccion> solicitudesInspeccion;

    @OneToMany(mappedBy = "tecnica", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<TecnicoTecnica> tecnicoTecnicas;
}
