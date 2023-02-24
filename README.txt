Robust Microservices

This building block is about robust microservices, which is needed, because distributed systems like
microservices will fail. To achieve this robustness, one shall create replicas with a load balancer in Kubernetes,
keep the services loosely coupled, give the services local data storage and isolate failures from propagating.
Isolating failures, caused by degradation of service, can be managed with retries, circuit-breakers, timeouts
and fallback methods.

- Retries will intend to communicat till maxRetries
- Circuit-breaker prevents a cascade of failures, when the remote service backend is down, by shutting off
communication to the backend for x seconds.
- Timeout will throw an exception when waiting time has exceeded threshold
- Fallback method is invoked to respond to consumer

Summary
- Java 17
- Directory robust-microservices
    - BackendController based on Quarkus at localhost:8082
    - Run with "./mvnw compile quarkus:dev" command at root
- Directory HttpServlet-bakend
    - TestServlet based on Jakarta EE8 at localhost:8080
    - Run with "mvn wildfly:run" command at root

Testing

By changing the parameters and uncomment the annotations in BackendController and TestServlet , you will see
how to isolate the microservices from failures.


