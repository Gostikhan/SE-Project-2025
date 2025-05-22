package seng201.team0.models;

/**
 * Controller for Race Route
 */
public class RaceRoute {
    private String raceDescription;
    private double raceDistance;
    private int fuelStops;
    private double raceDifficulty;

    /**
     * Race Route constructor
     * @param raceDescription description of the race
     * @param raceDistance the distance of the race
     * @param fuelStops the fuel stops in the race
     * @param raceDifficulty difficulty of the race
     */
    public RaceRoute(String raceDescription, double raceDistance, int fuelStops, double raceDifficulty) {
        this.raceDescription = raceDescription;
        this.raceDistance = raceDistance;
        this.fuelStops = fuelStops;
        this.raceDifficulty = raceDifficulty;
    }

    /**
     * Race Description
     * @return returns the description of the race in String
     */
    public String getRaceDescription() {
        return raceDescription;
    }

    /**
     * Race Distance
     * @return returns the race distance
     */
    public double getRaceDistance() {
        return raceDistance;
    }

    /**
     * Fuel stops
     * @return returns fuel stops in the race
     */
    public int getFuelStops() {
        return fuelStops;
    }

    /**
     * Race difficulty
     * @return returns race Difficulty
     */
    public double getRaceDifficulty() {
        return raceDifficulty;
    }

}
