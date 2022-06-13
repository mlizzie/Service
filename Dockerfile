FROM openjdk:11

EXPOSE 8080

RUN mkdir ./app

COPY ./Service.jar ./app

CMD java -jar ./app/Service.jar