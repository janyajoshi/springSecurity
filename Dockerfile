FROM openjdk:17
EXPOSE 5000
ADD build/libs/springSecurity-0.0.1.jar springSecurity-0.0.1.jar
ENTRYPOINT ["java", "-jar", "/springSecurity-0.0.1.jar"]