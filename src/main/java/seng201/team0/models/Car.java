package seng201.team0.models;
import java.util.ArrayList;
import java.util.List;

public class Car implements Purchase {
    private final String carName;
    private double carSpeed;
    private double carHandling;
    private double carReliability;
    private double carFuelEconomy;
    private final int baseCost;
    private int totalValue;
    private final List<CarParts> installedParts;

    public Car(String carName, double carSpeed, double carHandling, double carReliability, double carFuelEconomy, int carCost) {
        this.carName = carName;
        this.carSpeed = carSpeed;
        this.carHandling = carHandling;
        this.carReliability = carReliability;
        this.carFuelEconomy = carFuelEconomy;
        this.baseCost = carCost;
        this.totalValue = carCost;
        this.installedParts = new ArrayList<>();
    }

    //Car Stats
    @Override
    public int getCost() {
        return totalValue;
    }
    @Override
    public int getSellPrice() {
        return totalValue / 2;
    }
    public String getCarName() {
        return carName;
    }
    public double getSpeed() {
        return carSpeed;
    }
    public double getHandling() {
        return carHandling;
    }
    public double getReliability() {
        return carReliability;
    }
    public double getFuelEconomy() {
        return carFuelEconomy;
    }

    //Upgrades
    public List<CarParts> getInstalledParts() {
        return installedParts;
    }
    public void increaseValue(int amount) {
        totalValue += amount;
    }
    public void applyUpgrade(CarParts part) {
        switch (part.getStatBoostName().toLowerCase()) {
            case "speed":
                carSpeed += 1;
                break;
            case "handling":
                carHandling += 1;
                break;
            case "reliability":
                carReliability += 1;
                break;
            case "fuel economy":
                carFuelEconomy += 1;
                break;
            default:
                break;
        }
        installedParts.add(part);
        increaseValue(part.getCost() / 2);
    }

    //Car Description
    @Override
    public String getDescription() {
        return String.format("%s - Speed: %.1f, Handling: %.1f, Reliability: %.1f%%, Fuel: %.1fkm",
                carName, carSpeed, carHandling, carReliability, carFuelEconomy);
    }
}

