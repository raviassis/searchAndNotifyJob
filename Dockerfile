FROM openjdk:11
COPY ./target/searchAndNotifyJob-jar-with-dependencies.jar /tmp/app.jar
WORKDIR /tmp
ENTRYPOINT java -cp app.jar Main