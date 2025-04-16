package seng201.team0.models;

public class RaceRoute {
    private String raceDescription;
    private double raceDistance;
    private int fuelStops;
    private double raceDifficulty;

    public RaceRoute(String raceDescription, double raceDistance, int fuelStops, double raceDifficulty) {
        this.raceDescription = raceDescription;
        this.raceDistance = raceDistance;
        this.fuelStops = fuelStops;
        this.raceDifficulty = raceDifficulty;
    }
    public String getRaceDescription() {
        return raceDescription;
    }
    public double getRaceDistance() {
        return raceDistance;
    }
    public int getFuelStops() {
        return fuelStops;
    }
    public double getRaceDifficulty() {
        return raceDifficulty;
    }

}
