FROM openjdk:8-jdk-alpine
MAINTAINER shwetrai
RUN mkdir /app
WORKDIR /app
COPY /target/SISOnboarding-0.0.1-SNAPSHOT.jar trackerui.jar
CMD java -jar trackerui.jar
EXPOSE 8082