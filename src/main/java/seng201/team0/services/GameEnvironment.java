package seng201.team0.services;
import seng201.team0.models.Car;
import java.util.ArrayList;
import java.util.List;

public class GameEnvironment {
    private String playerName;
    private int seasonLength;
    private String difficulty;
    private int balance;
    private final List<Car> ownedCars = new ArrayList<>();

    public GameEnvironment() {
        balance = 0;
    }
    public void setPlayerName(String name) {
        this.playerName = name;
    }
    public String getPlayerName() {
        return playerName;
    }
    public void setSeasonLength(int length) {
        this.seasonLength = length;
    }
    public int getSeasonLength() {
        return seasonLength;
    }
    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
        if (difficulty.equals("Easy")) {
            this.balance = 250000;
        } else {
            this.balance = 200000;
        }
    }
    public String getDifficulty() {
        return difficulty;
    }
    public int getBalance() {
        return balance;
    }
    public void purchaseCar(Car car) {
        if (balance >= car.getCost()) if (ownedCars.size() < 3) {
            balance -= car.getCost();
            ownedCars.add(car);
        }
    }
    public List<Car> getOwnedCars() {
        return ownedCars;
    }
    public boolean canPurchase(Car car) {
        return ownedCars.size() < 3 && balance >= car.getCost();
    }
}

