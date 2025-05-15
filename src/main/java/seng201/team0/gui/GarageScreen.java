package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import seng201.team0.models.Car;
import seng201.team0.models.CarParts;
import seng201.team0.services.GameEnvironment;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import java.io.IOException;
import java.util.List;
// Controller for the Garage screen.
// Player can view their owned cars, change their racing car, install tuning parts and move to the race screen
public class GarageScreen {
    @FXML private Label speedStat; //Label for the selected car's speed
    @FXML private Label handlingStat; //Label for the selected car's  handling
    @FXML private Label reliabilityStat; //Label for the selected car's reliability
    @FXML private Label fuelEconomyStat; //Label for the selected car's fuel economy
    @FXML private Label priceStat; //Label for the selected car's price
    @FXML private Label selectedCar; //Label to show the selected car
    @FXML private Label selectedRacingCar; //Label to show the selected car for racing
    @FXML private Label staisticLabel; //Label to show the statistic that a tuning part upgrades

    private GameEnvironment game;
    private Car selected;
    private CarParts selectedPart;
    private Stage stage;

    //Loads the garage screen
    public void init(GameEnvironment game, Stage stage) {
        this.game = game;
        this.stage = stage;
        updateSelectedCar(null);
        updateSelectedRacingCar();
    }

    //Allows the player to select a new car
    private void selectCar(int index) {
        List<Car> owned = game.getOwnedCars();
        if (index < owned.size()) {
            updateSelectedCar(owned.get(index));
        }
    }
    //What occurs when ownedCar1 button is pressed
    @FXML
    public void onOwnedCar1Pressed() {
        selectCar(0);
    }
    //What occurs when ownedCar2 button is pressed
    @FXML
    public void onOwnedCar2Pressed() {
        selectCar(1);
    }
    //What occurs when ownedCar3 button is pressed
    @FXML
    public void onOwnedCar3Pressed() {
        selectCar(2);
    }
    //What occurs when ownedCar4 button is pressed
    @FXML
    public void onOwnedCar4Pressed() {
        selectCar(3);
    }
    //What occurs when ownedCar5 button is pressed
    @FXML
    public void onOwnedCar5Pressed() {
        selectCar(4);
    }
    //Shows the player's selected car
    private void updateSelectedCar(Car car) {
        this.selected = car;
        selectedCar.setText(car == null ? "Selected Car" : car.getCarName());
        updateStats(car);
    }
    //Updates labels to show the selected car's stats or shows nothing if no car is selected
    private void updateStats(Car car) {
        if (car == null) {
            speedStat.setText("Speed:");
            handlingStat.setText("Handling:");
            reliabilityStat.setText("Reliability:");
            fuelEconomyStat.setText("Fuel Economy:");
            priceStat.setText("Price:");
        } else {
            speedStat.setText("Speed: " + car.getSpeed());
            handlingStat.setText("Handling: " + car.getHandling());
            reliabilityStat.setText("Reliability: " + car.getReliability());
            fuelEconomyStat.setText("Fuel Economy: " + car.getFuelEconomy());
            priceStat.setText("Price: $" + car.getCost());
        }
    }

    //Allows the player to select a tuning part
    private void selectPart(int index) {
        List<CarParts> parts = game.getUnequippedParts();
        if (index < parts.size()) {
            selectedPart = parts.get(index);
            staisticLabel.setText("Stat " + selectedPart.getStatBoostName());
        }
    }
    //What occurs when InstallableTuningPart1 button is pressed
    @FXML
    public void onInstallableTuningPart1Pressed() {
        selectPart(0);
    }
    //What occurs when InstallableTuningPart2 button is pressed
    @FXML
    public void onInstallableTuningPart2Pressed() {
        selectPart(1);
    }
    //What occurs when InstallableTuningPart3 button is pressed
    @FXML
    public void onInstallableTuningPart3Pressed() {
        selectPart(2);
    }
    //Allows the player to install the selected tuning part when the InstallPart button is pressed
    @FXML
    public void onInstallPartPressed() {
        if (selected != null && selectedPart != null && game.getUnequippedParts().contains(selectedPart)) {
            selected.applyUpgrade(selectedPart);
            selected.increaseValue(selectedPart.getCost() / 2);
            game.getUnequippedParts().remove(selectedPart);
            selectedPart = null;
            staisticLabel.setText("Statistic:");
            updateStats(selected);
        }
    }

    //Shows the player's selected car for racing
    private void updateSelectedRacingCar() {
        Car car = game.getRacingCar();
        selectedRacingCar.setText(car == null ? "None" : car.getCarName());
    }
    //Allows the player to select a new car to race with when the RaceWithSelectedCar button is pressed
    @FXML
    public void onRaceWithSelectedCarPressed() {
        if (selected != null) {
            game.setSelectedRacingCar(selected);
            updateSelectedRacingCar();
        }
    }

    //Allows the player to continue to race selection when the continue button is pressed
    @FXML
    public void onGarageContinuePressed() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/race_selection.fxml"));
        Parent root = loader.load();
        RaceSelectionScreen controller = loader.getController();
        controller.init(game, stage);
        stage.setScene(new Scene(root));
    }
}