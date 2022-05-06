FROM maven:3.8.3-openjdk-17-slim

RUN mkdir /asset-type-service
WORKDIR /asset-type-service

COPY /Application/target /asset-type-service

ENTRYPOINT java -Dspring.profiles.active=$PROFILE -jar /asset-type-service/pam-asset-type-service-exec.jar