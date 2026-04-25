package com.example.Mantenimiento.Repository;

import com.example.Mantenimiento.Model.Flota;
import com.example.Mantenimiento.Model.TiempoTraslado;
import com.example.Mantenimiento.Model.Zona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TiempoTrasladoRepository extends JpaRepository<TiempoTraslado, Long> {
    // Busca el tiempo exacto entre la ubicación actual del técnico y la nueva solicitud
    Optional<TiempoTraslado> findByZonaOrigenAndZonaDestino(Zona origen, Zona destino);
}
