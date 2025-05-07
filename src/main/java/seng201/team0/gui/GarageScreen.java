package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import seng201.team0.models.Car;
import seng201.team0.models.CarParts;
import seng201.team0.services.GameEnvironment;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import java.io.IOException;
import java.util.List;

public class GarageScreen {
    @FXML private Label speedStat;
    @FXML private Label handlingStat;
    @FXML private Label reliabilityStat;
    @FXML private Label fuelEconomyStat;
    @FXML private Label priceStat;
    @FXML private Label selectedCar;
    @FXML private Label selectedRacingCar;
    @FXML private Label staisticLabel;

    private GameEnvironment game;
    private Car selected;
    private CarParts selectedPart;
    private Stage stage;

    //Loading The Garage Screen
    public void init(GameEnvironment game, Stage stage) {
        this.game = game;
        this.stage = stage;
        updateSelectedCar(null);
        updateSelectedRacingCar();
    }
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

    //Selecting Parts And Cars
    private void updateSelectedCar(Car car) {
        this.selected = car;
        selectedCar.setText(car == null ? "Selected Car" : car.getCarName());
        updateStats(car);
    }
    private void updateSelectedRacingCar() {
        Car car = game.getRacingCar();
        selectedRacingCar.setText(car == null ? "None" : car.getCarName());
    }
    private void selectCar(int index) {
        List<Car> owned = game.getOwnedCars();
        if (index < owned.size()) {
            updateSelectedCar(owned.get(index));
        }
    }
    private void selectPart(int index) {
        List<CarParts> parts = game.getUnequippedParts();
        if (index < parts.size()) {
            selectedPart = parts.get(index);
            staisticLabel.setText("Stat " + selectedPart.getStatBoostName());
        }
    }
    @FXML
    public void onOwnedCar1Pressed() {
        selectCar(0);
    }
    @FXML
    public void onOwnedCar2Pressed() {
        selectCar(1);
    }
    @FXML
    public void onOwnedCar3Pressed() {
        selectCar(2);
    }
    @FXML
    public void onOwnedCar4Pressed() {
        selectCar(3);
    }
    @FXML
    public void onOwnedCar5Pressed() {
        selectCar(4);
    }
    @FXML
    public void onInstallableTuningPart1Pressed() {
        selectPart(0);
    }
    @FXML
    public void onInstallableTuningPart2Pressed() {
        selectPart(1);
    }
    @FXML
    public void onInstallableTuningPart3Pressed() {
        selectPart(2);
    }

    //Installing Tuning Parts
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

    //Selecting New Racing Car
    @FXML
    public void onRaceWithSelectedCarPressed() {
        if (selected != null) {
            game.setSelectedRacingCar(selected);
            updateSelectedRacingCar();
        }
    }

    //Continue Button
    @FXML
    public void onGarageContinuePressed() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/race_screen.fxml"));
        Parent root = loader.load();
        RaceScreen controller = loader.getController();
        controller.init(game, stage);
        stage.getScene().setRoot(root);
    }
}