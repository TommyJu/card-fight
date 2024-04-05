package ca.bcit.comp2522.termproject.cards;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class StartSceneController {
    private static Stage stage;
    private static Scene scene;
//    private Parent root;
    public static Player player1;

    @FXML
    Label testLabel;


    // This method is automatically called by the FXML loader
    public void initialize() {
        Deck deck = new Deck();
        player1 = new HumanPlayer("Chris", deck);
    }

    public void switchToGameScene(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("GameScene.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void test() {
        testLabel.setText(player1.getName());
    }
}
