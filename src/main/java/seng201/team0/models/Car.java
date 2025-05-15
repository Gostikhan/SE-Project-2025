package seng201.team0.models;

import java.util.ArrayList;
import java.util.List;
//Creates car objects
public class Car implements Purchase {

    private final String carName; //Car name variable
    private double carSpeed; //Car speed variable
    private double carHandling; //Car handling variable
    private double carReliability; //Car reliability variable
    private double carFuelEconomy; //Car fuel economy variable
    private final int baseCost; //Car base cost variable
    private int totalValue; //Car total value variable
    private final List<CarParts> installedParts; //List of tuning parts installed on the car

    //Creates the car object
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

    //Gets the cost of a car
    @Override
    public int getCost() {
        return totalValue;
    }
    //Gets the sell price of a car
    @Override
    public int getSellPrice() {
        return totalValue / 2;
    }
    //Gets the name of the cra
    public String getCarName() {
        return carName;
    }
    //Gets the speed of the car
    public double getSpeed() {
        return carSpeed;
    }
    //Gets the handling of the car
    public double getHandling() {
        return carHandling;
    }
    //Gets the reliability of the car
    public double getReliability() {
        return carReliability;
    }
    //Gets the fuel economy of the car
    public double getFuelEconomy() {
        return carFuelEconomy;
    }
    //Gets a list of the tuning parts installed on the car
    public List<CarParts> getInstalledParts() {
        return installedParts;
    }
    //Increases the value of the car if any tuning parts are installed
    public void increaseValue(int amount) {
        totalValue += amount;
    }

    //Applies any upgrades from tuning parts to the car
    public void applyUpgrade(CarParts part) {
        switch (part.getStatBoostName().toLowerCase()) {
            case "speed": carSpeed += 1; break;
            case "handling": carHandling += 1; break;
            case "reliability": carReliability += 1; break;
            case "fuel economy": carFuelEconomy += 1; break;
        }
        installedParts.add(part);
        increaseValue(part.getCost() / 2);
    }

    //Gets the car's description
    @Override
    public String getDescription() {
        return String.format("%s - Speed: %.1f, Handling: %s, Reliability: %s, Fuel Economy: %s",
                carName, carSpeed, getReadableHandling(), getReadableReliability(), getReadableFuelEconomy());
    }
    //Makes the car's handling more readable
    public String getReadableHandling() {
        if (carHandling >= 90) return "Good";
        else if (carHandling >= 80) return "Medium";
        else return "Poor";
    }
    //Makes the car's fuel economy more readable
    public String getReadableFuelEconomy() {
        if (carFuelEconomy >= 500) return "Good";
        else if (carFuelEconomy >= 400) return "Medium";
        else return "Bad";
    }
    //Makes the car's reliability more readable
    public String getReadableReliability() {
        if (carReliability >= 90) return "Good";
        else if (carReliability >= 75) return "Medium";
        else return "Bad";
    }
}