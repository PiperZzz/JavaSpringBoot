# FROM openjdk:17-jdk
# ARG JAR_FILE=target/*.jar
# COPY ${JAR_FILE} app.jar
# ENTRYPOINT ["java","-jar","/app.jar"]

FROM maven:latest
WORKDIR /app
COPY . .
RUN mvn clean install -DskipTests
EXPOSE 8080
CMD ["java", "-jar", "target/spring-boot-web-server-0.0.1-SNAPSHOT.jar"]