#Spring AWS Web application
The web application which was created for use with this assignment is a SpringBoot Java application which allows users to perform CRUD and sort functions on AWS Services via REST (see Figure 1: spring-aws webapp). The application has an AngularJS client and writes to a MySQL database. The application deployment was completed via docker which will be discussed in further detail in Section 9 - Additional Functionality. This application was designed in order to support a three tier AWS web architecture which entails the following concepts:
Public Layer - Application Load balancer receiving and routing traffic to application instances.
Application Layer - Web Apps deployed to EC2 servers in private subnets
Database layer - Privately accessible RDS instance running MySQL instance with Multi AZ deployment.


# Application Details:
- SpringBoot 2.0.5 RELEASE
- Java SDK 1_8_0__161
- MySQL 5.7.23
- Docker Repository: annadowling/spring-aws:12
- Gradle 3.2.1
- AngularJS 1.2.16
-  Bootstrap 3.3.7-1
- App URL: loadBalancerUrl/spring-aws
  http://Cloud-Architect-ALB-180347510.eu-west-1.elb.amazonaws.com/spring-aws
  * This url is subject to change depending on the endpoint configured when the boto3 scripts are run.
  
  
# Java Run details
1. Change mysql details in application.properties to match local installation
2. Ensure aws database is created.
3. java jar build/libs/aws.jar 


# Docker Build Details
1. gradlew build (build latest jar)
2. docker build -f Dockerfile -t annadowling/spring-aws:versionNumber .
3. docker push annadowling/spring-aws:versionNumber
4. On Server: docker pull annadowling/spring-aws:versionNumber
5. On Server: docker rm --force containerId
6. On Server: docker rm $(docker ps -a -q)
7. On Server: docker run --restart always -t --name spring-aws-12 -p 80:8080 annadowling/spring-aws:versionNumber
8. On Server: sudo systemctl enable docker (enable docker to run on server reboot)
