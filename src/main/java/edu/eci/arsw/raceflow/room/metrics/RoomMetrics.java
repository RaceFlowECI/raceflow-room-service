package edu.eci.arsw.raceflow.room.metrics;

import io.micrometer.core.instrument.*;
import org.springframework.stereotype.Component;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class RoomMetrics {

    private final Counter roomsCreated;
    private final Counter joinAttempts;
    private final AtomicInteger activeRooms = new AtomicInteger(0);

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

    public void recordRoomCreated() { roomsCreated.increment(); }
    public void recordJoinAttempt(String result, MeterRegistry registry) {
        registry.counter("raceflow.rooms.join.attempts", "result", result).increment();
    }
    public void incrementActiveRooms() { activeRooms.incrementAndGet(); }
    public void decrementActiveRooms() { activeRooms.decrementAndGet(); }
}
