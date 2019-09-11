FROM openjdk:8-jdk-alpine

COPY target/helloJenkins-*.jar /opt/helloJenkins.jar

ENTRYPOINT ["java", "-jar", "/opt/helloJenkins.jar"]