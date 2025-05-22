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
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Controller for the Shop screen
 * Allows the player to buy and/or sell tuning parts and cars
 */
public class ShopScreen {
    @FXML private Label balanceLabel; //Label for the player's balance
    @FXML private Label speedStat; //Label for the speed of a selected car
    @FXML private Label handlingStat; //Label for the handling of a selected car
    @FXML private Label reliabilityStat; //Label for the reliability of a selected car
    @FXML private Label fuelEconomyStat; //Label for the fuel economy of a selected car
    @FXML private Label priceStat; //Label for the price of a selected car
    @FXML private Label staisticLabel; //Label to show the statistic that a tuning part upgrades

    private GameEnvironment game;
    private Car selectedCar;
    private CarParts selectedPart;
    private Stage stage;
    private List<Car> shopCars;
    private List<CarParts> shopParts;

    /**
     * Loads the shop screen
     * @param game
     * @param stage
     */
    public void init(GameEnvironment game, Stage stage) {
        this.game = game;
        this.stage = stage;
        updateBalance();
        shopCars = new ArrayList<>(game.getAvailableCars());
        shopParts = new ArrayList<>(game.getAvailableParts());
        Collections.shuffle(shopCars);
        Collections.shuffle(shopParts);
        shopCars = shopCars.subList(0, Math.min(3, shopCars.size()));
        shopParts = shopParts.subList(0, Math.min(3, shopParts.size()));
    }

    /**
     * Updates the balance that the player can see on screen
     */
    private void updateBalance() {
        balanceLabel.setText("$" + game.getBalance());
    }

    /**
     * Shows the selected tuning part's upgrade stat
     * @param part Car parts
     */
    private void showPartStat(CarParts part) {
        selectedPart = part;
        staisticLabel.setText(part.getStatBoostName() + ": +1");
    }

    /**
     * Button that shows tuning part 1's stats when it is pressed
     */
    @FXML
    public void onTuningPart1Pressed() {
        showPartStat(shopParts.get(0));
    }

    /**
     * Button that shows tuning part 2's stats when it is pressed
     */
    @FXML
    public void onTuningPart2Pressed() {
        showPartStat(shopParts.get(1));
    }

    /**
     * Button that shows tuning part 3's stats when it is pressed
     */
    @FXML
    public void onTuningPart3Pressed() {
        showPartStat(shopParts.get(2));
    }

    /**
     * Button that shows owned tuning part 1's stats when it is pressed
     */
    @FXML
    public void onOwnedTuningPart1Pressed() {
        showPartStat(game.getOwnedParts().get(0));
    }

    /**
     * Button that shows owned tuning part 12's stats when it is pressed
     */
    @FXML
    public void onOwnedTuningPart2Pressed() {
        showPartStat(game.getOwnedParts().get(1));
    }

    /**
     * Button that shows owned tuning part 3's stats when it is pressed
     */
    @FXML
    public void onOwnedTuningPart3Pressed() {
        showPartStat(game.getOwnedParts().get(2));
    }

    /**
     * Button that allows a player to purchase the selected tuning part
     */
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

    /**
     * Button that allows the player to sell the selected tuning part
     */
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

    /**
     * Shows the selected car's stats to the player
     * @param car
     */
    private void showCarStats(Car car) {
        selectedCar = car;
        speedStat.setText("Speed: " + car.getSpeed() + " km/h");
        handlingStat.setText("Handling: " + car.getHandling());
        reliabilityStat.setText("Reliability: " + car.getReliability() + "%");
        fuelEconomyStat.setText("Fuel Economy: " + car.getFuelEconomy() + " km");
        priceStat.setText("Price: $" + car.getCost());
    }

    /**
     * Shows the selected car's stats to the player
     */
    @FXML
    public void onSaleCar1Pressed() {
        showCarStats(shopCars.get(0));
    }

    /**
     * Button that allows the player to select car 2
     */
    @FXML
    public void onSaleCar2Pressed() {
        showCarStats(shopCars.get(1));
    }

    /**
     * Button that allows the player to select car 3
     */
    @FXML
    public void onSaleCar3Pressed() {
        showCarStats(shopCars.get(2));
    }

    /**
     * Button that allows the player to select owned car 1
     */
    @FXML
    public void onOwnedCar1Pressed() {
        showCarStats(game.getOwnedCars().get(0));
    }

    /**
     * Button that allows the player to select owned car 2
     */
    @FXML
    public void onOwnedCar2Pressed() {
        showCarStats(game.getOwnedCars().get(1));
    }

    /**
     * Button that allows the player to select owned car 3
     */
    @FXML
    public void onOwnedCar3Pressed() {
        showCarStats(game.getOwnedCars().get(2));
    }

    /**
     * Button that allows the player to select owned car 3
     */
    @FXML
    public void onOwnedCar4Pressed() {
        showCarStats(game.getOwnedCars().get(3));
    }

    /**
     * Button that allows the player to select owned car 3
     */
    @FXML
    public void onOwnedCar5Pressed() {
        showCarStats(game.getOwnedCars().get(4));
    }

    /**
     * Button that allows the payer to purchase the selected car
     */
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

    /**
     * Button that allows a player to sell the selected car
     */
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

    /**
     * Button that allows the player to continue to the garage
     * @throws IOException throws an exception if something goes wrong
     */
    @FXML
    public void onShopContinuePressed() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/garage.fxml"));
        Parent root = loader.load();
        GarageScreen controller = loader.getController();
        controller.init(game, stage);
        stage.setScene(new Scene(root));
    }

    /**
     * Shows an alert if the player tries to sell a car or tuning part they can't
     * Show an error if the player doesn't own at least 1 car
     * Shows an error if the player hasn't selected a tuning part or car
     * @param msg alert message
     */
    private void showAlert(String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setContentText(msg);
        alert.showAndWait();
    }

    /**
     * Information of any bought or sold part
     * @param msg
     */
    private void showInfo(String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Info");
        alert.setContentText(msg);
        alert.showAndWait();
    }
}
