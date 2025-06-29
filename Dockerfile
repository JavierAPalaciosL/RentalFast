FROM maven:sapmachine

LABEL maintaner="javi.261280@gmail.com" version="1.0" description="BACKEND WITH SPRING BOOT NEEDS ON SOME ENVERIOMENTS"

WORKDIR /backend

COPY . /backend

RUN mvn clean package

WORKDIR ./backend/target

ENTRYPOINT ["java","-jar","/backend/target/app-0.0.1-SNAPSHOT.jar"]

