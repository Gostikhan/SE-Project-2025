package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import seng201.team0.services.GameEnvironment;
//Controller for the Summary Screen
//Allows the player to see their name, season length, average placing and total prize money won
//Allows the player to exit the game
public class SummaryScreen {

    @FXML private Label nameLabel; //Label that shows the player's name
    @FXML private Label seasonLengthLabel; //label that shows the player's selected season length
    @FXML private Label racesCompletedLabel; //Label that show the player their amount of completed races
    @FXML private Label averagePlacingLabel; //Label that shows the player's average placing throughout the season
    @FXML private Label totalPrizeMoneyWonLabel; //Label the shows how much prize money the player won

    private GameEnvironment game;
    private Stage stage;

    //Loads the summary screen
    public void init(GameEnvironment game, Stage stage) {
        this.game = game;
        this.stage = stage;
        updateSummary();
    }

    //Updates the labels on the summary screen
    private void updateSummary() {
        nameLabel.setText(game.getPlayerName());
        seasonLengthLabel.setText(String.valueOf(game.getSeasonLength()));
        racesCompletedLabel.setText(String.valueOf(game.getRacesCompleted()));
        averagePlacingLabel.setText(String.format("%.2f", game.getAveragePlacing()));
        totalPrizeMoneyWonLabel.setText("$" + game.getBalance());
    }

    //Button that allows the player to exit the game
    @FXML
    public void onExitGamePressed() {
        stage.close();
    }
}