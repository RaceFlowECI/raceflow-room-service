package edu.eci.arsw.raceflow.room;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Entry point of the Room Service skeleton. Room lifecycle and live-session
 * responsibilities were consolidated into realtime-service; this module is
 * kept as a placeholder microservice with its metrics scaffolding in place.
 */
@SpringBootApplication
public class RoomApplication {
    /** @param args command-line arguments passed to Spring Boot */
    public static void main(String[] args) {
        SpringApplication.run(RoomApplication.class, args);
    }
}
