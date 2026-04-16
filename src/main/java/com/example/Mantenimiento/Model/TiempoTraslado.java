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
public class TiempoTraslado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_tiempoTraslado;

    @ManyToOne
    @JoinColumn(name = "id_zona_origen", nullable = false)
    private Zona zona_origen;

    @ManyToOne
    @JoinColumn(name = "id_zona_destino", nullable = false)
    private Zona zona_destino;

    private int minutos;
}
