package com.epark.application.usecase;

import com.epark.domain.model.Estadia;
import com.epark.domain.ports.Repositorio;
import com.epark.domain.ports.Servicios;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class NotificarProximoVencimientoUseCase {
    private final Repositorio repositorio;
    private final Servicios servicios;

    public NotificarProximoVencimientoUseCase(Repositorio repositorio, Servicios servicios) {
        this.repositorio = Objects.requireNonNull(repositorio, "Repositorio es obligatorio");
        this.servicios = Objects.requireNonNull(servicios, "Servicios es obligatorio");
    }

    public int ejecutar(long minutosAnticipacion) {
        if (minutosAnticipacion <= 0) {
            throw new IllegalArgumentException("minutosAnticipacion debe ser mayor que cero");
        }

        LocalDateTime ahora = LocalDateTime.now();
        List<Estadia> proximas = repositorio.buscarEstadiasProximasAVencer(ahora, minutosAnticipacion);
        int totalEnviadas = 0;

        for (Estadia estadia : proximas) {
            long minutosRestantes = java.time.Duration.between(ahora, estadia.getHoraFin()).toMinutes();
            if (minutosRestantes < 1) {
                minutosRestantes = 1;
            }
            String mensaje = "Tu parqueo vence en " + minutosRestantes + " minutos. Estadia: " + estadia.getIdEstadia();
            servicios.enviarNotificacion(estadia.getUsuario().getIdUsuario(), mensaje);
            totalEnviadas++;
        }

        return totalEnviadas;
    }
}
