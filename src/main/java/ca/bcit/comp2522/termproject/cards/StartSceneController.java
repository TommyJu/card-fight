package ca.bcit.comp2522.termproject.cards;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class StartSceneController {
    public static Stage stage;
    private static Scene scene;
    public static Player player1;
    @FXML
    private ImageView sailBoat;
    @FXML
    private Label playerName, gamesPlayed, gamesWon, winRate;
    @FXML
    private TextField nameField;
    @FXML
    private Button nameSubmit;

    // This method is automatically called by the FXML loader
    public void initialize() {
        playSailBoatAnimation();
        // Save the player every time we return initialize the start menu
        player1 = MainApplication.player1;
        MainApplication.serializeObject(player1,
                MainApplication.PLAYER_SAVE_FILEPATH,
                MainApplication.PLAYER_SAVE_FILENAME);
        playerName.setText(player1.getName());
        gamesPlayed.setText(String.format("Games Played: %d", player1.getGamesPlayed()));
        gamesWon.setText(String.format("Games Won: %d", player1.getTotalWins()));
        winRate.setText(String.format("Win Rate: %%%.1f", player1.getWinRate()));
    }

    public void switchToGameScene(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("GameScene.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToDeckBuildScene(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("DeckBuildScene.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void playSailBoatAnimation() {
        TranslateTransition translate = new TranslateTransition();
        translate.setNode(sailBoat);
        translate.setDuration(Duration.minutes(1));
        translate.setCycleCount(TranslateTransition.INDEFINITE);
        translate.setByX(1300);
        translate.play();
    }

    public void submit() {
        String defaultName = "World's Longest Name";
        String nameInput = nameField.getText().strip();
        if (nameInput.isBlank()) {
            return;
        }
        if (nameInput.length() > 20) {
            playerName.setText(defaultName);
            player1.setName(defaultName);
            return;
        }
        playerName.setText(nameInput);
        player1.setName(nameInput);
        // Save player changes
        MainApplication.serializeObject(player1,
                MainApplication.PLAYER_SAVE_FILEPATH,
                MainApplication.PLAYER_SAVE_FILENAME);
    }
}
