FROM amazoncorretto:17
RUN mkdir -p /DB
WORKDIR /
COPY target/RSS-pastebin-0.0.1-SNAPSHOT.jar /pastebin.jar
CMD java -jar pastebin.jar