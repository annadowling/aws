FROM java:8
VOLUME /tmp
EXPOSE 8080
ADD /build/libs/aws.jar aws.jar
ENTRYPOINT ["java","-jar","aws.jar"]