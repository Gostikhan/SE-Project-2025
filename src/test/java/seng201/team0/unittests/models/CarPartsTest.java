package seng201.team0.unittests.models;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import seng201.team0.models.CarParts;

public class CarPartsTest {

    @Test
    public void testConstructor() {
        CarParts part = new CarParts("Part x", 2500, "Speed", "Boosts Speed by 1 Level");
        assertEquals("Nitrous", part.getPartName());
        assertEquals("Extra boost", part.getDescription());
        assertEquals(2500, part.getCost());
    }
}

