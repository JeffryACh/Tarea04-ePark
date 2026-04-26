package com.epark.domain.model;

public class Vehiculo {
    private final String idVehiculo;
    private final String tipo;
    private final String placa;

    public Vehiculo(String idVehiculo, String tipo, String placa) {
        this.idVehiculo = idVehiculo;
        this.tipo = tipo;
        this.placa = placa;
    }

    public String getIdVehiculo() {
        return idVehiculo;
    }

    public String getTipo() {
        return tipo;
    }

    public String getPlaca() {
        return placa;
    }
}
