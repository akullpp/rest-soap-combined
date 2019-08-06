FROM alpine:edge

RUN apk add --no-cache openjdk8 maven

WORKDIR /opt/geoloc_build/
ADD pom.xml .
ADD src/ src/

RUN mvn clean install
RUN apk del maven

WORKDIR /opt/geoloc/
RUN ls /opt/geoloc_build/target
RUN mv /opt/geoloc_build/target/geoloc-*.jar ./geoloc.jar
RUN rm -rf /opt/geoloc_build

ENV JAVA_OPTS -server -Xms512m -Xmx512m -XX:+HeapDumpOnOutOfMemoryError
CMD exec /usr/bin/java $JAVA_OPTS -jar ./geoloc.jar