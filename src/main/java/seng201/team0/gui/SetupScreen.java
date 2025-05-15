package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import seng201.team0.models.Car;
import seng201.team0.services.GameEnvironment;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import java.io.IOException;
//Controller for the Setup Screen
//Allows the player to set their name, season length, buy cars and view available cars' stats
public class SetupScreen {
    @FXML private TextField username; //TextField for the player to enter their username
    @FXML private Slider raceAmount; //Slider for the player to select season length
    @FXML private Label statSpeed; //Label for a cars speed
    @FXML private Label statHandling; //Label for a car's handling
    @FXML private Label statReliability; //label for a car's reliability
    @FXML private Label statFuelEconomy; //Label for a car's fuel economy
    @FXML private Label statPrice; //Label for a car's price
    @FXML private Label balanceLabel; //Label for the player's balance
    @FXML private Label purchasedCar1; //Label for a purchased car
    @FXML private Label purchasedCar2; //Label for a purchased car
    @FXML private Label purchasedCar3; //Label for a purchased car

    private GameEnvironment game;
    private Car selectedCar;
    private Stage stage;

    //Loads the setup screen
    public void init(GameEnvironment game, Stage stage) {
        this.game = game;
        this.stage = stage;
        updateBalance();
    }
    private void updateBalance() {
        balanceLabel.setText("$" + game.getBalance());
    }

    //What happens when the easy difficulty button is pressed
    @FXML
    public void onEasyPressed() {
        game.setDifficulty("Easy");
        updateBalance();
    }
    //What happens when the hard difficulty button is pressed
    @FXML
    public void onHardPressed() {
        game.setDifficulty("Hard");
        updateBalance();
    }

    //Allows the player to see available cars
    private void showCarStats(Car car) {
        selectedCar = car;
        statSpeed.setText("Speed: " + car.getSpeed());
        statHandling.setText("Handling: " + car.getHandling());
        statReliability.setText("Reliability: " + car.getReliability());
        statFuelEconomy.setText("Fuel Economy: " + car.getFuelEconomy());
        statPrice.setText("Price: $" + car.getCost());
    }
    //Marks cars as purchased after the player has purchased them
    private void markCarAsPurchased(Car car) {
        if (purchasedCar1.getText().equals("N/A")) {
            purchasedCar1.setText(car.getCarName());
        } else if (purchasedCar2.getText().equals("N/A")) {
            purchasedCar2.setText(car.getCarName());
        } else if (purchasedCar3.getText().equals("N/A")) {
            purchasedCar3.setText(car.getCarName());
        }
    }
    //Button that shows car 1's stats when it is pressed
    @FXML
    public void onCarOnePressed() {
        showCarStats(new Car("Car 1", 100, 80, 90, 400, 3500));
    }
    //Button that shows car 2's stats when it is pressed
    @FXML
    public void onCarTwoPressed() {
        showCarStats(new Car("Car 2", 115, 70, 90, 350, 4000));
    }
    //Button that shows car 3's stats when it is pressed
    @FXML
    public void onCarThreePressed() {
        showCarStats(new Car("Car 3", 85, 90, 75, 300, 3000));
    }
    //Button that shows car 4's stats when it is pressed
    @FXML
    public void onCarFourPressed() {
        showCarStats(new Car("Car 4", 100, 85, 75, 370, 3800));
    }
    //Button that shows car 5's stats when it is pressed
    @FXML
    public void onCarFivePressed() {
        showCarStats(new Car("Car 5", 85, 75, 75, 420, 3200));
    }
    //Button that shows car 6's stats when it is pressed
    @FXML
    public void onCarSixPressed() {
        showCarStats(new Car("Car 6", 115, 82, 50, 390, 3600));
    }

    //Button that allows a player to purchase a car
    @FXML
    public void onPurchasePressed() {
        if (selectedCar == null) {
            showAlert("No car selected.");
            return;
        }
        if (game.getOwnedCars().size() >= 3) {
            showAlert("You can only purchase up to 3 cars.");
            return;
        }
        if (!game.canPurchase(selectedCar)) {
            showAlert("Not enough balance to purchase this car.");
            return;
        }
        game.purchaseCar(selectedCar);
        markCarAsPurchased(selectedCar);
        updateBalance();

    }

    //Button that allows a player to continue to the race screen
    @FXML
    public void onContinuePressed() throws IOException {
        String name = username.getText().trim();
        if (name.length() < 3 || name.length() > 15 || !name.matches("[a-zA-Z0-9 ]+")) {
            showAlert("Invalid name. Must be 3–15 letters, no special characters.");
            // ChatGPT was used to help write the above 2 lines of code
            return;
        }
        if (game.getOwnedCars().isEmpty()) {
            showAlert("You must purchase at least one car.");
            return;
        }
        game.setPlayerName(username.getText().trim());
        game.setSeasonLength((int) raceAmount.getValue());
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/shop.fxml"));
        Parent root = loader.load();
        ShopScreen controller = loader.getController();
        controller.init(game, stage);
        stage.setScene(new Scene(root));
    }
    //Button that allows a player to go back to the start screen
    @FXML
    public void onBackPressed() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/start_screen.fxml"));
        Parent root = loader.load();
        StartScreen controller = loader.getController();
        controller.init(stage, new GameEnvironment());
        stage.setScene(new Scene(root));
    }

    //Shows an alert if the player hasn't correctly selected a name, selected a car or purchased any cars
    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Input Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}