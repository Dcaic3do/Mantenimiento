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
public class Flota {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id_flota;

    private String nombre;

    @OneToMany(mappedBy = "flota", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Equipo> equipos;
}