package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import seng201.team0.services.CounterService;
import seng201.team0.services.GameEnvironment;

/**
 * Controller for the main.fxml window
 * @author seng201 teaching team
 */
public class MainController {

    @FXML
    private Label defaultLabel;

    @FXML
    private Button defaultButton;

    private CounterService counterService;
    private GameEnvironment game;
    private Stage stage;

    /**
     * Initialize the window
     *
     * @param stage Top level container for this window
     */
    public void init(Stage stage) {
        this.stage = stage;
        this.counterService = new CounterService();
        this.game = new GameEnvironment(); // Create shared game state
    }

    /**
     * Method to call when our counter button is clicked
     */
    @FXML
    public void onButtonClicked() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/setup_screen.fxml"));
            Parent root = loader.load();

            SetupScreen controller = loader.getController();
            controller.init(game); // Pass game state to setup screen

            stage.setScene(new Scene(root, 600, 400));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

