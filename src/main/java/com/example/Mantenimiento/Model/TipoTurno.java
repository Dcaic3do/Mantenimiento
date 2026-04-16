package com.example.Mantenimiento.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Time;
import java.time.LocalTime;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Data
public class TipoTurno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_tipoTurno;

    private String tipo;
    private LocalTime horaInicio;
    private LocalTime horaFin;

    @OneToMany(mappedBy = "tipo_turno", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Turno> turnos;
}
