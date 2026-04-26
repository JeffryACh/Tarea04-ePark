package com.epark.domain.model;

public class Usuario {
    private final String idUsuario;
    private final String nombreCompleto;
    private final String correo;

    public Usuario(String idUsuario, String nombreCompleto, String correo) {
        this.idUsuario = idUsuario;
        this.nombreCompleto = nombreCompleto;
        this.correo = correo;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public String getCorreo() {
        return correo;
    }
}
