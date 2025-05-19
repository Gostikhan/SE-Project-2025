package seng201.team0.unittests.models;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import seng201.team0.models.RaceRoute;

public class RaceRouteTest {

    @Test
    public void testRouteStats() {
        RaceRoute route = new RaceRoute("Route A for Race x", 150, 3, 1.8);
        assertEquals("Route A for Race x", route.getRaceDescription());
        assertEquals(150, route.getRaceDistance());
        assertEquals(3, route.getFuelStops());
    }
}

