package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import seng201.team0.services.GameEnvironment;

/**
 * Controller for the Start screen
 * Allows the player to start or exit the game
 */
public class StartScreen {

    private Stage stage;
    private GameEnvironment game;

    /**
     * Loads the start screen
     * @param game Game manager, manages the whole game
     * @param stage Window application, used for scene transitions
     */
    public void init(Stage stage, GameEnvironment game) {
        this.stage = stage;
        this.game = game;
    }

    /**
     * Button that allows the player to start the game
     */
    @FXML
    public void onStartPressed() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/setup_screen.fxml"));
            Parent root = loader.load();
            SetupScreen controller = loader.getController();
            controller.init(game, stage);
            stage.setScene(new Scene(root, 600, 400));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * //Button that allows the player to exit the game
     */
    @FXML
    public void onExitPressed() {
        System.exit(0);
    }
}
