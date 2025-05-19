package seng201.team0.unittests.models;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import seng201.team0.models.Race;
import seng201.team0.models.RaceRoute;

import java.util.Arrays;

public class RaceTest {

    @Test
    public void testRaceSetup() {
        RaceRoute route1 = new RaceRoute("Route A for Race x", 100, 2, 1.8);
        Race race = new Race(5, 3, 20000);
        assertEquals(5, race.getHours());
        assertEquals(20000, race.getPrizeMoney());
        assertEquals(1, race.getRoutes().size());
        assertEquals(3, race.getAiEntries());
    }
}
