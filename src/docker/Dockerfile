FROM java:8
MAINTAINER Mahima Agarwal  <mahima.agarwal@accenture.com>

VOLUME /tmp
ADD GenericCURGatewayWS-0.0.1.jar  app.jar
RUN sh -c 'touch /app.jar'
EXPOSE 80
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]