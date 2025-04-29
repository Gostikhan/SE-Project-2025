package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import seng201.team0.services.GameEnvironment;

public class StartScreen {

    private Stage stage;
    private GameEnvironment game;

    public void init(Stage stage, GameEnvironment game) {
        this.stage = stage;
        this.game = game;
    }
    @FXML
    public void onStartPressed() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/setup_screen.fxml"));
            Parent root = loader.load();

            SetupScreen controller = loader.getController();
            controller.init(game);

            stage.setScene(new Scene(root, 620, 400));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void onOptionsPressed() {
        System.out.println("Options is pressed");

    }


    @FXML
    public void onExitPressed() {
        System.exit(0);
    }
}
