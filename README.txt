K8-SPRING-HttpServlet-Microservices

This building block consist of a Spring Boot- and backend microservice, both mounted in a Kubernetes minikube.
The Spring Boot microservice receives a REST Get request, subsequently inquires the backend microservice and the
response of the backend is then returned.

Summary

- Spring Boot microservice on Tomcat server
- HttpServlet-backend on WildFly server
- Java SDK 17
- Docker and Docker-Hub
- Kubernetes minikube

Testing

Create a pod from the curlimages/curl image:
- kubectl run mycurlpod --image=curlimages/curl -i --tty -- sh

Start testing the microservices:

curl -v springboot-microservice:8080/greeting

..and you should see or similar:

"Hello Spring Boot from cluster Backend at host: 10.244.0.49"


