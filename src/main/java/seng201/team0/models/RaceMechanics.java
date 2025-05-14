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
        boolean carRetired = false;

        while (distanceCovered < totalDistance ||  time < race.getHours()) {
            if (random.nextDouble() < (1 - adjustReliability / 100)) {
                carBrokeDown = true;
                if (random.nextBoolean()) {
                    time += 0.5;
                    System.out.println("Car broke down");
                }
                else {
                    carRetired = true;
                    System.out.println("Car retired");
                    break;
                }
            }
            if (fuelRange <= 0) {
                carRetired = true;
                System.out.println("Car ran out of fuel");
                break;
            }
            if (carRetired || carBrokeDown){
                return new RaceResult(false, "Did not finish the race", -1, 0);
            }

        }
        return new RaceResult(true, "Finished the race", 1, 10000);
    }
}
