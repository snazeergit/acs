#Use an official OpenJDK 17 runtime as a parent image
FROM openjdk:17

#Set the working directory in the container
WORKDIR /app

#Copy the application JAR file into the container at /app
COPY ./acs-service.jar /app

#Make port 8080 available to the world outside this container
EXPOSE 8080

#Run the application when the container launches
ENTRYPOINT ["java", "-jar", "acs-service.jar"]