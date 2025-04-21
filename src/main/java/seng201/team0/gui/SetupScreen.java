package seng201.team0.gui;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import seng201.team0.models.Car;
import seng201.team0.services.GameEnvironment;

public class SetupScreen {
    @FXML
    private TextField username;
    @FXML
    private Slider raceAmount;
    @FXML
    private Label statSpeed;
    @FXML
    private Label statHandling;
    @FXML
    private Label statReliability;
    @FXML
    private Label statFuelEconomy;
    @FXML
    private Label statPrice;
    @FXML
    private Label balanceLabel;
    private GameEnvironment game;
    private Car selectedCar;

    public void init(GameEnvironment game) {
        this.game = game;
        updateBalance();
    }
    private void updateBalance() {
        balanceLabel.setText("$" + game.getBalance());
    }
    private void showCarStats(Car car) {
        selectedCar = car;
        statSpeed.setText("Speed: " + car.getDescription().split(",")[0].split(":")[1].trim());
        statHandling.setText("Handling: " + car.getDescription().split(",")[1].split(":")[1].trim());
        statReliability.setText("Reliability: " + car.getDescription().split(",")[2].split(":")[1].trim());
        statFuelEconomy.setText("Fuel Economy: " + car.getDescription().split(",")[3].split(":")[1].trim());
        statPrice.setText("Price: $" + car.getCost());
    }

    @FXML
    public void onEasyPressed() {
        game.setDifficulty("Easy");
        updateBalance();
    }
    @FXML
    public void onHardPressed() {
        game.setDifficulty("Hard");
        updateBalance();
    }

    @FXML
    public void onCarOnePressed() {
        showCarStats(new Car("Falcon", 200, 80, 95, 400, 3500));
    }
    @FXML
    public void onCarTwoPressed() {
        showCarStats(new Car("Cheetah", 220, 70, 90, 350, 4000));
    }
    @FXML
    public void onCarThreePressed() {
        showCarStats(new Car("Rhino", 180, 90, 98, 300, 3000));
    }
    @FXML
    public void onCarFourPressed() {
        showCarStats(new Car("Panther", 210, 85, 92, 370, 3800));
    }
    @FXML
    public void onCarFivePressed() {
        showCarStats(new Car("Wasp", 190, 75, 88, 420, 3200));
    }
    @FXML
    public void onCarSixPressed() {
        showCarStats(new Car("Blizzard", 195, 82, 96, 390, 3600));
    }

    @FXML
    public void onPurchasePressed() {
        if (selectedCar == null) {
            System.out.println("No car selected.");
            return;
        }
        if (game.canPurchase(selectedCar)) {
            game.purchaseCar(selectedCar);
            System.out.println("Purchased: " + selectedCar.getCarName());
            updateBalance();
        } else {
            System.out.println("Cannot purchase this car.");
        }
    }

    @FXML
    public void onContinuePressed() {
        String name = username.getText().trim();
        if (name.length() < 3 || name.length() > 15 || !name.matches("[a-zA-Z0-9 ]+")) {
            System.out.println("Invalid name. Must be 3–15 letters, no special characters.");
            // ChatGPT was used to help write the above 2 lines of code
            return;
        }
        if (game.getOwnedCars().isEmpty()) {
            System.out.println("You must purchase at least one car.");
            return;
        }
        game.setPlayerName(name);
        game.setSeasonLength((int) raceAmount.getValue());
        System.out.println("Setup complete:");
        System.out.println("Name: " + game.getPlayerName());
        System.out.println("Races: " + game.getSeasonLength());
        System.out.println("Difficulty: " + game.getDifficulty());
        System.out.println("Cars Owned: " + game.getOwnedCars().size());

        // TODO: Load the main game screen here
    }
}