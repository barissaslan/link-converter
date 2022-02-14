FROM openjdk:11
MAINTAINER barisaslan.com
ARG JAR_FILE
COPY ${JAR_FILE} trendyol-link-converter.jar
ENTRYPOINT ["java", "-jar", "trendyol-link-converter.jar"]