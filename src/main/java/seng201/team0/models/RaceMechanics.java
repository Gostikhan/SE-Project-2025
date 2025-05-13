package seng201.team0.models;

import java.util.Random;

public class RaceMechanics {
    private final Random random = new Random();

    public RaceResult runRace(Car playerCar, Race race, RaceRoute route){
        double adjustSpeed = playerCar.getSpeed() * (1 - route.getRaceDifficulty() * 0.1);
        double adjustHandling = playerCar.getHandling() * (1 - route.getRaceDifficulty() * 0.1);
        double adjustReliability = playerCar.getReliability();
        double adjustFuelEconomy = playerCar.getFuelEconomy() * (1 - route.getRaceDifficulty() * 0.1);
        //ChatGPT was used to create the formula
        double totalDistance = route.getRaceDistance();
        int fuelStops = route.getFuelStops();
        double fuelRange = adjustFuelEconomy;
        double time = 0;
        double distanceCovered = 0;
        double distancePerFuelStop = totalDistance / (fuelStops + 1);

        int currentStop = 0;
        boolean carBrokeDown = false;
        boolean refueled;

        return null; //just for now to escape errors
    }
}
