
FROM openjdk:17
# Copiar entrypoint.sh y darle permisos de ejecución
COPY entrypoint.sh /usr/local/bin/entrypoint.sh
RUN chmod +x /usr/local/bin/entrypoint.sh

ADD https://raw.githubusercontent.com/vishnubob/wait-for-it/master/wait-for-it.sh /usr/local/bin/wait-for-it.sh
RUN chmod +x /usr/local/bin/wait-for-it.sh

COPY target/ms-client-0.0.1-SNAPSHOT.jar /app.jar

# Establecer entrypoint.sh como el punto de entrada
ENTRYPOINT ["/usr/local/bin/entrypoint.sh"]