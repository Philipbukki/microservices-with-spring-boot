#start with base image containing java runtime image
FROM openjdk:17-jdk-slim
#who maintains the image
MAINTAINER philip bukki
#Add appplication jar to the image
COPY target/accounts-0.0.1-SNAPSHOT.jar accounts-0.0.1-SNAPSHOT.jar
#execute the application
ENTRYPOINT ["java","-jar","accounts-0.0.1-SNAPSHOT.jar"]