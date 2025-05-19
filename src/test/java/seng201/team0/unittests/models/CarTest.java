package seng201.team0.unittests.models;

import org.junit.jupiter.api.Assertions;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seng201.team0.models.Car;
import seng201.team0.models.CarParts;

public class CarTest {
    private Car car;

    @BeforeEach
    public void setUp() {
        car = new Car("Racer", 100, 80, 90, 400, 30000);
    }

    @Test
    public void testInitialStats() {
        Assertions.assertEquals("Car x", car.getCarName());
        Assertions.assertEquals(100, car.getSpeed());
        Assertions.assertEquals(80, car.getHandling());
        Assertions.assertEquals(90, car.getReliability());
        Assertions.assertEquals(400, car.getFuelEconomy());
        assertEquals(30000, car.getSellPrice());
        assertTrue(car.getInstalledParts().isEmpty());
    }

    @Test
    public void testAddAndRemoveUpgrade() {
        CarParts part = new CarParts("Part x", 5000, "Speed", "Boosts Speed by 1 Level");
        car.applyUpgrade(part);
        assertEquals(1, car.getInstalledParts().size());
        assertTrue(car.getInstalledParts().contains(part));
    }
}