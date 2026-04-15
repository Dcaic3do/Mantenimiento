package com.example.Mantenimiento.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Time;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class TipoTurno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_tipoTurno;

    private String tipo;
    private Time hora_inicio;
    private Time hora_fin;

    @OneToMany(mappedBy = "tipo_turno", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Turno> turnos;
}
