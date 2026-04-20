# Manual de Usuario - ePark (Plantilla)

## 1. Proposito

Este manual explica como ejecutar la plantilla base y como validar de forma simple los tres escenarios de usuario solicitados.

## 2. Requisitos

1. Java JDK 17 o superior instalado.
2. VS Code con Extension Pack for Java o IDE equivalente.

## 3. Preparacion inicial

1. Abrir una terminal en tu sistema operativo.
2. Validar que Java esta correctamente instalado:

```bash
java -version
javac -version
```

Si alguno falla, instala JDK 17+ con las instrucciones de la seccion 4 segun tu entorno.

## 4. Ejecucion paso a paso por sistema operativo

### 4.1 Linux Ubuntu o Debian (nativo)

#### Paso 1 (Ubuntu/Debian): Instalar Java JDK

```bash
sudo apt update
sudo apt install -y openjdk-17-jdk
```

#### Paso 2 (Ubuntu/Debian): Verificar Java

```bash
java -version
javac -version
```

#### Paso 3 (Ubuntu/Debian): Ir a la carpeta del proyecto

```bash
cd /ruta/a/Tarea04-ePark
```

#### Paso 4 (Ubuntu/Debian): Compilar

```bash
mkdir -p out
find src/main/java -name "*.java" > sources.txt
javac -d out @sources.txt
```

#### Paso 5 (Ubuntu/Debian): Ejecutar

```bash
java -cp out com.epark.app.Main
```

### 4.2 Linux Arch (nativo)

#### Paso 1 (Arch): Instalar Java JDK

```bash
sudo pacman -Syu
sudo pacman -S --needed jdk17-openjdk
```

#### Paso 2 (Arch): Verificar Java

```bash
java -version
javac -version
```

#### Paso 3 (Arch): Ir al proyecto

```bash
cd /ruta/a/Tarea04-ePark
```

#### Paso 4 (Arch): Compilar y ejecutar

```bash
mkdir -p out
find src/main/java -name "*.java" > sources.txt
javac -d out @sources.txt
java -cp out com.epark.app.Main
```

### 4.3 Linux por WSL (Ubuntu, Debian o Arch)

#### Paso 1 (WSL): Instalar WSL (solo una vez, en Windows 11)

Abrir PowerShell como administrador:

```powershell
wsl --install
```

Reiniciar el equipo cuando termine.

#### Paso 2 (WSL): Instalar distribucion Linux

Listar distribuciones disponibles:

```powershell
wsl --list --online
```

Instalar una distribucion (ejemplo):

```powershell
wsl --install -d Ubuntu
```

o

```powershell
wsl --install -d Debian
```

Para Arch, usar el nombre exacto mostrado por `wsl --list --online`.

#### Paso 3 (WSL): Entrar a tu distro e instalar Java

Aplicar las instrucciones de Ubuntu/Debian (seccion 4.1) o Arch (seccion 4.2).

#### Paso 4 (WSL): Ir al proyecto desde WSL

Si el proyecto esta en el disco C de Windows:

```bash
cd /mnt/c/TareasReque/Tarea04/Tarea04-ePark
```

#### Paso 5 (WSL): Compilar y ejecutar

```bash
mkdir -p out
find src/main/java -name "*.java" > sources.txt
javac -d out @sources.txt
java -cp out com.epark.app.Main
```

### 4.4 Windows 11 (PowerShell)

#### Paso 1 (Windows 11): Instalar Java JDK

```powershell
winget install -e --id Microsoft.OpenJDK.17
```

Si ese paquete no aparece:

```powershell
winget install -e --id EclipseAdoptium.Temurin.17.JDK
```

#### Paso 2 (Windows 11): Cerrar y abrir PowerShell, luego verificar

```powershell
java -version
javac -version
```

#### Paso 3 (Windows 11): Ir al proyecto

```powershell
cd C:\TareasReque\Tarea04\Tarea04-ePark
```

#### Paso 4 (Windows 11): Compilar

```powershell
New-Item -ItemType Directory -Force out | Out-Null
$fuentes = Get-ChildItem -Recurse -Path src/main/java -Filter *.java | ForEach-Object { $_.FullName }
javac -d out $fuentes
```

#### Paso 5 (Windows 11): Ejecutar

```powershell
java -cp out com.epark.app.Main
```

### 4.5 macOS

#### Paso 1 (macOS): Instalar Homebrew (si no lo tienes)

```bash
/bin/bash -c "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/HEAD/install.sh)"
```

#### Paso 2 (macOS): Instalar Java JDK

```bash
brew update
brew install openjdk@17
echo 'export PATH="$(brew --prefix openjdk@17)/bin:$PATH"' >> ~/.zshrc
source ~/.zshrc
```

#### Paso 3 (macOS): Verificar Java

```bash
java -version
javac -version
```

#### Paso 4 (macOS): Ir al proyecto

```bash
cd /ruta/a/Tarea04-ePark
```

#### Paso 5 (macOS): Compilar y ejecutar

```bash
mkdir -p out
find src/main/java -name "*.java" > sources.txt
javac -d out @sources.txt
java -cp out com.epark.app.Main
```

### 4.6 Opcion IDE (VS Code o IntelliJ)

1. Abrir la carpeta del proyecto.
2. Configurar JDK 17+ en el IDE.
3. Ejecutar la clase principal `com.epark.app.Main`.
4. Revisar salida de consola.

## 5. Que valida esta demo

1. HU 7.1:
Se crea una reserva de parqueo y se intenta un cobro simulado.

2. HU 7.2:
Se consulta si hay estadias proximas a vencer para enviar aviso.

3. HU 7.3:
Se listan pagos de una tarjeta para un usuario en la fecha actual.

## 6. Como usarla en equipo de 3 personas

1. Persona 1 corre el flujo de estacionamiento y valida estados.
2. Persona 2 prueba envio de notificaciones y mensajes.
3. Persona 3 valida las consultas de pagos y reportes.

Todos deben integrar semanalmente para evitar divergencias de modelo.

## 7. Resultado esperado en consola

Se deben ver lineas similares a:

1. Mensaje de resultado de reserva.
2. ID de estadia y monto.
3. Cantidad de notificaciones enviadas.
4. Cantidad de pagos encontrados.

## 8. Limitaciones actuales de la plantilla

1. Persistencia solo en memoria.
2. Cobro simulado sin pasarela real.
3. Notificaciones por consola.

## 9. Proximos pasos recomendados

1. Agregar pruebas unitarias por caso de uso.
2. Reemplazar stubs por adaptadores reales.
3. Crear diagrama UML final con los objetos implementados.
