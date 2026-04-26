package com.epark.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Pago {
    private final String idPago;
    private final String idEstadia;
    private final String idUsuario;
    private final String idTarjeta;
    private final BigDecimal monto;
    private final LocalDateTime fecha;

    public Pago(String idPago, String idEstadia, String idUsuario, String idTarjeta, BigDecimal monto, LocalDateTime fecha) {
        this.idPago = idPago;
        this.idEstadia = idEstadia;
        this.idUsuario = idUsuario;
        this.idTarjeta = idTarjeta;
        this.monto = monto;
        this.fecha = fecha;
    }

    public String getIdPago() {
        return idPago;
    }

    public String getIdEstadia() {
        return idEstadia;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public String getIdTarjeta() {
        return idTarjeta;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    @Override
    public String toString() {
        return "Pago{idPago='" + idPago + "', idEstadia='" + idEstadia + "', monto=" + monto + ", fecha=" + fecha + "}";
    }
}
