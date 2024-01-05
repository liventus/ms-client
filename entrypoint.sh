#!/bin/bash
set -e

# Esperar a que config-server esté listo (usando wait-for-it.sh)
/usr/local/bin/wait-for-it.sh config-server:8888 -t 0
/usr/local/bin/wait-for-it.sh mongo:27017 -t 0

# Ejecutar la aplicación Java
exec java -jar /app.jar