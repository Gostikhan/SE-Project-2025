package seng201.team0.models;

public class Car implements Purchase {
    private String carName;
    private double carSpeed;
    private double carHandling;
    private double carReliability;
    private double carfuelEconomy;
    private int carCost;
    //private List<Part> upgrades (Will be our installed parts)

    public Car(String carName, double carSpeed, double carHandling, double carReliability, double carfuelEconomy, int carCost) {
        this.carName = carName;
        this.carSpeed = carSpeed;
        this.carHandling = carHandling;
        this.carReliability = carReliability;
        this.carfuelEconomy = carfuelEconomy;
        this.carCost = carCost;

    }
    @Override
    public int getCost() {
        return carCost;
    }
    @Override
    public int getSellPrice() {
        return carCost / 2;
    }
    public String getCarName() {
        return carName;
    }
    @Override
    public String getDescription() {
        return String.format("%s - Speed: %.1f, Handling: %.1f, Reliability: %.1f%%, Fuel: %.1fkm",
                carName, carSpeed, carHandling, carReliability, carfuelEconomy);
    }

}
