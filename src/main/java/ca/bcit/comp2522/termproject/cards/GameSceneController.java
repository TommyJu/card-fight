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
    private ImageView firstCardInHand;
    @FXML
    private ImageView secondCardInHand;
    @FXML
    private ImageView thirdCardInHand;
    @FXML
    private ImageView fourthCardInHand;
    @FXML
    private ImageView fifthCardInHand;

    // This method is automatically called by the FXML loader
    public void initialize() {

    }

    public void switchToStartScene(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("StartScene.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void setImage() {
        fifthCardInHand.setImage(new Image("master_splinter.png"));
    }

    public void startGame() {
        // game logic here
    }

}
