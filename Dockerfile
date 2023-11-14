#FROM maven:3.8.6-amazoncorretto-8 AS build
#
#COPY . /app
#WORKDIR /app
#
#RUN --mount=type=cache,target=/root/.m2 mvn clean install assembly:assembly
#
##CMD tail -f /dev/null
#FROM amazoncorretto:8
#
#COPY --from=build ./app/target /app
#
#WORKDIR /app/target/classes
#
#EXPOSE 8090
#
#CMD java -cp premium-soap-1.0-SNAPSHOT-jar-with-dependencies.jar ServerJWS
#
#CMD tail -f /dev/null

FROM maven:3.6.3-amazoncorretto-8
COPY ./target /app
WORKDIR /app
COPY . .
RUN mvn clean compile assembly:single
CMD ["java", "-jar", "target/premium-SOAP-1.0-SNAPSHOT-jar-with-dependencies.jar"]
#EXPOSE 8090
#ENTRYPOINT
