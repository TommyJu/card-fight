package ca.bcit.comp2522.termproject.cards;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

public class GameSceneController {
    private static Stage stage;
    private static Scene scene;
    private static Game game;

    @FXML
    private ImageView firstCardInHand, secondCardInHand, thirdCardInHand, fourthCardInHand, fifthCardInHand;

    // This method is automatically called by the FXML loader
    public void initialize() {
        // A game is created with the player initialized on the start screen
        game = new Game(StartSceneController.player1);
        updateCardImages();
    }

    public void switchToStartScene(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("StartScene.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void updateCardImages() {
        firstCardInHand.setImage(game.getPlayer1().getDeck().getHand().get(0).getImage());
        secondCardInHand.setImage(game.getPlayer1().getDeck().getHand().get(1).getImage());
        thirdCardInHand.setImage(game.getPlayer1().getDeck().getHand().get(2).getImage());
        fourthCardInHand.setImage(game.getPlayer1().getDeck().getHand().get(3).getImage());
        fifthCardInHand.setImage(game.getPlayer1().getDeck().getHand().get(4).getImage());
    }

    public void startGame() {
        // game logic here
    }

}
