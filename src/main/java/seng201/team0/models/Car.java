package seng201.team0.models;

import java.util.ArrayList;
import java.util.List;


/**
 * Creates car objects
 */
public class Car implements Purchase {

    private final String carName; //Car name variable
    private double carSpeed; //Car speed variable
    private double carHandling; //Car handling variable
    private double carReliability; //Car reliability variable
    private double carFuelEconomy; //Car fuel economy variable
    private final int baseCost; //Car base cost variable
    private int totalValue; //Car total value variable
    private final List<CarParts> installedParts; //List of tuning parts installed on the car

    /**
     * Creates a Car object
     * @param carName Car's name
     * @param carSpeed Car's speed
     * @param carHandling Car's handling
     * @param carReliability Car's reliability
     * @param carFuelEconomy Car's fuel economy
     * @param carCost Car's cost $
     */
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

    /**
     * Gets the cost of a car
     * @return the car cost
     */

    @Override
    public int getCost() {
        return totalValue;
    }

    /**
     * Gets the sell price of a car
     * @return the price of the car
     */
    @Override
    public int getSellPrice() {
        return totalValue / 2;
    }

    /**
     * Gets the name of the car
     * @return the name of the car
     */
    public String getCarName() {
        return carName;
    }

    /**
     * Gets the speed of the car
     * @return the speed of the car
     */
    public double getSpeed() {
        return carSpeed;
    }

    /**
     * Gets the handling of the car
     * @return the handling of the car
     */
    public double getHandling() {
        return carHandling;
    }

    /**
     * Gets the reliability of the car
     * @return the reliability of the car
     */
    public double getReliability() {
        return carReliability;
    }

    /**
     * Gets the fuel economy of the car
     * @return the fuel economy of the car
     */
    public double getFuelEconomy() {
        return carFuelEconomy;
    }

    /**
     * Gets a list of the tuning parts installed on the car
     * @return the installed parts of the car
     */
    public List<CarParts> getInstalledParts() {
        return installedParts;
    }

    /**
     * Increases the value of the car if any tuning parts are installed
     * @param amount amount that increases the car value by
     */
    public void increaseValue(int amount) {
        totalValue += amount;
    }

    /**
     * Applies any upgrades from tuning parts to the car
     * @param part the CarParts to apply to the car
     */
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

    /**
     * Gets the car's description
     * @return returns the desciption of the car
     */
    @Override
    public String getDescription() {
        return String.format("%s - Speed: %.1f, Handling: %s, Reliability: %s, Fuel Economy: %s",
                carName, carSpeed, getReadableHandling(), getReadableReliability(), getReadableFuelEconomy());
    }

    /**
     * Makes the car's handling more readable
     * @return returns Good, Medium, Poor
     */
    public String getReadableHandling() {
        if (carHandling >= 90) return "Good";
        else if (carHandling >= 80) return "Medium";
        else return "Poor";
    }

    /**
     * Makes the car's fuel economy more readable
     * @return  returns Good, Medium, Poor
     */
    public String getReadableFuelEconomy() {
        if (carFuelEconomy >= 500) return "Good";
        else if (carFuelEconomy >= 400) return "Medium";
        else return "Bad";
    }

    /**
     * Makes the car's reliability more readable
     * @return returns Good, Medium, Poor
     */
    public String getReadableReliability() {
        if (carReliability >= 90) return "Good";
        else if (carReliability >= 75) return "Medium";
        else return "Bad";
    }
}