package ca.bcit.comp2522.termproject.cards;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

public class PlayerWinController {
    private static Scene scene;
    @FXML
    Label gameResult;
    @FXML
    Button okButton;
    @FXML
    ImageView finalPlayerCard, finalAICard;

    public void initialize() {
        Game game = GameSceneController.game;
        Player player1 = game.getPlayer1();
        if (game.getIsPreviousRoundWon()) {
            gameResult.setText(player1.getName() + " wins! Player stats have been updated.");
        }
        else {
            gameResult.setText("You lose! Player stats have been updated.");
        }
        finalAICard.setImage(GameSceneController.game.getAISelectedCard().getImage());
        finalPlayerCard.setImage(GameSceneController.game.getPlayer1SelectedCard().getImage());
    }
    public void switchToStartScene() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("StartScene.fxml"));
        scene = new Scene(root);
        StartSceneController.stage.setScene(scene);
        StartSceneController.stage.show();
    }
//    public void setGameResult(String message) {
//        gameResult.setText(message);
//    }
}
