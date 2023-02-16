package dev.manyroads.microservices.springbootmicroservice;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * The Spring Boot GreeterRestController class receives a REST Get request, inquires the cluster backend microservice
 * and returnsits response.
 */
@RestController
@CrossOrigin
// Variables of the greeting prefix are stored in the application.properties file in resources directory
@ConfigurationProperties(prefix="greeting")
public class GreeterRestController {

    // ---- Attributes ----
    private RestTemplate template = new RestTemplateBuilder().build();
    private String saying;
    private String backendServiceHost;
    private int backendServicePort;

    // ---- Routes ----
    @RequestMapping(value       = "/greeting",
                    method      = RequestMethod.GET,
                    produces    = "text/plain")
    public String greeting(){

        // Composing the url for inquiring the backend microservice
        String backendServiceUrl = String.format(   "http://%s:%d/backend/api/backend?greeting={greeting}",
                                                    backendServiceHost,
                                                    backendServicePort);

        System.out.println("Sending to: " + backendServiceUrl);

        // Get response from backend URL request
        BackendDTO response = template.getForObject(backendServiceUrl, BackendDTO.class, saying);

        System.out.println("Response " + response);

        // Return response
        return response.getGreeting() + " at host: " + response.getIp();
    }


    // --- Getters / Setters to set attributes from application.properties file in resources directory  ---
    public void setSaying(String saying) {
        this.saying = saying;
    }

    public void setBackendServiceHost(String backendServiceHost) {
        this.backendServiceHost = backendServiceHost;
    }

    public void setBackendServicePort(int backendServicePort) {
        this.backendServicePort = backendServicePort;
    }
}