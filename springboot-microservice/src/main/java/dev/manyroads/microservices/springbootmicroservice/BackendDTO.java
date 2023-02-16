package dev.manyroads.microservices.springbootmicroservice;

/**
 * This class is used as a response object for communication between the services
 */
public class BackendDTO {

    // ---- Attributes ----
    private String greeting;
    private long time;
    private String ip;

    // ---- Getters and Setters ----
    public String getGreeting() {
        return greeting;
    }

    public void setGreeting(String greeting) {
        this.greeting = greeting;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}
