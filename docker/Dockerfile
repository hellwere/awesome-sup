FROM alpine/java:21-jdk
WORKDIR /app
COPY target/awesome-sup.jar app.jar
# Открываем порт
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]