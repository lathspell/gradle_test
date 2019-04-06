FROM openjdk:8-jdk-alpine
COPY template-java-groovy-all.jar /srv/foo/foo.jar
# VOLUME /var/log/foo/
# EXPOSE 8080
# ENV SPRING_PROFILES_ACTIVE=dev
ENTRYPOINT ["/usr/bin/java"]
CMD ["-jar", "/srv/foo/foo.jar"]
