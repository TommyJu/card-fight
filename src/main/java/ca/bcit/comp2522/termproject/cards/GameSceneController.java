package ca.bcit.comp2522.termproject.cards;

import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GameSceneController {
    private static Stage stage;
    private static Scene scene;
    private static Parent root;
    private static Game game;
    private static Player player1;
    private static Deck player1Deck;
    private static List<Card> player1Hand;
    private static List<ImageView> allCardsInHand;
    @FXML
    private ImageView firstCardInHand, secondCardInHand, thirdCardInHand, fourthCardInHand, fifthCardInHand;
    @FXML
    private ImageView AISelectedCardDisplay;
    @FXML
    private ImageView playerSelectedCardDisplay;
    private FadeTransition AISelectedCardAnimation;
    private FadeTransition playerSelectedCardAnimation;

    // This method is automatically called by the FXML loader
    @FXML
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
        AISelectedCardAnimation = createCardAnimation(AISelectedCardDisplay);
        playerSelectedCardAnimation = createCardAnimation(playerSelectedCardDisplay);
    }

    public void updateCardImages() {
        int counter = 0;
        for (ImageView card : allCardsInHand) {
            card.setImage(player1Hand.get(counter).getImage());
            counter++;
        }
    }

    public void switchToStartScene() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("StartScene.fxml"));
        scene = new Scene(root);
        StartSceneController.stage.setScene(scene);
        StartSceneController.stage.show();
    }

    public void updateSelectedCardsDisplay(Card player1Card, Card AICard) {
        AISelectedCardDisplay.setImage(AICard.getImage());
        playerSelectedCardDisplay.setImage(player1Card.getImage());
        AISelectedCardDisplay.setVisible(true);
        playerSelectedCardDisplay.setVisible(true);
        playerSelectedCardAnimation.play();
        AISelectedCardAnimation.play();
    }

    public FadeTransition createCardAnimation(ImageView card) {
        FadeTransition fade = new FadeTransition();
        fade.setNode(card);
        fade.setDuration(Duration.seconds(0.5));
        fade.setInterpolator(Interpolator.LINEAR);
        fade.setFromValue(0);
        fade.setToValue(1);
        return fade;
    }

    public void exitOnGameWin() {
        Player gameWinner = game.determineGameWinner();
        if (gameWinner == null) {
            return;
        }
        else {
            try {
                switchToStartScene();
            } catch (IOException e) {
                System.err.println("Failed to switch from game scene to start scene.");
            }

        }
    }

    // Card on click methods
    public void firstCardOnClick() {
        Card selectedCard = player1Hand.get(0);
        Card newCard = player1Deck.dealNewCard(selectedCard);
        firstCardInHand.setImage(newCard.getImage());
        Card AISelectedCard = game.playRoundAgainstAI(selectedCard);
        updateSelectedCardsDisplay(selectedCard, AISelectedCard);
        exitOnGameWin();
    }

    public void secondCardOnClick() {
        Card selectedCard = player1Hand.get(1);
        Card newCard = player1Deck.dealNewCard(selectedCard);
        secondCardInHand.setImage(newCard.getImage());
        Card AISelectedCard = game.playRoundAgainstAI(selectedCard);
        updateSelectedCardsDisplay(selectedCard, AISelectedCard);
        exitOnGameWin();
    }

    public void thirdCardOnClick() {
        Card selectedCard = player1Hand.get(2);
        Card newCard = player1Deck.dealNewCard(selectedCard);
        thirdCardInHand.setImage(newCard.getImage());
        Card AISelectedCard = game.playRoundAgainstAI(selectedCard);
        updateSelectedCardsDisplay(selectedCard, AISelectedCard);
        exitOnGameWin();
    }

    public void fourthCardOnClick() {
        Card selectedCard = player1Hand.get(3);
        Card newCard = player1Deck.dealNewCard(selectedCard);
        fourthCardInHand.setImage(newCard.getImage());
        Card AISelectedCard = game.playRoundAgainstAI(selectedCard);
        updateSelectedCardsDisplay(selectedCard, AISelectedCard);
        exitOnGameWin();
    }

    public void fifthCardOnClick() {
        Card selectedCard = player1Hand.get(4);
        Card newCard = player1Deck.dealNewCard(selectedCard);
        fifthCardInHand.setImage(newCard.getImage());
        Card AISelectedCard = game.playRoundAgainstAI(selectedCard);
        updateSelectedCardsDisplay(selectedCard, AISelectedCard);
        exitOnGameWin();
    }
}
