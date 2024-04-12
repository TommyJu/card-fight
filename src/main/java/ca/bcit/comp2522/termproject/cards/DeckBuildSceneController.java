package ca.bcit.comp2522.termproject.cards;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;
import java.util.List;

public class DeckBuildSceneController {
    private static Scene scene;
    private static List<Card> newDeck;
    @FXML
    public void initialize() {
        List<Card> newDeck = MainApplication.player1.getDeck().getReserve();
        List<Card> PlayerHand = MainApplication.player1.getDeck().getHand();
        newDeck.addAll(PlayerHand);
    }
    public void switchToStartScene() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("StartScene.fxml"));
        scene = new Scene(root);
        StartSceneController.stage.setScene(scene);
        StartSceneController.stage.show();
    }
}
