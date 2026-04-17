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
    @JoinColumn(name = "idZonaOrigen", nullable = false)
    private Zona zonaOrigen;

    @ManyToOne
    @JoinColumn(name = "idZonaDestino", nullable = false)
    private Zona zonaDestino;

    private int minutos;
}
