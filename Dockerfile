# Usar la imagen oficial de OpenJDK 21 en lugar de una imagen genérica
FROM openjdk:21-jdk

# Establecer el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copiar el archivo JAR generado al contenedor
COPY SpringSwagger1/target/SpringSwagger1-0.0.1-SNAPSHOT.jar app.jar

# Exponer el puerto en el que corre la aplicación
EXPOSE 8081

# Configurar el punto de entrada para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
