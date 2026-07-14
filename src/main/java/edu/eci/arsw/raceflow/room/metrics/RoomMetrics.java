package edu.eci.arsw.raceflow.room.metrics;

import io.micrometer.core.instrument.*;
import org.springframework.stereotype.Component;
import java.util.concurrent.atomic.AtomicInteger;

/** Contadores/gauges de Micrometer para room-service, expuestos en {@code /actuator/prometheus}. */
@Component
public class RoomMetrics {

    private final Counter roomsCreated;
    private final Counter joinAttempts;
    private final AtomicInteger activeRooms = new AtomicInteger(0);

    /** @param registry el registro de Micrometer al cual asociar estas métricas */
    public RoomMetrics(MeterRegistry registry) {
        this.roomsCreated = Counter.builder("raceflow.rooms.created")
                .description("Total rooms created")
                .register(registry);

        this.joinAttempts = Counter.builder("raceflow.rooms.join.attempts")
                .description("Room join attempts")
                .tag("result", "unknown")
                .register(registry);

        Gauge.builder("raceflow.rooms.active", activeRooms, AtomicInteger::get)
                .description("Currently active rooms")
                .register(registry);

        // Pre-register tagged counters for each result
        Tags.of("result", "success");
        Counter.builder("raceflow.rooms.join.attempts").tag("result", "success").register(registry);
        Counter.builder("raceflow.rooms.join.attempts").tag("result", "invalid_code").register(registry);
        Counter.builder("raceflow.rooms.join.attempts").tag("result", "full").register(registry);
    }

    /** Incrementa el contador total de salas creadas. */
    public void recordRoomCreated() { roomsCreated.increment(); }
    /**
     * Incrementa el contador de intentos de unión, etiquetado por resultado.
     *
     * @param result   uno de {@code success}, {@code invalid_code}, {@code full}
     * @param registry el registro del cual resolver el contador etiquetado
     */
    public void recordJoinAttempt(String result, MeterRegistry registry) {
        registry.counter("raceflow.rooms.join.attempts", "result", result).increment();
    }
    /** Incrementa el gauge de salas activas. */
    public void incrementActiveRooms() { activeRooms.incrementAndGet(); }
    /** Decrementa el gauge de salas activas. */
    public void decrementActiveRooms() { activeRooms.decrementAndGet(); }
}
