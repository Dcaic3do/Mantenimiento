package com.example.Mantenimiento.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Time;
import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Asignacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id_asignacion;

    @ManyToOne
    @JoinColumn(name = "id_solicitud", nullable = false)
    @JsonIgnore
    private SolicitudInspeccion solicitud;

    @ManyToOne
    @JoinColumn(name = "id_tecnico_lider", nullable = false)
    @JsonIgnore
    private Tecnico tecnico_lider;

    @ManyToOne
    @JoinColumn(name = "id_tecnico_acompanante", nullable = true)
    @JsonIgnore
    private Tecnico tecnico_acompanante;

    @ManyToOne
    @JoinColumn(name = "id_turno", nullable = false)
    @JsonIgnore
    private Turno turno;

    private Time hora_inicio_programada;
    private Time hora_fin_programada;
    private float tiempoTrasladoMin;
    private String estado;
}
