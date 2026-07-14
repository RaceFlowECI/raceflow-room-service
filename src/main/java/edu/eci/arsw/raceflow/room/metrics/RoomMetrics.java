package edu.eci.arsw.raceflow.room.metrics;

import io.micrometer.core.instrument.*;
import org.springframework.stereotype.Component;
import java.util.concurrent.atomic.AtomicInteger;

/** Micrometer counters/gauges for room-service, exposed at {@code /actuator/prometheus}. */
@Component
public class RoomMetrics {

    private final Counter roomsCreated;
    private final Counter joinAttempts;
    private final AtomicInteger activeRooms = new AtomicInteger(0);

    /** @param registry the Micrometer registry to bind these meters to */
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

    /** Increments the total rooms-created counter. */
    public void recordRoomCreated() { roomsCreated.increment(); }
    /**
     * Increments the join-attempts counter, tagged by result.
     *
     * @param result   one of {@code success}, {@code invalid_code}, {@code full}
     * @param registry the registry to resolve the tagged counter from
     */
    public void recordJoinAttempt(String result, MeterRegistry registry) {
        registry.counter("raceflow.rooms.join.attempts", "result", result).increment();
    }
    /** Increments the active-rooms gauge. */
    public void incrementActiveRooms() { activeRooms.incrementAndGet(); }
    /** Decrements the active-rooms gauge. */
    public void decrementActiveRooms() { activeRooms.decrementAndGet(); }
}
