package seng201.team0.services;

import seng201.team0.models.Car;
import seng201.team0.models.CarParts;
import seng201.team0.models.Race;
import seng201.team0.models.RaceRoute;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * The environment that the game runs in,
 * Keeps track of everything and generates, races, parts and cars when the game is started
 */

public class GameEnvironment {
    private String playerName; //Variable for the player's name
    private int seasonLength; //Variable for the season length
    private String difficulty; //Variable for the difficulty
    private int balance; //Variable for the player's balance

    private final List<Car> ownedCars = new ArrayList<>(); //List of owned cars
    private final List<CarParts> unequippedParts = new ArrayList<>(); //List of tuning parts
    private final List<Car> availableCars = new ArrayList<>(); //List of available cars
    private final List<CarParts> ownedParts = new ArrayList<>();
    private final List<Race> allRaces = new ArrayList<>(); //List of races
    private final List<Race> currentRaces = new ArrayList<>(); //List of current races that the player can choose from
    private final List<Integer> racePlacings = new ArrayList<>(); //List of the all the player's placings

    private final Random random = new Random(); //Random number generator
    private int racesCompleted = 0; //Amount of races completed
    private Car selectedRacingCar; //The selected car for racing

    /**
     * Runs when the game starts, sets balance, generate cars, parts and races
     */
    public GameEnvironment() {
        balance = 0;
        generateCars();
        generateParts();
        generateRaces();
        refreshCurrentRaces();
    }

    /**
     * Generates cars
     */
    private void generateCars() {
        int[] handlingValues = {70, 80, 90};
        int[] speedValues = {85, 100, 115};
        int[] fuelValues = {300, 400, 500};
        int[] reliabilityValues = {50, 75, 90};
        for (int i = 1; i <= 10; i++) {
            int h = random.nextInt(3);
            int s = random.nextInt(3);
            int f = random.nextInt(3);
            int r = random.nextInt(3);
            int baseCost = 2000 + (h + s + f + r) * 500;
            Car car = new Car("Car " + i,
                    speedValues[s],
                    handlingValues[h],
                    reliabilityValues[r],
                    fuelValues[f],
                    baseCost);
            availableCars.add(car);
        }
        Collections.shuffle(availableCars);
    }

    /**
     * Generates tuning parts
     */
    private void generateParts() {
        String[] stats = {"Speed", "Handling", "Reliability", "Fuel Economy"};
        for (int i = 1; i <= 25; i++) {
            String stat = stats[random.nextInt(stats.length)];
            int cost = 3000 + random.nextInt(2000);
            CarParts part = new CarParts("Part " + i, cost, stat, "Boosts " + stat + " by 1 level.");
            unequippedParts.add(part);
        }
        Collections.shuffle(unequippedParts);
    }

    /**
     * Generates races
     */
    public void generateRaces() {
        allRaces.clear();
        for (int i = 1; i <= 15; i++) {
            Race race = new Race(4 + random.nextInt(4), 3 + random.nextInt(4), 10000 + random.nextInt(15000));
            race.addRoute(new RaceRoute("Route A for Race " + i, 400 + random.nextInt(200), 1 + random.nextInt(3), 1.0 + random.nextDouble()));
            race.addRoute(new RaceRoute("Route B for Race " + i, 400 + random.nextInt(200), 1 + random.nextInt(3), 1.0 + random.nextDouble()));
            allRaces.add(race);
        }
    }

    /**
     * Shows the player new races to choose from
     */
    public void refreshCurrentRaces() {
        currentRaces.clear();
        List<Race> shuffled = new ArrayList<>(allRaces);
        Collections.shuffle(shuffled);
        for (int i = 0; i < 3 && i < shuffled.size(); i++) {
            currentRaces.add(shuffled.get(i));
        }
    }

    /**
     * Gets the currently selected race
     * @return returns current Races
     */
    public List<Race> getCurrentRaces() {
        return currentRaces;
    }

    /**
     * Sets the player's name
     * @param name sets player's name
     */
    public void setPlayerName(String name) {
        this.playerName = name;
    }

    /**
     * Gets the player's name
     * @return returns player's name
     */
    public String getPlayerName() {
        return playerName;
    }

    /**
     * Sets the season length
     * @param length season length
     */
    public void setSeasonLength(int length) {
        this.seasonLength = length;
    }

    /**
     * Gets the season length
     * @return season length
     */
    public int getSeasonLength() {
        return seasonLength;
    }

    /**
     * Sets the difficulty
     * @param difficulty the difficulty in the game, "Easy" and "Hard"
     */
    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
        if (difficulty.equals("Easy")) {
            this.balance = 250000;
        } else {
            this.balance = 200000;
        }
    }

    /**
     * Gets the difficulty
     * @return returns difficulty of the game
     */
    public String getDifficulty() {
        return difficulty;
    }

    /**
     * Gets the player's balance
     * @return returns player's balance
     */
    public int getBalance() {
        return balance;
    }

    /**
     * Adds to the players balance
     * @param amount $ to players balance
     */
    public void addBalance(int amount) {
        this.balance += amount;
    }

    /**
     * Gets a list of the player's owned cars
     * @return returns player's owned cars
     */
    public List<Car> getOwnedCars() {
        return ownedCars;
    }

    /**
     * Whether a player can purchase the selected car
     * @param car gets Car class details
     * @return returns True or False
     */
    public boolean canPurchase(Car car) {
        return ownedCars.size() < 5 && balance >= car.getCost();
    }

    /**
     * Lets the player purchase the selected car
     * @param car Car class details
     */
    public void purchaseCar(Car car) {
        if (canPurchase(car)) {
            balance -= car.getCost();
            ownedCars.add(car);
        }
    }

    /**
     * Whether a player can sell the selected car
     * @return returns True or False
     */
    public boolean canSellCar() {
        return ownedCars.size() > 1;
    }

    /**
     * Lets the player sell the selected car
     * @param car Car class details
     */
    public void sellCar(Car car) {
        if (ownedCars.contains(car) && canSellCar()) {
            balance += car.getSellPrice();
            ownedCars.remove(car);
            if (selectedRacingCar == car) {
                selectedRacingCar = ownedCars.isEmpty() ? null : ownedCars.get(0);
            }
        }
    }

    /**
     * Gets the available cars in the shop
     * @return returns available cars
     */
    public List<Car> getAvailableCars() {
        return availableCars;
    }

    /**
     * Gets the available tuning parts in the shop
     * @return returns unequipped parts
     */
    public List<CarParts> getUnequippedParts() {
        return unequippedParts;
    }

    /**
     * Whether a player can purchase a tuning part
     * @param part Car parts class details
     * @return returns True or False
     */
    public boolean canPurchasePart(CarParts part) {
        return unequippedParts.contains(part) && ownedParts.size() < 3 && balance >= part.getCost();
    }

    /**
     * Lets the player purchase a tuning part
     * @param part Car parts class details
     */
    public void purchasePart(CarParts part) {
        if (canPurchasePart(part)) {
            balance -= part.getCost();
            unequippedParts.remove(part);
            ownedParts.add(part);
        }
    }

    /**
     * Gets a list of available tuning parts in the shop
     * @return returns parts
     */
    public List<CarParts> getAvailableParts() {
        List<CarParts> parts = new ArrayList<>(unequippedParts);
        Collections.shuffle(parts);
        return parts.subList(0, Math.min(3, parts.size()));
    }

    /**
     * Owned parts that player have or will have
     * @return returns the parts that player owns
     */
    public List<CarParts> getOwnedParts() {
        return ownedParts;
    }

    /**
     * Whether a player can sell a tuning part
     * @param part Car parts class details
     * @return returns True or False
     */
    public boolean canSellPart(CarParts part) {
        return ownedParts.contains(part);
    }

    /**
     * Allows the player to sell a tuning part
     * @param part Car parts class details
     */
    public void sellPart(CarParts part) {
        if (canSellPart(part)) {
            balance += part.getSellPrice();
            ownedParts.remove(part);
            unequippedParts.add(part);
        }
    }

    /**
     * Whether a player can install a tuning part
     * @param part Car parts class details
     * @param car Car c;ass details
     * @return returns True or False
     */
    public boolean canInstallPart(CarParts part, Car car) {
        return ownedParts.contains(part) && ownedCars.contains(car);
    }

    /**
     * Allows the player to install a tuning part
     * @param part Car parts class details
     * @param car Car c;ass details
     */
    public void installPartToCar(CarParts part, Car car) {
        if (canInstallPart(part, car)) {
            car.applyUpgrade(part);
            car.increaseValue(part.getCost() / 2);
            ownedParts.remove(part);
        }
    }

    /**
     * Sets the selected car for racing
     * @param car Car class details
     */
    public void setSelectedRacingCar(Car car) {
        if (ownedCars.contains(car)) {
            this.selectedRacingCar = car;
        }
    }

    /**
     * Gets the selected car for racing
     * @return returns selected racing car
     */
    public Car getSelectedRacingCar() {
        return selectedRacingCar;
    }

    /**
     * Get the current car that is selected for racing
     * @return returns selected racing car
     */
    public Car getRacingCar() {
        return getSelectedRacingCar();
    }

    /**
     * Whether a player owns a car
     * @param car Car class details
     * @return returns True or False
     */
    public boolean ownsCar(Car car) {
        return ownedCars.contains(car);
    }

    /**
     * Whether a player owns a tuning part
     * @param part Car parts class details
     * @return returns True or False
     */
    public boolean ownsPart(CarParts part) {
        return unequippedParts.contains(part);
    }

    /**
     * Increases the amount of completed races by 1
     */
    public void incrementRacesCompleted() {
        racesCompleted++;
    }

    /**
     * Gets the amount of completed races
     * @return returns completed races
     */
    public int getRacesCompleted() {
        return racesCompleted;
    }

    /**
     * Checks if the season is over
     * @return returns True or False
     */
    public boolean isSeasonOver() {
        return racesCompleted >= seasonLength;
    }

    /**
     * Records the player's position in a race
     * @param place players place
     */
    public void recordRacePlacing(int place) {
        racePlacings.add(place);
    }

    /**
     * Gets the average placing from a player throughout the season
     * @return returns average placing from a player
     */
    public double getAveragePlacing() {
        if (racePlacings.isEmpty()) return 0;
        return racePlacings.stream().mapToInt(i -> i).average().orElse(0.0);
    }
}