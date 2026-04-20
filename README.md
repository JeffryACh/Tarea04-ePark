# Tarea 4 - Modelado de Objetos ePark

Este repositorio contiene una base inicial para trabajar la Tarea 4 del curso, enfocada en modelado orientado a objetos y mensajeria entre componentes del ecosistema urbano.

La base esta hecha para iniciar rapido con codigo exclusivamente en Java en la capa de implementacion.

## Objetivo del repositorio

1. Dejar una estructura limpia para modelar responsabilidades por clase.
2. Cubrir como plantilla las HU del punto 7:
3. HU 7.1 Estacionar un vehiculo en la calle.
4. HU 7.2 Notificar al usuario cuando faltan 5 minutos para vencer.
5. HU 7.3 Consultar pagos por tarjeta en un dia para un cliente especifico.

## Estructura creada

- src/main/java/com/epark/domain/enums
- src/main/java/com/epark/domain/model
- src/main/java/com/epark/domain/ports
- src/main/java/com/epark/application/dto
- src/main/java/com/epark/application/usecase
- src/main/java/com/epark/infrastructure/stub
- src/main/java/com/epark/app

## Como ejecutar la plantilla

### 1. Requisitos comunes para cualquier sistema operativo

1. Instalar Java JDK 17 o superior.
2. Abrir una terminal y validar que Java quedo instalado:

```bash
java -version
javac -version
```

- Ubicarse en la carpeta raiz del proyecto.
- Seguir la seccion de tu sistema operativo.

### 2. Linux Ubuntu o Debian (nativo o por WSL)

#### 2.1 Instalar Java JDK

```bash
sudo apt update
sudo apt install -y openjdk-17-jdk
```

#### 2.2 Verificar instalacion

```bash
java -version
javac -version
```

#### 2.3 Entrar al proyecto

Si estas en Linux nativo:

```bash
cd /ruta/a/Tarea04-ePark
```

Si estas en WSL y el proyecto esta en disco de Windows:

```bash
cd /mnt/c/TareasReque/Tarea04/Tarea04-ePark
```

#### 2.4 Compilar y ejecutar

```bash
mkdir -p out
find src/main/java -name "*.java" > sources.txt
javac -d out @sources.txt
java -cp out com.epark.app.Main
```

### 3. Linux Arch (nativo o por WSL)

#### 3.1 Instalar Java JDK

```bash
sudo pacman -Syu
sudo pacman -S --needed jdk17-openjdk
```

#### 3.2 Verificar instalacion

```bash
java -version
javac -version
```

#### 3.3 Entrar al proyecto

Nativo:

```bash
cd /ruta/a/Tarea04-ePark
```

WSL con proyecto en disco Windows:

```bash
cd /mnt/c/TareasReque/Tarea04/Tarea04-ePark
```

#### 3.4 Compilar y ejecutar

```bash
mkdir -p out
find src/main/java -name "*.java" > sources.txt
javac -d out @sources.txt
java -cp out com.epark.app.Main
```

### 4. Windows 11 (PowerShell)

#### 4.1 Instalar Java JDK (una sola vez)

Opcion 1:

```powershell
winget install -e --id Microsoft.OpenJDK.17
```

Opcion 2 (si la opcion 1 no aparece en tu catalogo):

```powershell
winget install -e --id EclipseAdoptium.Temurin.17.JDK
```

#### 4.2 Cerrar y abrir PowerShell de nuevo, luego validar

```powershell
java -version
javac -version
```

#### 4.3 Ir al proyecto

```powershell
cd C:\TareasReque\Tarea04\Tarea04-ePark
```

#### 4.4 Compilar y ejecutar

```powershell
New-Item -ItemType Directory -Force out | Out-Null
$fuentes = Get-ChildItem -Recurse -Path src/main/java -Filter *.java | ForEach-Object { $_.FullName }
javac -d out $fuentes
java -cp out com.epark.app.Main
```

### 5. macOS (Intel y Apple Silicon)

#### 5.1 Instalar Homebrew (si aun no lo tienes)

```bash
/bin/bash -c "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/HEAD/install.sh)"
```

#### 5.2 Instalar Java JDK

```bash
brew update
brew install openjdk@17
```

#### 5.3 Agregar Java al PATH de tu shell

```bash
echo 'export PATH="$(brew --prefix openjdk@17)/bin:$PATH"' >> ~/.zshrc
source ~/.zshrc
```

#### 5.4 Verificar instalacion

```bash
java -version
javac -version
```

#### 5.5 Compilar y ejecutar

```bash
cd /ruta/a/Tarea04-ePark
mkdir -p out
find src/main/java -name "*.java" > sources.txt
javac -d out @sources.txt
java -cp out com.epark.app.Main
```

### 6. Instalacion de WSL en Windows 11 (si deseas correr Linux)

Paso 1. Abrir PowerShell como administrador.

Paso 2. Instalar WSL:

```powershell
wsl --install
```

Paso 3. Reiniciar el equipo cuando se solicite.

Paso 4. Ver distribuciones disponibles:

```powershell
wsl --list --online
```

Paso 5. Instalar la distro que vayas a usar (ejemplo Ubuntu o Debian):

```powershell
wsl --install -d Ubuntu
```

o

```powershell
wsl --install -d Debian
```

Para Arch, usar el nombre exacto que aparezca en el listado de `wsl --list --online`.

Paso 6. Abrir la distro, crear usuario Linux y luego seguir pasos de las secciones 2 o 3 segun corresponda.

### 7. Opcion IDE (VS Code o IntelliJ)

1. Abrir el proyecto en el IDE.
2. Confirmar JDK 17+ configurado en el IDE.
3. Ejecutar la clase `com.epark.app.Main`.
4. Verificar salida en consola con el resultado de reserva y pagos.

## Reparto recomendado para 3 personas

### Persona 1 - Dominio y HU 7.1

Responsabilidad principal:
Modelar y fortalecer el flujo de estacionamiento.

Entregables:

1. Validaciones de negocio para Parquimetro, ZonaParqueo y Estadia.
2. Ajustes de tarifa por tipo de vehiculo.
3. Casos de prueba de dominio para estacionamiento.

### Persona 2 - Notificaciones y HU 7.2

Responsabilidad principal:
Implementar y estabilizar las alertas de vencimiento.

Entregables:

1. Estrategia para evitar notificaciones duplicadas.
2. Integracion de canales APP, SMS o EMAIL por configuracion.
3. Casos de prueba del use case NotificarProximoVencimientoUseCase.

### Persona 3 - Pagos, reportes y HU 7.3

Responsabilidad principal:
Completar consulta de pagos y preparar salida para auditoria.

Entregables:

1. Mejora de filtros por fecha, cliente y estado de pago.
2. Formato de reporte reutilizable para exportacion.
3. Casos de prueba del use case ConsultarPagosPorTarjetaUseCase.

## Flujo de trabajo sugerido

1. Cada persona trabaja en su propia rama.
2. Merge semanal hacia una rama de integracion.
3. Demo conjunta al final de cada iteracion con las 3 HU.

## Documentos del proyecto

- README.md: resumen general y arranque rapido.
- Documentacion.md: detalle tecnico y decisiones de modelado.
- ManualDeUsuario.md: guia practica para usar y probar la plantilla.
