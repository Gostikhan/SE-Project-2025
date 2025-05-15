package seng201.team0.models;
//Creates tuning part objects
public class CarParts implements Purchase {
    private String partName;
    private String partDescription;
    private int partCost;
    private String statBoostName;

    //Creates a tuning part object
    public CarParts(String partName, int partCost, String statBoostName, String partDescription) {
        this.partName = partName;
        this.partCost = partCost;
        this.statBoostName = statBoostName;
        this.partDescription = partDescription;
    }

    //Gets the part's cost
    @Override
    public int getCost() {
        return partCost;
    }
    //Gets the part's sell price
    @Override
    public int getSellPrice() {
        return partCost / 2;
    }
    //Gets the part's description
    @Override
    public String getDescription() {
        return partDescription;
    }
    //Gets the part's name
    public String getPartName() {
        return partName;
    }
    //Gets the stat that the car boosts
    public String getStatBoostName() {
        return statBoostName;
    }
}