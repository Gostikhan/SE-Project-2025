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
        car = new Car("Car x", 100, 80, 90, 400, 30000);
    }

    @Test
    public void testInitialStats() {
        Assertions.assertEquals("Car x", car.getCarName());
        Assertions.assertEquals(100, car.getSpeed());
        Assertions.assertEquals(80, car.getHandling());
        Assertions.assertEquals(90, car.getReliability());
        Assertions.assertEquals(400, car.getFuelEconomy());
        assertEquals(30000, car.getCost());
        assertEquals("Car x", car.getCarName());
        assertEquals(100, car.getSpeed());
        assertEquals(80, car.getHandling());
        assertEquals(90, car.getReliability());
        assertEquals(400, car.getFuelEconomy());
        assertTrue(car.getInstalledParts().isEmpty());
    }

    @Test
    public void testAddAndRemoveUpgrade() {
        CarParts part = new CarParts("Part x", 5000, "Speed", "Boosts Speed by 1 Level");
        car.applyUpgrade(part);
        assertEquals(1, car.getInstalledParts().size());
        assertTrue(car.getInstalledParts().contains(part));
    }
    @Test
    public void testReadableHandling() {
        car = new Car("Car w", 80, 60, 95, 410, 15000);
        assertEquals("Poor", car.getReadableHandling());
        car = new Car("Car Metro", 80, 100, 70, 300, 10000);
        assertEquals("Good", car.getReadableHandling());
        car = new Car("Orange Car", 80, 80, 80, 400, 40000);
        assertEquals("Medium", car.getReadableHandling());
    }
    @Test
    public void testReadableFuelEconomy() {
        car = new Car("Car Java", 100, 80, 90, 100, 35000);
        assertEquals("Bad", car.getReadableFuelEconomy());
        car = new Car("Red Car", 80, 80, 80, 420, 40000);
        assertEquals("Medium", car.getReadableFuelEconomy());
        car = new Car("Black Car", 80, 80, 80, 501, 40000);
        assertEquals("Good", car.getReadableFuelEconomy());
    }
    @Test
    public void testReadableReliability() {
        car = new Car("Car xy", 50, 70, 91, 200, 30000);
        assertEquals("Good", car.getReadableReliability());
        car = new Car("Purple Car", 80, 80, 80, 400, 40000);
        assertEquals("Medium", car.getReadableReliability());
        car = new Car("Triple Orange Car", 80, 80, 71, 501, 40000);
        assertEquals("Bad", car.getReadableReliability());
    }
}