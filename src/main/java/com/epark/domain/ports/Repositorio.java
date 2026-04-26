package com.epark.domain.ports;

import com.epark.domain.model.Estadia;
import com.epark.domain.model.Pago;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface Repositorio {
    void guardarEstadia(Estadia estadia);
    void guardarPago(Pago pago);
    List<Pago> buscarPagosPorTarjetaYFecha(String idTarjeta, LocalDate fecha);
    List<Estadia> buscarEstadiasProximasAVencer(LocalDateTime ahora, long minutosAnticipacion);
}
