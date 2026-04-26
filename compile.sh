#!/usr/bin/env bash
set -euo pipefail

# Compile all Java sources into the bin directory.
mkdir -p bin
javac -d bin $(find src/main/java -name '*.java')

echo "Compilation finished. To run the app, use:"
echo "  java -cp bin com.epark.app.Main"

echo "Running the application..."
java -cp bin com.epark.app.Main
