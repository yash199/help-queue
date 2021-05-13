FROM java:8
VOLUME /tmp 
EXPOSE 8080
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} help-queue-1.0-SNAPSHOT.jar 
ENTRYPOINT ["java","-jar","help-queue-1.0-SNAPSHOT.jar"]
