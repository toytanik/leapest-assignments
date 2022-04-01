FROM maven:3.8.4-jdk-11 AS build
COPY src /usr/src/app/src
COPY pom.xml /usr/src/app
RUN mvn -f /usr/src/app/pom.xml clean package

FROM openjdk:11
COPY --from=build /usr/src/app/target/fraud-detection-0.0.1-SNAPSHOT.jar /usr/app/reading-is-good-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/usr/app/fraud-detection-0.0.1-SNAPSHOT.jar"]

