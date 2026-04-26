package com.epark.app;

import com.epark.application.usecase.ConsultarPagosPorTarjetaUseCase;
import com.epark.application.usecase.EstacionarVehiculoUseCase;
import com.epark.application.usecase.NotificarProximoVencimientoUseCase;
import com.epark.domain.model.Estadia;
import com.epark.domain.model.Pago;
import com.epark.domain.model.Usuario;
import com.epark.domain.model.Vehiculo;
import com.epark.domain.ports.Repositorio;
import com.epark.domain.ports.Servicios;
import com.epark.domain.ports.Servicios.CobroResultado;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Repositorio repositorio = new RepositorioEnMemoria();
        Servicios servicios = new ServiciosSimulados();

        EstacionarVehiculoUseCase estacionarVehiculoUseCase = new EstacionarVehiculoUseCase(repositorio, servicios);
        ConsultarPagosPorTarjetaUseCase consultarPagosPorTarjetaUseCase = new ConsultarPagosPorTarjetaUseCase(repositorio);
        NotificarProximoVencimientoUseCase notificarProximoVencimientoUseCase = new NotificarProximoVencimientoUseCase(repositorio, servicios);

        System.out.println("=== ePark - Reserva de estacionamiento ===");

        while (true) {
            System.out.println("\nSeleccione una opción:");
            System.out.println("1) Reservar estacionamiento");
            System.out.println("2) Consultar pagos por tarjeta");
            System.out.println("3) Notificar próximos vencimientos");
            System.out.println("4) Salir");

            int opcion = leerEntero(scanner, "Opción: ", 1, 4);
            if (opcion == 4) {
                System.out.println("Saliendo. Gracias por usar ePark.");
                break;
            }

            switch (opcion) {
                case 1:
                    realizarReserva(scanner, estacionarVehiculoUseCase, consultarPagosPorTarjetaUseCase);
                    break;
                case 2:
                    consultarPagos(scanner, consultarPagosPorTarjetaUseCase);
                    break;
                case 3:
                    notificarVencimientos(scanner, notificarProximoVencimientoUseCase);
                    break;
                default:
                    System.out.println("Opción no reconocida. Intente nuevamente.");
            }
        }
    }

    private static void realizarReserva(
            Scanner scanner,
            EstacionarVehiculoUseCase estacionarVehiculoUseCase,
            ConsultarPagosPorTarjetaUseCase consultarPagosPorTarjetaUseCase
    ) {
        imprimirLinea();
        System.out.println("Datos de usuario y vehículo para la reserva");
        String nombre = leerTexto(scanner, "Nombre completo: ");
        String correo = leerCorreo(scanner, "Correo: ");
        String tipoVehiculo = leerTexto(scanner, "Tipo de vehículo: ");
        String placa = leerTexto(scanner, "Placa: ");
        int minutos = leerEntero(scanner, "Minutos de estacionamiento: ", 1, Integer.MAX_VALUE);
        String numeroTarjeta = leerTarjeta(scanner, "Número de tarjeta: ");
        String cvv = leerCVV(scanner, "CVV: ");

        String idUsuario = "USR-" + UUID.randomUUID();
        String idVehiculo = "VEH-" + UUID.randomUUID();
        String idTarjeta = numeroTarjeta;

        Usuario usuario = new Usuario(idUsuario, nombre, correo);
        Vehiculo vehiculo = new Vehiculo(idVehiculo, tipoVehiculo, placa);

        Estadia estadia = estacionarVehiculoUseCase.ejecutar(usuario, vehiculo, minutos, idTarjeta);
        System.out.println("\nReserva registrada:");
        System.out.println(estadia);

        List<Pago> pagosHoy = consultarPagosPorTarjetaUseCase.ejecutar(idTarjeta, LocalDate.now());
        System.out.println("Pagos por tarjeta hoy: " + pagosHoy.size());
        System.out.println("Tarjeta usada: " + maskNumeroTarjeta(numeroTarjeta));
        System.out.println("CVV recibido: " + cvv.length() + " dígitos");
    }

    private static void consultarPagos(
            Scanner scanner,
            ConsultarPagosPorTarjetaUseCase consultarPagosPorTarjetaUseCase
    ) {
        imprimirLinea();
        System.out.println("Consultar pagos por tarjeta");
        String idTarjeta = leerTexto(scanner, "Id de tarjeta: ");
        LocalDate fecha = leerFecha(scanner, "Fecha (YYYY-MM-DD) [enter = hoy]: ", LocalDate.now());

        List<Pago> pagosHoy = consultarPagosPorTarjetaUseCase.ejecutar(idTarjeta, fecha);
        System.out.println("Pagos encontrados: " + pagosHoy.size());
        for (Pago pago : pagosHoy) {
            System.out.println(pago);
        }
    }

    private static void notificarVencimientos(
            Scanner scanner,
            NotificarProximoVencimientoUseCase notificarProximoVencimientoUseCase
    ) {
        imprimirLinea();
        System.out.println("Notificar próximos vencimientos");
        int minutos = leerEntero(scanner, "Minutos de anticipación: ", 1, Integer.MAX_VALUE);
        int enviadas = notificarProximoVencimientoUseCase.ejecutar(minutos);
        System.out.println("Notificaciones enviadas: " + enviadas);
    }

    private static String leerTexto(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String valor = scanner.nextLine().trim();
            if (!valor.isEmpty()) {
                return valor;
            }
            System.out.println("El valor no puede estar vacío. Intente de nuevo.");
        }
    }

    private static int leerEntero(Scanner scanner, String prompt, int min, int max) {
        while (true) {
            System.out.print(prompt);
            String linea = scanner.nextLine().trim();
            try {
                int valor = Integer.parseInt(linea);
                if (valor >= min && valor <= max) {
                    return valor;
                }
            } catch (NumberFormatException ignored) {
            }
            System.out.println("Ingrese un número válido entre " + min + " y " + max + ".");
        }
    }

    private static LocalDate leerFecha(Scanner scanner, String prompt, LocalDate defaultValue) {
        while (true) {
            System.out.print(prompt);
            String linea = scanner.nextLine().trim();
            if (linea.isEmpty()) {
                return defaultValue;
            }
            try {
                return LocalDate.parse(linea);
            } catch (Exception e) {
                System.out.println("Formato inválido. Use YYYY-MM-DD.");
            }
        }
    }

    private static String leerCorreo(Scanner scanner, String prompt) {
        while (true) {
            String correo = leerTexto(scanner, prompt);
            if (correo.contains("@") && correo.contains(".")) {
                return correo;
            }
            System.out.println("Correo inválido. Debe contener '@' y '.'.");
        }
    }

    private static String leerTarjeta(Scanner scanner, String prompt) {
        while (true) {
            String numero = leerTexto(scanner, prompt);
            if (numero.matches("\\d{13,19}")) {
                return numero;
            }
            System.out.println("Número de tarjeta inválido. Ingrese entre 13 y 19 dígitos.");
        }
    }

    private static String leerCVV(Scanner scanner, String prompt) {
        while (true) {
            String cvv = leerTexto(scanner, prompt);
            if (cvv.matches("\\d{3,4}")) {
                return cvv;
            }
            System.out.println("CVV inválido. Ingrese 3 o 4 dígitos.");
        }
    }

    private static String maskNumeroTarjeta(String numeroTarjeta) {
        if (numeroTarjeta.length() <= 4) {
            return numeroTarjeta;
        }
        return "**** **** **** " + numeroTarjeta.substring(numeroTarjeta.length() - 4);
    }

    private static void imprimirLinea() {
        System.out.println("--------------------------------------------------");
    }

    private static class RepositorioEnMemoria implements Repositorio {
        private final List<Estadia> estadias = new ArrayList<>();
        private final List<Pago> pagos = new ArrayList<>();

        @Override
        public void guardarEstadia(Estadia estadia) {
            estadias.removeIf(e -> e.getIdEstadia().equals(estadia.getIdEstadia()));
            estadias.add(estadia);
        }

        @Override
        public void guardarPago(Pago pago) {
            pagos.add(pago);
        }

        @Override
        public List<Pago> buscarPagosPorTarjetaYFecha(String idTarjeta, LocalDate fecha) {
            List<Pago> resultados = new ArrayList<>();
            for (Pago pago : pagos) {
                if (pago.getIdTarjeta().equals(idTarjeta) && pago.getFecha().toLocalDate().equals(fecha)) {
                    resultados.add(pago);
                }
            }
            return resultados;
        }

        @Override
        public List<Estadia> buscarEstadiasProximasAVencer(LocalDateTime ahora, long minutosAnticipacion) {
            List<Estadia> proximas = new ArrayList<>();
            for (Estadia estadia : estadias) {
                if (!"ACTIVA".equals(estadia.getEstado())) {
                    continue;
                }
                long minutosRestantes = java.time.Duration.between(ahora, estadia.getHoraFin()).toMinutes();
                if (minutosRestantes > 0 && minutosRestantes <= minutosAnticipacion) {
                    proximas.add(estadia);
                }
            }
            return proximas;
        }
    }

    private static class ServiciosSimulados implements Servicios {
        @Override
        public CobroResultado cobrar(String idUsuario, String idTarjeta, BigDecimal monto) {
            String referencia = "REF-" + UUID.randomUUID();
            return new CobroResultado(true, referencia);
        }

        @Override
        public void enviarNotificacion(String idUsuario, String mensaje) {
            System.out.println("Notificación para " + idUsuario + ": " + mensaje);
        }
    }
}
