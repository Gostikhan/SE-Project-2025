package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import seng201.team0.models.Car;
import seng201.team0.models.Race;
import seng201.team0.models.RaceRoute;
import seng201.team0.services.GameEnvironment;

import java.io.IOException;
import java.util.Random;
//Controller for the Race Screen
//Player can start race, see the current race status, see the race result and their prizing and make race decisions
public class RaceScreen {

    @FXML private Label statusLabel; //Label for the race status
    @FXML private Label eventLabel; //Label for any events that have occurred
    @FXML private Label positionLabel; //Label for the player's position at the end of the race
    @FXML private Label prizeLabel; //Label for the money that the player has won
    @FXML private Label timeLabel; //Label for how long it took the player to complete the race
    @FXML private Button refuel; //Button to allow the player to refuel at a fuel stop
    @FXML private Button dontRefuel; //Button to allow the player to skip refueling at a fuel stop
    @FXML private Button repair; //Button to allow the player to repair their car in the event that it breaks down
    @FXML private Button retire; //Button to allow the player to retire their car in the event that it breaks down

    private GameEnvironment game;
    private Stage stage;
    private Race race;
    private RaceRoute route;
    private Car car;

    private boolean playerRefueled = false; //Variable that tells the game whether a player has refueled
    private final Random random = new Random(); //Random number generator for events

    //Loads the Race Screen
    public void init(GameEnvironment game, Stage stage, Race race, RaceRoute route) {
        this.game = game;
        this.stage = stage;
        this.race = race;
        this.route = route;
        this.car = game.getRacingCar();
        statusLabel.setText("Waiting to start...");
        eventLabel.setText("No event yet.");
        prizeLabel.setText("-");
        timeLabel.setText("-");
        positionLabel.setText("-");
        refuel.setDisable(true);
        dontRefuel.setDisable(true);
        repair.setVisible(false);
        retire.setVisible(false);
    }

    //Button that allows the player to start the race
    @FXML
    public void onStartRacePressed() {
        statusLabel.setText("Reached fuel stop.");
        refuel.setDisable(false);
        dontRefuel.setDisable(false);
    }
    //Button that allows the player to refuel
    @FXML
    public void onRefuelPressed() {
        playerRefueled = true;
        refuel.setDisable(true);
        dontRefuel.setDisable(true);
        runRace();
    }
    //Button that allows a player to skip refueling
    @FXML
    public void onDontRefuelPressed() {
        playerRefueled = false;
        refuel.setDisable(true);
        dontRefuel.setDisable(true);
        runRace();
    }

    //Race logic of the game, handles events, allows the player to play the game
    private void runRace() {
        if (!hasEnoughFuel()) {
            eventLabel.setText("You ran out of fuel before the next stop!");
            statusLabel.setText("DNF due to fuel exhaustion.");
            positionLabel.setText("DNF");
            prizeLabel.setText("$0");
            game.recordRacePlacing(race.getAiEntries() + 1);
            game.incrementRacesCompleted();
            return;
        }
        double baseTime = route.getRaceDistance() / (double) car.getSpeed();
        double handlingPenalty = (100 - car.getHandling()) * 0.01;
        double finalTime = baseTime * route.getRaceDifficulty() + handlingPenalty;
        boolean severeWeather = random.nextDouble() < 0.2;
        if (severeWeather) {
            eventLabel.setText("Severe weather! All racers must retire.");
            statusLabel.setText("Race cancelled.");
            positionLabel.setText("DNF");
            prizeLabel.setText("$0");
            timeLabel.setText("-");
            game.recordRacePlacing(race.getAiEntries() + 1);
            game.incrementRacesCompleted();
            return;
        }
        if (checkBreakdown()) {
            eventLabel.setText("Your car broke down");
            statusLabel.setText("Repair or retire?");
            repair.setVisible(true);
            retire.setVisible(true);
            return;
        }
        boolean traveler = random.nextDouble() < 0.3;
        if (traveler) {
            finalTime += 0.25;
            int reward = 500 + random.nextInt(501);
            game.addBalance(reward);
            eventLabel.setText("You picked up a hitch hiker who gave you+$" + reward);
        } else {
            eventLabel.setText("No event");
        }
        finishRace(finalTime);
    }

    //Checks if the player has enough fuel to race
    private boolean hasEnoughFuel() {
        double maxPerTank = car.getFuelEconomy();
        int fuelStops = route.getFuelStops();
        double segment = route.getRaceDistance() / (fuelStops + 1.0);
        return maxPerTank >= segment;
    }

    //Checks if the player has a breakdown
    private boolean checkBreakdown() {
        double reliability = car.getReliability();
        double chance = reliability >= 90 ? 0.10 : reliability >= 75 ? 0.25 : 0.50;
        if (!playerRefueled) chance += 0.10;
        return random.nextDouble() < chance;
    }

    //What happens when the repair button is pressed
    //Hides this button if the car hasn't broken down
    @FXML
    public void onRepairPressed() {
        repair.setVisible(false);
        retire.setVisible(false);
        eventLabel.setText("Car repaired. Time penalty applied.");
        double repairPenalty = 2.0;
        finishRace(repairPenalty);
    }
    //What happens when the retire button is pressed
    //Hides this button if the car hasn't broken down
    @FXML
    public void onRetirePressed() {
        repair.setVisible(false);
        retire.setVisible(false);
        eventLabel.setText("You chose to retire.");
        statusLabel.setText("Race ended.");
        positionLabel.setText("DNF");
        prizeLabel.setText("$0");
        game.recordRacePlacing(race.getAiEntries() + 1);
        game.incrementRacesCompleted();
    }

    //Handles the end of the race, updates placing, prize money and race count
    private void finishRace(double additionalTime) {
        double baseTime = route.getRaceDistance() / (double) car.getSpeed();
        double handlingPenalty = (100 - car.getHandling()) * 0.01;
        double finalTime = baseTime * route.getRaceDifficulty() + handlingPenalty + additionalTime;
        int place = 1 + random.nextInt(race.getAiEntries() + 1);
        int prize = (place == 1) ? race.getPrizeMoney() : race.getPrizeMoney() / (place + 1);
        statusLabel.setText("Race complete.");
        positionLabel.setText("P" + place);
        prizeLabel.setText("$" + prize);
        timeLabel.setText(String.format("%.2f hours", finalTime));
        game.addBalance(prize);
        game.recordRacePlacing(place);
        game.incrementRacesCompleted();
    }

    //What happens when the end race button is pressed
    @FXML
    public void onEndRacePressed() {
        try {
            FXMLLoader loader;
            if (game.isSeasonOver()) {
                loader = new FXMLLoader(getClass().getResource("/fxml/summary_screen.fxml"));
                Parent root = loader.load();
                SummaryScreen controller = loader.getController();
                controller.init(game, stage);
                stage.setScene(new Scene(root));
            } else {
                loader = new FXMLLoader(getClass().getResource("/fxml/shop.fxml"));
                Parent root = loader.load();
                ShopScreen controller = loader.getController();
                controller.init(game, stage);
                stage.setScene(new Scene(root));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}