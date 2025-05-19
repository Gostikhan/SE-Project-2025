package seng201.team0.models;

/**\
 * Creates tuning part objects
 */
public class CarParts implements Purchase {
    private String partName;
    private String partDescription;
    private int partCost;
    private String statBoostName;

    /**
     * Creates a tuning part object
     * @param partName Name of the car's part
     * @param partCost Cost of the car's part
     * @param statBoostName Boost Name for the part
     * @param partDescription the description of the car part
     */
    public CarParts(String partName, int partCost, String statBoostName, String partDescription) {
        this.partName = partName;
        this.partCost = partCost;
        this.statBoostName = statBoostName;
        this.partDescription = partDescription;
    }

    /**
     * Gets the part's cost
     * @return returns the cost of the car's part $
     */
    @Override
    public int getCost() {
        return partCost;
    }

    /**
     * Gets the part's sell price
     * @return returns the part price of the car $
     */
    @Override
    public int getSellPrice() {
        return partCost / 2;
    }

    /**
     * Gets the part's description
     * @return returns the car's part description
     */
    @Override
    public String getDescription() {
        return partDescription;
    }

    /**
     * Gets the part's name
     * @return car's part name
     */
    public String getPartName() {
        return partName;
    }

    /**
     * Gets the stat that the car boosts
     * @return car's part boost name
     */
    public String getStatBoostName() {
        return statBoostName;
    }
}