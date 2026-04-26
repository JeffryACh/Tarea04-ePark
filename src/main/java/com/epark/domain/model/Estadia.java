package com.epark.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Estadia {
    private final String idEstadia;
    private final Usuario usuario;
    private final Vehiculo vehiculo;
    private final int minutos;
    private final BigDecimal monto;
    private final LocalDateTime horaInicio;
    private String estado;

    public Estadia(String idEstadia, Usuario usuario, Vehiculo vehiculo, int minutos, BigDecimal monto, LocalDateTime horaInicio, String estado) {
        this.idEstadia = idEstadia;
        this.usuario = usuario;
        this.vehiculo = vehiculo;
        this.minutos = minutos;
        this.monto = monto;
        this.horaInicio = horaInicio;
        this.estado = estado;
    }

    public String getIdEstadia() {
        return idEstadia;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public int getMinutos() {
        return minutos;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public LocalDateTime getHoraInicio() {
        return horaInicio;
    }

    public LocalDateTime getHoraFin() {
        return horaInicio.plusMinutes(minutos);
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Estadia{idEstadia='" + idEstadia + "', usuario=" + usuario.getNombreCompleto() + ", vehiculo=" + vehiculo.getPlaca() + ", minutos=" + minutos + ", monto=" + monto + ", estado='" + estado + "'}";
    }
}
