package com.epark.application.usecase;

import com.epark.domain.model.Pago;
import com.epark.domain.ports.Repositorio;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class ConsultarPagosPorTarjetaUseCase {
    private final Repositorio repositorio;

    public ConsultarPagosPorTarjetaUseCase(Repositorio repositorio) {
        this.repositorio = Objects.requireNonNull(repositorio, "Repositorio es obligatorio");
    }

    public List<Pago> ejecutar(String idTarjeta, LocalDate fecha) {
        Objects.requireNonNull(idTarjeta, "Id de tarjeta es obligatorio");
        Objects.requireNonNull(fecha, "Fecha es obligatoria");
        return repositorio.buscarPagosPorTarjetaYFecha(idTarjeta, fecha);
    }
}
