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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GameSceneController {
    private static Stage stage;
    private static Scene scene;
    private static Game game;
    private static Player player1;
    private static Deck player1Deck;
    private static List<Card> player1Hand;
    @FXML
    private static List<ImageView> allCardsInHand;

    @FXML
    private ImageView firstCardInHand, secondCardInHand, thirdCardInHand, fourthCardInHand, fifthCardInHand;

    // This method is automatically called by the FXML loader
    public void initialize() {
        // A game is created with the player initialized on the start screen
        game = new Game(StartSceneController.player1);

        player1 = game.getPlayer1();
        player1Deck = player1.getDeck();
        player1Hand = player1Deck.getHand();

        allCardsInHand = new ArrayList<>();
        allCardsInHand.addAll(
                Arrays.asList(firstCardInHand, secondCardInHand, thirdCardInHand, fourthCardInHand, fifthCardInHand));
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
        int counter = 0;
        for (ImageView card : allCardsInHand) {
            card.setImage(player1Hand.get(counter).getImage());
            counter++;
        }
    }

    // Card on click methods
    public void firstCardOnClick() {
        Card selectedCard = player1Hand.get(0);
        Card newCard = player1Deck.dealNewCard(selectedCard);
        firstCardInHand.setImage(newCard.getImage());
    }

    public void secondCardOnClick() {
        Card selectedCard = player1Hand.get(1);
        Card newCard = player1Deck.dealNewCard(selectedCard);
        secondCardInHand.setImage(newCard.getImage());
    }

    public void thirdCardOnClick() {
        Card selectedCard = player1Hand.get(2);
        Card newCard = player1Deck.dealNewCard(selectedCard);
        thirdCardInHand.setImage(newCard.getImage());
    }

    public void fourthCardOnClick() {
        Card selectedCard = player1Hand.get(3);
        Card newCard = player1Deck.dealNewCard(selectedCard);
        fourthCardInHand.setImage(newCard.getImage());
    }

    public void fifthCardOnClick() {
        Card selectedCard = player1Hand.get(4);
        Card newCard = player1Deck.dealNewCard(selectedCard);
        fifthCardInHand.setImage(newCard.getImage());
    }

}
