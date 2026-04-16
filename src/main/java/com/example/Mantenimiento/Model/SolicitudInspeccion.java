package com.example.Mantenimiento.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class SolicitudInspeccion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id_solicitudInspeccion;

    private String ot;

    @ManyToOne
    @JoinColumn(name = "id_equipo", nullable = false)
    @JsonIgnore
    private Equipo equipo;

    @ManyToOne
    @JoinColumn(name = "id_flota", nullable = false)
    @JsonIgnore
    private Flota flota;

    @ManyToOne
    @JoinColumn(name = "id_tarea", nullable = false)
    @JsonIgnore
    private Tarea tarea;

    @ManyToOne
    @JoinColumn(name = "id_tecnica", nullable = false)
    @JsonIgnore
    private Tecnica tecnica;

    @ManyToOne
    @JoinColumn(name = "id_zona", nullable = false)
    @JsonIgnore
    private Zona zona;

    private float duracionEstimadaHrs;
    private Date fechaSolicitada;
    private String estado;

    @OneToMany(mappedBy = "solicitud", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Asignacion> asignaciones;
}
