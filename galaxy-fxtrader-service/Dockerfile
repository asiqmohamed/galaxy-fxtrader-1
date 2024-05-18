FROM openjdk:17

#Set app home folder
ENV APP_HOME /app

#Create base app folder
RUN mkdir $APP_HOME

WORKDIR $APP_HOME

ARG JAR_FILE

ADD target/${JAR_FILE} galaxy-fxtrader-service.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","galaxy-fxtrader-service.jar"]