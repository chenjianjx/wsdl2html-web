FROM maven:3.6-jdk-8-alpine as BuildWar
COPY src /usr/app/src
COPY pom.xml /usr/app
WORKDIR /usr/app
RUN mvn dependency:go-offline
RUN mvn clean install -DskipTests



FROM tomcat:8.5.53-jdk8-openjdk as DropWar
COPY --from=BuildWar /usr/app/target/*.war /usr/local/tomcat/webapps/ROOT.war

EXPOSE 2340

ENTRYPOINT ["catalina.sh", "run"]