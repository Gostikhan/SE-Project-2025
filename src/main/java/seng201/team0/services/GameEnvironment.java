package seng201.team0.services;

import seng201.team0.models.Car;
import seng201.team0.models.CarParts;
import seng201.team0.models.Race;
import seng201.team0.models.RaceRoute;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
//The environment that the game runs in,
// Keeps track of everything and generates, races, parts and cars when the game is started
public class GameEnvironment {
    private String playerName; //Variable for the player's name
    private int seasonLength; //Variable for the season length
    private String difficulty; //Variable for the difficulty
    private int balance; //Variable for the player's balance

    private final List<Car> ownedCars = new ArrayList<>(); //List of owned cars
    private final List<CarParts> unequippedParts = new ArrayList<>(); //List of tuning parts
    private final List<Car> availableCars = new ArrayList<>(); //List of available cars

    private final List<Race> allRaces = new ArrayList<>(); //List of races
    private final List<Race> currentRaces = new ArrayList<>(); //List of current races that the player can choose from
    private final List<Integer> racePlacings = new ArrayList<>(); //List of the all the player's placings

    private final Random random = new Random(); //Random number generator
    private int racesCompleted = 0; //Amount of races completed
    private Car selectedRacingCar; //The selected car for racing

    //Runs when the game starts, sets balance, generates cars, parts and races
    public GameEnvironment() {
        balance = 0;
        generateCars();
        generateParts();
        generateRaces();
        refreshCurrentRaces();
    }

    //Generates cars
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

    //Generates tuning parts
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

    //Generates races
    public void generateRaces() {
        allRaces.clear();
        for (int i = 1; i <= 15; i++) {
            Race race = new Race(4 + random.nextInt(4), 3 + random.nextInt(4), 10000 + random.nextInt(15000));
            race.addRoute(new RaceRoute("Route A for Race " + i, 400 + random.nextInt(200), 1 + random.nextInt(3), 1.0 + random.nextDouble()));
            race.addRoute(new RaceRoute("Route B for Race " + i, 400 + random.nextInt(200), 1 + random.nextInt(3), 1.0 + random.nextDouble()));
            allRaces.add(race);
        }
    }

    //Shows the player new races to choose from
    public void refreshCurrentRaces() {
        currentRaces.clear();
        List<Race> shuffled = new ArrayList<>(allRaces);
        Collections.shuffle(shuffled);
        for (int i = 0; i < 3 && i < shuffled.size(); i++) {
            currentRaces.add(shuffled.get(i));
        }
    }

    //Gets the currently selected race
    public List<Race> getCurrentRaces() {
        return currentRaces;
    }
    //Sets the player's name
    public void setPlayerName(String name) {
        this.playerName = name;
    }
    //Gets the player's name
    public String getPlayerName() {
        return playerName;
    }
    //Sets the season length
    public void setSeasonLength(int length) {
        this.seasonLength = length;
    }
    //Gets the season length
    public int getSeasonLength() {
        return seasonLength;
    }
    //Sets the difficulty
    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
        if (difficulty.equals("Easy")) {
            this.balance = 250000;
        } else {
            this.balance = 200000;
        }
    }
    //Gets the difficulty
    public String getDifficulty() {
        return difficulty;
    }
    //Gets the player's balance
    public int getBalance() {
        return balance;
    }
    //Adds to the players balance
    public void addBalance(int amount) {
        this.balance += amount;
    }
    //Gets a list of the player's owned cars
    public List<Car> getOwnedCars() {
        return ownedCars;
    }
    //Whether a player can purchase the selected car
    public boolean canPurchase(Car car) {
        return ownedCars.size() < 5 && balance >= car.getCost();
    }
    //Lets the player purchase the selected car
    public void purchaseCar(Car car) {
        if (canPurchase(car)) {
            balance -= car.getCost();
            ownedCars.add(car);
        }
    }
    //Whether a player can sell the selected car
    public boolean canSellCar() {
        return ownedCars.size() > 1;
    }
    //Lets the player sell the selected car
    public void sellCar(Car car) {
        if (ownedCars.contains(car) && canSellCar()) {
            balance += car.getSellPrice();
            ownedCars.remove(car);
            if (selectedRacingCar == car) {
                selectedRacingCar = ownedCars.isEmpty() ? null : ownedCars.get(0);
            }
        }
    }
    //Gets the available cars in the shop
    public List<Car> getAvailableCars() {
        return availableCars;
    }
    //Gets the available tuning parts in the shop
    public List<CarParts> getUnequippedParts() {
        return unequippedParts;
    }
    //Whether a player can purchase a tuning part
    public boolean canPurchasePart(CarParts part) {
        return unequippedParts.size() < 3 && balance >= part.getCost();
    }
    //Lets the player purchase a tuning part
    public void purchasePart(CarParts part) {
        if (canPurchasePart(part)) {
            balance -= part.getCost();
            unequippedParts.add(part);
        }
    }
    //Gets a list of available tuning parts in the shop
    public List<CarParts> getAvailableParts() {
        List<CarParts> parts = new ArrayList<>(unequippedParts);
        Collections.shuffle(parts);
        return parts.subList(0, Math.min(3, parts.size()));
    }
    //Whether a player can sell a tuning part
    public boolean canSellPart(CarParts part) {
        return unequippedParts.contains(part);
    }
    //Allows the player to sell a tuning part
    public void sellPart(CarParts part) {
        if (canSellPart(part)) {
            balance += part.getSellPrice();
            unequippedParts.remove(part);
        }
    }
    //Whether a player can install a tuning part
    public boolean canInstallPart(CarParts part, Car car) {
        return unequippedParts.contains(part) && ownedCars.contains(car);
    }
    //Allows the player to install a tuning part
    public void installPartToCar(CarParts part, Car car) {
        if (canInstallPart(part, car)) {
            car.applyUpgrade(part);
            car.increaseValue(part.getCost() / 2);
            unequippedParts.remove(part);
        }
    }
    //Sets the selected car for racing
    public void setSelectedRacingCar(Car car) {
        if (ownedCars.contains(car)) {
            this.selectedRacingCar = car;
        }
    }
    //Gets the selected car for racing
    public Car getSelectedRacingCar() {
        return selectedRacingCar;
    }
    //Get the current car that is selected for racing
    public Car getRacingCar() {
        return getSelectedRacingCar();
    }
    //Whether a player owns a car
    public boolean ownsCar(Car car) {
        return ownedCars.contains(car);
    }
    //Whether a player owns a tuning part
    public boolean ownsPart(CarParts part) {
        return unequippedParts.contains(part);
    }
    //Increases the amount of completed races by 1
    public void incrementRacesCompleted() {
        racesCompleted++;
    }
    //Gets the amount of completed races
    public int getRacesCompleted() {
        return racesCompleted;
    }
    //Checks if the season is over
    public boolean isSeasonOver() {
        return racesCompleted >= seasonLength;
    }
    //Records the player's position in a race
    public void recordRacePlacing(int place) {
        racePlacings.add(place);
    }
    //Gets the average placing from a player throughout the season
    public double getAveragePlacing() {
        if (racePlacings.isEmpty()) return 0;
        return racePlacings.stream().mapToInt(i -> i).average().orElse(0.0);
    }
}