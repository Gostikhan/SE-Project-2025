package seng201.team0.unittests.services;

import org.junit.jupiter.api.Test;
import seng201.team0.models.Car;
import seng201.team0.models.CarParts;
import seng201.team0.services.GameEnvironment;

import static org.junit.jupiter.api.Assertions.*;

public class GameEnvironmentTest {
    @Test
    public void testSetPlayerName(){
        GameEnvironment game = new GameEnvironment();
        game.setPlayerName("Alena");
        assertEquals("Alena", game.getPlayerName());
    }
    @Test
    public void testsetDifficulty() {
        GameEnvironment game = new GameEnvironment();
        game.setDifficulty("Easy");
        assertEquals(250000, game.getBalance());

        game.setDifficulty("Hard");
        assertEquals(200000, game.getBalance());
    }
    @Test
    public void testcanPurchase() {
        GameEnvironment game = new GameEnvironment();
        game.setDifficulty("Hard");
        Car car = game.getAvailableCars().get(0);
        assertTrue(game.canPurchase(car));
    }
    @Test
    public void testcanSellCar() {
        GameEnvironment game = new GameEnvironment();
        game.setDifficulty("Hard");
        Car car = game.getAvailableCars().get(0);
        game.purchaseCar(car);
        assertFalse(game.canSellCar());

        Car car2 = game.getAvailableCars().get(1);
        game.purchaseCar(car2);
        assertTrue(game.canSellCar());
    }
    @Test
    public void testcanSellPart(){
        GameEnvironment game = new GameEnvironment();
        game.setDifficulty("Easy");
        CarParts part = game.getAvailableParts().get(0);
        game.purchasePart(part);
        assertTrue(game.canSellPart(part));
    }
    @Test
    public void testsetSeasonLength(){
        GameEnvironment game = new GameEnvironment();
        int seasonLength = 1;
        game.setSeasonLength(seasonLength);
        int actualSeasonLength = game.getSeasonLength();
        assertEquals(seasonLength, actualSeasonLength);
    }
    @Test
    public void testaddBalance(){
        GameEnvironment game = new GameEnvironment();
        game.setDifficulty("Hard");
        int amount = 2000;
        game.addBalance(amount);
        assertEquals(202000, game.getBalance());
    }
    @Test
    public void testsetSelectedRacingCar(){
        GameEnvironment game = new GameEnvironment();
        game.setDifficulty("Easy");

        Car car = game.getAvailableCars().get(0);
        game.purchaseCar(car);
        game.setSelectedRacingCar(car);
        assertEquals(car, game.getSelectedRacingCar());

    }
    @Test
    public void testisSeasonOver(){
        GameEnvironment game = new GameEnvironment();
        game.setSeasonLength(5);
        assertFalse(game.isSeasonOver());
        game.incrementRacesCompleted();
        game.incrementRacesCompleted();
        game.incrementRacesCompleted();
        game.incrementRacesCompleted();
        game.incrementRacesCompleted();
        assertTrue(game.isSeasonOver());
    }
    @Test
    public void testownsParts(){
        GameEnvironment game = new GameEnvironment();
        game.setDifficulty("Easy");
        CarParts part = game.getAvailableParts().get(0);
        game.purchasePart(part);
        game.purchasePart(part);
        assertTrue(game.ownsPart(part));
    }
}
