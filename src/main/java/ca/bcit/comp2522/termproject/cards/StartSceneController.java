package ca.bcit.comp2522.termproject.cards;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
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

    // This method is automatically called by the FXML loader
    public void initialize() {
        Deck deck = new Deck();
        player1 = new HumanPlayer("Chris", deck);
        playSailBoatAnimation();
    }

    public void switchToGameScene(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("GameScene.fxml"));
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
}
