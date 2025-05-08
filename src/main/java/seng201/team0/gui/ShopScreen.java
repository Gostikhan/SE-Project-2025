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

public class ShopScreen {
    @FXML private Label balanceLabel;
    @FXML private Label speedStat;
    @FXML private Label handlingStat;
    @FXML private Label reliabilityStat;
    @FXML private Label fuelEconomyStat;
    @FXML private Label priceStat;
    @FXML private Label staisticLabel;

    private GameEnvironment game;
    private Car selectedCar;
    private CarParts selectedPart;
    private Stage stage;

    //Loading The Shop Screen
    public void init(GameEnvironment game, Stage stage) {
        this.game = game;
        this.stage = stage;
        updateBalance();
    }
    private void updateBalance() {
        balanceLabel.setText("$" + game.getBalance());
    }

    //Tuning Parts
    private void showPartStat(CarParts part) {
        selectedPart = part;
        staisticLabel.setText(part.getStatBoostName() + ": +1");
    }
    @FXML
    public void onTuningPart1Pressed() {
        showPartStat(game.getAvailableParts().get(0));
    }
    @FXML
    public void onTuningPart2Pressed() {
        showPartStat(game.getAvailableParts().get(1));
    }
    @FXML
    public void onTuningPart3Pressed() {
        showPartStat(game.getAvailableParts().get(2));
    }
    @FXML
    public void onOwnedTuningPart1Pressed() {
        showPartStat(game.getUnequippedParts().get(0));
    }
    @FXML
    public void onOwnedTuningPart2Pressed() {
        showPartStat(game.getUnequippedParts().get(1));
    }
    @FXML
    public void onOwnedTuningPart3Pressed() {
        showPartStat(game.getUnequippedParts().get(2));
    }
    @FXML
    public void onPurchasePartPressed() {
        if (selectedPart == null) {
            showAlert("No tuning part selected.");
        } else if (!game.canPurchasePart(selectedPart)) {
            showAlert("Can't purchase tuning part.");
        } else {
            game.purchasePart(selectedPart);
            showInfo("Purchased " + selectedPart.getPartName());
            updateBalance();
        }
    }
    @FXML
    public void onSellPartPressed() {
        if (selectedPart == null || !game.canSellPart(selectedPart)) {
            showAlert("Can't sell tuning part.");
        } else {
            game.sellPart(selectedPart);
            showInfo("Sold " + selectedPart.getPartName());
            updateBalance();
        }
    }

    //Cars
    private void showCarStats(Car car) {
        selectedCar = car;
        speedStat.setText("Speed: " + car.getSpeed());
        handlingStat.setText("Handling: " + car.getHandling());
        reliabilityStat.setText("Reliability: " + car.getReliability());
        fuelEconomyStat.setText("Fuel Economy: " + car.getFuelEconomy());
        priceStat.setText("Price: $" + car.getCost());
    }
    @FXML
    public void onSaleCar1Pressed() {
        showCarStats(game.getAvailableCars().get(0));
    }
    @FXML
    public void onSaleCar2Pressed() {
        showCarStats(game.getAvailableCars().get(1));
    }
    @FXML
    public void onSaleCar3Pressed() {
        showCarStats(game.getAvailableCars().get(2));
    }
    @FXML
    public void onOwnedCar1Pressed() {
        showCarStats(game.getOwnedCars().get(0));
    }
    @FXML
    public void onOwnedCar2Pressed() {
        showCarStats(game.getOwnedCars().get(1));
    }
    @FXML
    public void onOwnedCar3Pressed() {
        showCarStats(game.getOwnedCars().get(2));
    }
    @FXML
    public void onOwnedCar4Pressed() {
        showCarStats(game.getOwnedCars().get(3));
    }
    @FXML
    public void onOwnedCar5Pressed() {
        showCarStats(game.getOwnedCars().get(4));
    }
    @FXML
    public void onPurchaseCarPressed() {
        if (selectedCar == null) {
            showAlert("No car selected.");
        } else if (!game.canPurchase(selectedCar)) {
            showAlert("Can't purchase car.");
        } else {
            game.purchaseCar(selectedCar);
            showInfo("Purchased " + selectedCar.getCarName());
            updateBalance();
        }
    }
    @FXML
    public void onSellCarPressed() {
        if (selectedCar == null || !game.getOwnedCars().contains(selectedCar)) {
            showAlert("Can't sell car.");
        } else if (!game.canSellCar()) {
            showAlert("You must own at least 1 car.");
        } else {
            game.sellCar(selectedCar);
            showInfo("Sold " + selectedCar.getCarName());
            updateBalance();
        }
    }

    //Continue Button
    @FXML
    public void onShopContinuePressed() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/garage.fxml"));
        Parent root = loader.load();
        GarageScreen controller = loader.getController();
        controller.init(game, stage);
        stage.setScene(new Scene(root));
    }

    //Alert and Info
    private void showAlert(String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setContentText(msg);
        alert.showAndWait();
    }
    private void showInfo(String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Info");
        alert.setContentText(msg);
        alert.showAndWait();
    }
}