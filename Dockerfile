FROM adoptopenjdk/openjdk11:jre-11.0.8_10-debianslim

EXPOSE 8080

WORKDIR /data/app

COPY ./build/libs/*jar .

USER 1001:1001

ENTRYPOINT [ "/bin/bash", "-c", "find -type f -name '*.jar' | xargs java -jar" ]
