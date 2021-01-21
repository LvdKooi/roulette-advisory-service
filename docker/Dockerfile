FROM openjdk:13-alpine

ADD RouletteAdvice.jar /

CMD ["java","-Dspring.profiles.active=prd", "-jar", "RouletteAdvice.jar"]

EXPOSE 8080