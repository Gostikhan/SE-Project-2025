package seng201.team0.models;

public class Race {
    private int hours;
    private int aiEntries;
    private int prizeMoney;
    //private List<Route> routes; (different route options)
    public Race(int hours, int aiEntries, int prizeMoney) {
        this.hours = hours;
        this.aiEntries = aiEntries;
        this.prizeMoney = prizeMoney;
    }
    public int getHours() {
        return hours;
    }
    public void setHours(int hours) {
        this.hours = hours;
    }
    public int getAiEntries() {
        return aiEntries;
    }
    public int getPrizeMoney() {
        return prizeMoney;
    }
    public String raceDetails() {
        return String.format("Race - %d hours, %d opponents, $%d prize", hours, aiEntries, prizeMoney);
    }
}
