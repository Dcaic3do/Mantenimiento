package com.example.Mantenimiento.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class TiempoTaller {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id_tiempoTaller;

    @ManyToOne
    @JoinColumn(name = "id_zona", nullable = false)
    private Zona zona;

    private Long minutos_desde_taller;
}
