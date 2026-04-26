package com.epark.domain.ports;

import java.math.BigDecimal;

public interface Servicios {
    CobroResultado cobrar(String idUsuario, String idTarjeta, BigDecimal monto);
    void enviarNotificacion(String idUsuario, String mensaje);

    final class CobroResultado {
        private final boolean aprobado;
        private final String referencia;

        public CobroResultado(boolean aprobado, String referencia) {
            this.aprobado = aprobado;
            this.referencia = referencia;
        }

        public boolean isAprobado() {
            return aprobado;
        }

        public String getReferencia() {
            return referencia;
        }
    }
}
