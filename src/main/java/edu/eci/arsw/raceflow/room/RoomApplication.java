package edu.eci.arsw.raceflow.room;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Punto de entrada del esqueleto de Room Service. Las responsabilidades de ciclo
 * de vida de salas y sesiones en vivo se consolidaron en realtime-service; este
 * módulo se mantiene como un microservicio placeholder con su andamiaje de métricas en su lugar.
 */
@SpringBootApplication
public class RoomApplication {
    /** @param args argumentos de línea de comandos pasados a Spring Boot */
    public static void main(String[] args) {
        SpringApplication.run(RoomApplication.class, args);
    }
}
