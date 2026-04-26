# Historias de Usuario - ePark

## Estacionar vehiculo en calle

Un usuario ingresa los datos del vehiculo, el parquimetro, los minutos de estacionamiento y la tarjeta.
El sistema verifica disponibilidad, calcula el monto estimado y procesa el cobro.
Si el pago es aprobado, guarda la estadia y el pago, activa la estadia y entrega la confirmacion.
Si el pago es rechazado, cancela la estadia, libera el cupo y notifica el motivo.

## Notificar proximidad de vencimiento

El sistema revisa las estadias activas cercanas a su vencimiento y envía notificaciones al usuario.
Si el parametro de anticipacion es invalido, devuelve error.
Cada estadia encontrada genera un aviso antes de que termine el tiempo.

## Consultar pagos por tarjeta en un dia

El usuario solicita los pagos hechos con una tarjeta en una fecha especifica.
El sistema busca los pagos del usuario para esa tarjeta y fecha, transforma cada pago en un resumen y muestra la lista.
