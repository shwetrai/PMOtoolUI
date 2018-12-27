FROM openjdk:8-jdk-alpine
MAINTAINER ppant
RUN mkdir /app
WORKDIR /app
COPY /target/SISOnboarding-0.0.1-SNAPSHOT.jar trackerui.jar
COPY /target/classes/application.properties application-dev.properties
COPY /target/classes/config/uiclient.properties uiclient.properties
CMD java -jar trackerui.jar
EXPOSE 8082