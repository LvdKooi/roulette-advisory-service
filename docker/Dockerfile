FROM java:openjdk-8-jdk

ADD RouletteAdvisorySystem.jar /

CMD ["java","-Dspring.profiles.active=prd", "-jar", "RouletteAdvisorySystem.jar"]

EXPOSE 8080