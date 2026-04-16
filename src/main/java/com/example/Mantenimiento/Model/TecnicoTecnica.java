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
public class TecnicoTecnica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_tecnicoTecnica;

    @ManyToOne
    @JoinColumn(name = "id_tecnico", nullable = false)
    private Tecnico tecnico;

    @ManyToOne
    @JoinColumn(name = "id_tecnica", nullable = false)
    private Tecnica tecnica;
}
