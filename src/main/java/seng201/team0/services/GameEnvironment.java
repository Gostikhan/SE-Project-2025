package seng201.team0.services;

import seng201.team0.models.Car;
import seng201.team0.models.CarParts;
import java.util.ArrayList;
import java.util.List;

public class GameEnvironment {
    private String playerName;
    private int seasonLength;
    private String difficulty;
    private int balance;
    private final List<Car> ownedCars = new ArrayList<>();
    private final List<CarParts> unequippedParts = new ArrayList<>();
    private Car selectedRacingCar;
    private final List<Car> availableCars = new ArrayList<>();

    //Setup
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
    public void addBalance(int amount) {
        this.balance += amount;
    }

    //Cars
    public List<Car> getOwnedCars() {
        return ownedCars;
    }
    public boolean canPurchase(Car car) {
        return ownedCars.size() < 5 && balance >= car.getCost();
    }
    public void purchaseCar(Car car) {
        if (canPurchase(car)) {
            balance -= car.getCost();
            ownedCars.add(car);
        }
    }
    public boolean canSellCar() {
        return ownedCars.size() > 1;
    }
    public void sellCar(Car car) {
        if (ownedCars.contains(car) && canSellCar()) {
            balance += car.getSellPrice();
            ownedCars.remove(car);
            if (selectedRacingCar == car) {
                selectedRacingCar = ownedCars.isEmpty() ? null : ownedCars.get(0);
            }
        }
    }
    public GameEnvironment() {
        balance = 0;
        availableCars.add(new Car("Tempest", 215, 78, 93, 410, 4500));
        availableCars.add(new Car("Interceptor", 205, 83, 91, 395, 4200));
        availableCars.add(new Car("Drift King", 190, 89, 94, 385, 4000));
    //Car names were generated using ChatGPT
    }
    public List<Car> getAvailableCars() {
        return availableCars;
    }

    //Tuning Parts
    public List<CarParts> getUnequippedParts() {
        return unequippedParts;
    }
    public boolean canPurchasePart(CarParts part) {
        return unequippedParts.size() < 3 && balance >= part.getCost();
    }
    public void purchasePart(CarParts part) {
        if (canPurchasePart(part)) {
            balance -= part.getCost();
            unequippedParts.add(part);
        }
    }
    public List<CarParts> getAvailableParts() {
        List<CarParts> parts = new ArrayList<>();
        parts.add(new CarParts("Turbo Boost", 5000, "Speed", "Increases speed by +1."));
        parts.add(new CarParts("Performance Brakes", 4000, "Handling", "Increases handling by +1."));
        parts.add(new CarParts("Suspension Kit", 4500, "Reliability", "Increases reliability by +1."));
        return parts;
    }
    public boolean canSellPart(CarParts part) {
        return unequippedParts.contains(part);
    }
    public void sellPart(CarParts part) {
        if (canSellPart(part)) {
            balance += part.getSellPrice();
            unequippedParts.remove(part);
        }
    }

    //Tuning Part Installation
    public boolean canInstallPart(CarParts part, Car car) {
        return unequippedParts.contains(part) && ownedCars.contains(car);
    }
    public void installPartToCar(CarParts part, Car car) {
        if (canInstallPart(part, car)) {
            car.applyUpgrade(part);
            car.increaseValue(part.getCost() / 2);
            unequippedParts.remove(part);
        }
    }

    //Car Selection
    public void setSelectedRacingCar(Car car) {
        if (ownedCars.contains(car)) {
            this.selectedRacingCar = car;
        }
    }
    public Car getSelectedRacingCar() {
        return selectedRacingCar;
    }
    public Car getRacingCar() {
        return getSelectedRacingCar();
    }

    //Owned Tuning Parts and Cars
    public boolean ownsCar(Car car) {
        return ownedCars.contains(car);
    }
    public boolean ownsPart(CarParts part) {
        return unequippedParts.contains(part);
    }
}