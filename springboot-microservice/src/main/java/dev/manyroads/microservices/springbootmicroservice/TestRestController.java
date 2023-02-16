package dev.manyroads.microservices.springbootmicroservice;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * This is a test class for initial testing of the microservice. Use "mvn spring-boot:run" in root directory
 */
@RestController
@CrossOrigin
@RequestMapping("/api")
// Variables of the greeting prefix are stored in the application.properties file in resources directory
@ConfigurationProperties(prefix="helloapp")
public class TestRestController {

    // ----------- Attributes ----------
    private String saying;

    // ----------- Getters /Setters ----------
    public void setSaying(String saying) {
        this.saying = saying;
    }

    // ----------- Routes ----------
    @GetMapping("/hello")
    public String hello(){

        String hostname = null;
        try {
            hostname = InetAddress.getLocalHost()
                    .getHostAddress();
        } catch (UnknownHostException e) {
            hostname = "unknown";
        }
        return saying + " " + hostname;
    }


}
