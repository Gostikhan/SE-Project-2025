package seng201.team0.models;

import java.util.ArrayList;
import java.util.List;
//Creates race objects
public class Race {
    private int hours; //Variable that is how long the race is expected to take
    private int aiEntries; //Variable that is the amount of other racers not including the player
    private int prizeMoney; //Variable that is the prize money won in the race
    private final List<RaceRoute> routes = new ArrayList<>();

    //Creates a race object
    public Race(int hours, int aiEntries, int prizeMoney) {
        this.hours = hours;
        this.aiEntries = aiEntries;
        this.prizeMoney = prizeMoney;
    }

    //Gets how the long the race is expected to take
    public int getHours() {
        return hours;
    }
    //Gets the amount of other racers not including the player
    public int getAiEntries() {
        return aiEntries;
    }
    //Gets the prize money won from a race
    public int getPrizeMoney() {
        return prizeMoney;
    }
    //Adds a route to the race
    public void addRoute(RaceRoute route) {
        routes.add(route);
    }
    //Gets the routes in a race
    public List<RaceRoute> getRoutes() {
        return routes;
    }
    //Gets a race's description
    public String raceDetails() {
        return "Race - Duration: " + hours + "h, Prize: $" + prizeMoney + ", Entries: " + aiEntries;
    }
}