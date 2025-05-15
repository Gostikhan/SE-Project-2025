package seng201.team0.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Creates race objects
 */
public class Race {
    private int hours;
    private int aiEntries;
    private int prizeMoney;
    private final List<RaceRoute> routes = new ArrayList<>();

    /**
     * //Creates a race object
     * @param hours Variable that is how long the race is expected to take
     * @param aiEntries Variable that is the amount of other racers not including the player
     * @param prizeMoney Variable that is the prize money won in the race
     */
    public Race(int hours, int aiEntries, int prizeMoney) {
        this.hours = hours;
        this.aiEntries = aiEntries;
        this.prizeMoney = prizeMoney;
    }

    /**
     * Gets how the long the race is expected to take
     * @return returns hours of the race
     */
    public int getHours() {
        return hours;
    }

    /**
     * Gets the amount of other racers not including the player
     * @return returns number of bots entries in the car
     */
    public int getAiEntries() {
        return aiEntries;
    }

    /**
     * Gets the prize money won from a race
     * @return returns the price money $
     */
    public int getPrizeMoney() {
        return prizeMoney;
    }

    /**
     * Adds a route to the race
     * @param route route of teh race
     */
    public void addRoute(RaceRoute route) {
        routes.add(route);
    }

    /**
     * Gets the routes in a race
     * @return returns the race routes
     */
    public List<RaceRoute> getRoutes() {
        return routes;
    }

    /**
     * Gets a race's description
     * @return returns the details of the race: "Race - Duration: " + hours + "h, Prize: $" +
     * prizeMoney + ", Entries: " + aiEntries;
     */
    public String raceDetails() {
        return "Race - Duration: " + hours + "h, Prize: $" + prizeMoney + ", Entries: " + aiEntries;
    }
}