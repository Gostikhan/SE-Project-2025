package seng201.team0.models;

public class CarParts implements Purchase {
    private String partName;
    private String partDescription;
    private int partCost;
    private String statBoostName; // "Speed", "Handling", etc.

    public CarParts(String partName, int partCost, String statBoostName, String partDescription) {
        this.partName = partName;
        this.partCost = partCost;
        this.statBoostName = statBoostName;
        this.partDescription = partDescription;
    }

    @Override
    public int getCost() {
        return partCost;
    }

    @Override
    public int getSellPrice() {
        return partCost / 2;
    }

    @Override
    public String getDescription() {
        return partDescription;
    }

    public String getPartName() {
        return partName;
    }

    public String getStatBoostName() {
        return statBoostName;
    }
    
}
