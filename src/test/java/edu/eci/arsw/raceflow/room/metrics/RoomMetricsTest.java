package edu.eci.arsw.raceflow.room.metrics;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.simple.SimpleMeterRegistry;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RoomMetricsTest {

    private MeterRegistry registry;
    private RoomMetrics metrics;

    @BeforeEach
    void setUp() {
        registry = new SimpleMeterRegistry();
        metrics = new RoomMetrics(registry);
    }

    @Test
    void recordRoomCreatedIncrementsCounter() {
        metrics.recordRoomCreated();
        metrics.recordRoomCreated();

        assertThat(registry.get("raceflow.rooms.created").counter().count())
                .isEqualTo(2.0);
    }

    @Test
    void recordJoinAttemptIncrementsTaggedCounter() {
        metrics.recordJoinAttempt("success", registry);
        metrics.recordJoinAttempt("invalid_code", registry);
        metrics.recordJoinAttempt("invalid_code", registry);

        assertThat(registry.get("raceflow.rooms.join.attempts")
                .tag("result", "success").counter().count())
                .isEqualTo(1.0);
        assertThat(registry.get("raceflow.rooms.join.attempts")
                .tag("result", "invalid_code").counter().count())
                .isEqualTo(2.0);
    }

    @Test
    void activeRoomsGaugeTracksIncrementAndDecrement() {
        metrics.incrementActiveRooms();
        metrics.incrementActiveRooms();
        metrics.incrementActiveRooms();
        metrics.decrementActiveRooms();

        assertThat(registry.get("raceflow.rooms.active").gauge().value())
                .isEqualTo(2.0);
    }
}
