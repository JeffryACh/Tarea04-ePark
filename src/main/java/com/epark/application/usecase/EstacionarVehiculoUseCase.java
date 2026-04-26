package com.epark.application.usecase;

import com.epark.domain.model.Estadia;
import com.epark.domain.model.Pago;
import com.epark.domain.model.Usuario;
import com.epark.domain.model.Vehiculo;
import com.epark.domain.ports.Repositorio;
import com.epark.domain.ports.Servicios;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class EstacionarVehiculoUseCase {
    private final Repositorio repositorio;
    private final Servicios servicios;

    public EstacionarVehiculoUseCase(Repositorio repositorio, Servicios servicios) {
        this.repositorio = Objects.requireNonNull(repositorio, "Repositorio es obligatorio");
        this.servicios = Objects.requireNonNull(servicios, "Servicios es obligatorio");
    }

    public Estadia ejecutar(Usuario usuario, Vehiculo vehiculo, int minutos, String idTarjeta) {
        Objects.requireNonNull(usuario, "Usuario es obligatorio");
        Objects.requireNonNull(vehiculo, "Vehiculo es obligatorio");
        Objects.requireNonNull(idTarjeta, "Id de tarjeta es obligatorio");
        if (minutos <= 0) {
            throw new IllegalArgumentException("Los minutos deben ser mayores que cero");
        }

        BigDecimal monto = BigDecimal.valueOf(minutos).multiply(BigDecimal.valueOf(0.50));
        LocalDateTime ahora = LocalDateTime.now();
        Estadia estadia = new Estadia(
                UUID.randomUUID().toString(),
                usuario,
                vehiculo,
                minutos,
                monto,
                ahora,
                "ACTIVA"
        );

        Servicios.CobroResultado resultadoCobro = servicios.cobrar(usuario.getIdUsuario(), idTarjeta, monto);
        if (!resultadoCobro.isAprobado()) {
            estadia.setEstado("CANCELADA");
            repositorio.guardarEstadia(estadia);
            return estadia;
        }

        Pago pago = new Pago(
                UUID.randomUUID().toString(),
                estadia.getIdEstadia(),
                usuario.getIdUsuario(),
                idTarjeta,
                monto,
                ahora
        );
        repositorio.guardarPago(pago);
        repositorio.guardarEstadia(estadia);
        return estadia;
    }
}
