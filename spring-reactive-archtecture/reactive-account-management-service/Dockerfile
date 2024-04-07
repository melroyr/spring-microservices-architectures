FROM openjdk:17-oracle
COPY target/reactive-account-management-service-0.0.1-SNAPSHOT.jar app.jar
#ENTRYPOINT ["java","-jar","/app.jar"]
ENTRYPOINT exec java $JAVA_OPTS app.jar
