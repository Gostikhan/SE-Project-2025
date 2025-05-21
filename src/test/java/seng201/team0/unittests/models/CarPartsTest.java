package seng201.team0.unittests.models;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import seng201.team0.models.CarParts;

public class CarPartsTest {

    @Test
    public void testConstructor() {
        CarParts part = new CarParts("Part x", 2500, "Speed", "Boosts Speed by 1 Level");
        assertEquals("Part x", part.getPartName());
        assertEquals("Boosts Speed by 1 Level", part.getDescription());
        assertEquals("Speed", part.getStatBoostName());
        assertEquals(2500, part.getCost());
    }
    @Test
    public void testSellPrice(){
        CarParts part = new CarParts("Part x", 2000, "Speed", "Boosts Speed by 1 Level");
        assertEquals(1000, part.getSellPrice());
    }
}

