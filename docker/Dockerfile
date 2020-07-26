FROM openjdk:13-alpine

ADD RouletteAdvisorySystem.jar /

CMD ["java","-Dspring.profiles.active=prd", "-jar", "RouletteAdvisorySystem.jar"]

EXPOSE 8080