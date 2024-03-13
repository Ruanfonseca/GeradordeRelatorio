# Use a imagem base do OpenJDK
FROM openjdk:17-alpine

ADD target/geradordearquivo-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 9193

CMD ["java", "-jar", "app.jar"]
