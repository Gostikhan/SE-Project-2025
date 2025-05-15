package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import seng201.team0.models.*;
import seng201.team0.services.GameEnvironment;

import java.io.IOException;
import java.util.List;
//Controller for the Race Selection screen
//Allows the player to see available races, select a race, select route and view route descriptions
public class RaceSelectionScreen {

    @FXML private Label durationLabel; //Label for the race duration
    @FXML private Label entriesLabel; //Label for the amount of entries
    @FXML private Label prizeLabel; //Label for the prize money for the race
    @FXML private Label route1Label; //Label to show Route 1 description
    @FXML private Label route2Label; //Label to show Route 2 description

    private GameEnvironment game;
    private Stage stage;
    private List<Race> races;
    private Race selectedRace;
    private RaceRoute selectedRoute;

    //Loads the race selection screen
    public void init(GameEnvironment game, Stage stage) {
        this.game = game;
        this.stage = stage;
        game.refreshCurrentRaces();
        this.races = game.getCurrentRaces();
        showRace(0);
    }

    //Shows available races to the player
    private void showRace(int index) {
        if (index >= races.size()) return;
        selectedRace = races.get(index);
        durationLabel.setText(selectedRace.getHours() + " hours");
        entriesLabel.setText(selectedRace.getAiEntries() + " opponents");
        prizeLabel.setText("$" + selectedRace.getPrizeMoney());
        List<RaceRoute> routes = selectedRace.getRoutes();
        route1Label.setText(formatRoute(routes.get(0)));
        route1Label.setVisible(true);
        route2Label.setText(formatRoute(routes.get(1)));
        route2Label.setVisible(true);
        selectedRoute = null;
    }

    //Formats the route description
    private String formatRoute(RaceRoute route) {
        return route.getRaceDescription() + ": " +
                route.getRaceDistance() + "km, " +
                route.getFuelStops() + " stops, Difficulty: " +
                String.format("%.1f", route.getRaceDifficulty());
    }

    //Button that allows the player to select race 1
    @FXML
    public void onRace1Pressed() {
        showRace(0);
    }
    //Button that allows the player to select race 2
    @FXML
    public void onRace2Pressed() {
        showRace(1);
    }
    //Button that allows the player to select race 3
    @FXML
    public void onRace3Pressed() {
        showRace(2);
    }

    //Button that allows the player to select route 1
    @FXML
    public void onRoute1Pressed() {
        selectedRoute = selectedRace.getRoutes().get(0);
    }
    //Button that allows the player to select route 2
    @FXML
    public void onRoute2Pressed() {
        selectedRoute = selectedRace.getRoutes().get(1);
    }

    //Button that allows the player to start the race
    @FXML
    public void onStartRacePressed() throws IOException {
        if (selectedRace == null || selectedRoute == null) {
            showAlert("Please select a race and route first.");
            return;
        }
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/race_screen.fxml"));
        Parent root = loader.load();
        RaceScreen controller = loader.getController();
        controller.init(game, stage, selectedRace, selectedRoute);
        stage.setScene(new Scene(root));
    }

    //Shows an alert if the player hasn't selected a race and a route
    private void showAlert(String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setContentText(msg);
        alert.showAndWait();
    }
}