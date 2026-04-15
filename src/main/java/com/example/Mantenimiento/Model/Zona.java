package com.example.Mantenimiento.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Zona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_zona;

    private String nombre;

    @OneToMany(mappedBy = "zona", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<TiempoTaller> tiempoTalleres;

    @OneToMany(mappedBy = "zona_origen", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<TiempoTraslado> trasladosOrigen;

    @OneToMany(mappedBy = "zona_destino", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<TiempoTraslado> trasladosDestino;

    @OneToMany(mappedBy = "zona", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<SolicitudInspeccion> solicitudesInspeccion;
}
