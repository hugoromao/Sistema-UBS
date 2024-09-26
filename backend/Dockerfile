#
# Build stage
#
FROM maven:3.9.1-amazoncorretto-17 AS build
COPY . .
RUN mvn clean package -DskipTests

#
# Package stage
#
FROM amazoncorretto:17.0.7-alpine3.14
COPY --from=build /target/teste-0.0.1-SNAPSHOT.jar teste.jar

ENTRYPOINT ["java", "-jar", "teste.jar"]