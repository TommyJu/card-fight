package ca.bcit.comp2522.termproject.cards;

import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GameSceneController {
    private static Parent root;
    private static Scene scene;
    public static Game game;
    private static Player player1;
    private static Deck player1Deck;
    private static List<Card> player1Hand;
    private static List<ImageView> allCardsInHand;
    @FXML
    private ImageView firstCardInHand, secondCardInHand, thirdCardInHand, fourthCardInHand, fifthCardInHand;
    @FXML
    public ImageView AISelectedCardDisplay, playerSelectedCardDisplay;
    @FXML
    private Label player1Name, player2Name, roundResult;
    @FXML
    private Label player1FireWins, player1GrassWins, player1WaterWins, player2FireWins, player2GrassWins, player2WaterWins;
    private FadeTransition AISelectedCardAnimation, playerSelectedCardAnimation;

    // This method is automatically called by the FXML loader
    @FXML
    public void initialize() {
        // A game is created with the player initialized on the start screen
        game = new Game(StartSceneController.player1);

        player1 = game.getPlayer1();
        player1Deck = player1.getDeck();
        player1Hand = player1Deck.getHand();

        player1Name.setText(player1.getName());
        player2Name.setText(game.getPlayer2().getName());

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

    public void switchToGameEndScene() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("playerWin.fxml"));
            scene = new Scene(root);
            StartSceneController.stage.setScene(scene);
            StartSceneController.stage.show();
        } catch (IOException e) {
            System.err.println("Failed to load FXML for game end screen.");
        }
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

    public void updateRoundResult(Card player1Card, Card player2Card) {
        if (game.getIsPreviousRoundWon()) {
            roundResult.setText(player1.getName() + " has won this round!");
            roundResult.setTextFill(Color.LIGHTGREEN);
            String elementToUpdate = player1Card.getElement();
            updatePlayer1WinCounter(elementToUpdate);
        } else {
            roundResult.setText(game.getPlayer2().getName() + " has won this round!");
            roundResult.setTextFill(Color.RED);
            String elementToUpdate = player2Card.getElement();
            updatePlayer2WinCounter(elementToUpdate);
        }
    }

    public void updatePlayer1WinCounter(String element) {
        switch(element) {
            case "fire" -> {
                int fireWins = game.getP1Wins().get(1).size();
                player1FireWins.setText(String.valueOf(fireWins));
            }
            case "water" -> {
                int waterWins = game.getP1Wins().get(2).size();
                player1WaterWins.setText(String.valueOf(waterWins));
            }
            case "grass" -> {
                int grassWins = game.getP1Wins().get(3).size();
                player1GrassWins.setText(String.valueOf(grassWins));
            }
        }
    }

    public void updatePlayer2WinCounter(String element) {
        switch(element) {
            case "fire" -> {
                int fireWins = game.getP2Wins().get(1).size();
                player2FireWins.setText(String.valueOf(fireWins));
            }
            case "water" -> {
                int waterWins = game.getP2Wins().get(2).size();
                player2WaterWins.setText(String.valueOf(waterWins));
            }
            case "grass" -> {
                int grassWins = game.getP2Wins().get(3).size();
                player2GrassWins.setText(String.valueOf(grassWins));
            }
        }
    }

//    public void setGameEndScreenText(String message) {
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("playerWin.fxml"));
//        try {
//            root = loader.load();
//        } catch (IOException e) {
//            System.err.println("Failed to load the player win FXML file.");
//        }
//        PlayerWinController playerWinController = loader.getController();
//        playerWinController.gameResult.setText(message);
//    }
    public void exitOnGameWin() {
        Player gameWinner = game.determineGameWinner();
        if (gameWinner == null) { return; }
        else if (gameWinner.equals(player1)) {
//            setGameEndScreenText(player1Name + " wins! Player stats have been updated.");
            player1.incrementTotalWins();
            player1.incrementGamesPlayed();
            player1.calculateWinRate();
            player1Deck.shuffle();
            switchToGameEndScene();
        }
        else {
//            setGameEndScreenText("You lose! Player stats have been updated.");
            player1.incrementGamesPlayed();
            player1.calculateWinRate();
            player1Deck.shuffle();
            switchToGameEndScene();
        }
    }

//    public void exitOnGameWin() {
//        Player gameWinner = game.determineGameWinner();
//        if (gameWinner.equals(player1)) {
//            try {
//                switchToStartScene();
//            } catch (IOException e) {
//                System.err.println("Failed to switch from game scene to start scene.");
//            }
//        }
//        else {
//
//        }
//    }

    // Card on click methods
    public void firstCardOnClick() {
        Card selectedCard = player1Hand.get(0);
        Card newCard = player1Deck.dealNewCard(selectedCard);
        firstCardInHand.setImage(newCard.getImage());
        Card AISelectedCard = game.playRoundAgainstAI(selectedCard);
        updateSelectedCardsDisplay(selectedCard, AISelectedCard);
        updateRoundResult(selectedCard, AISelectedCard);
        exitOnGameWin();
    }

    public void secondCardOnClick() {
        Card selectedCard = player1Hand.get(1);
        Card newCard = player1Deck.dealNewCard(selectedCard);
        secondCardInHand.setImage(newCard.getImage());
        Card AISelectedCard = game.playRoundAgainstAI(selectedCard);
        updateSelectedCardsDisplay(selectedCard, AISelectedCard);
        updateRoundResult(selectedCard, AISelectedCard);
        exitOnGameWin();
    }

    public void thirdCardOnClick() {
        Card selectedCard = player1Hand.get(2);
        Card newCard = player1Deck.dealNewCard(selectedCard);
        thirdCardInHand.setImage(newCard.getImage());
        Card AISelectedCard = game.playRoundAgainstAI(selectedCard);
        updateSelectedCardsDisplay(selectedCard, AISelectedCard);
        updateRoundResult(selectedCard, AISelectedCard);
        exitOnGameWin();
    }

    public void fourthCardOnClick() {
        Card selectedCard = player1Hand.get(3);
        Card newCard = player1Deck.dealNewCard(selectedCard);
        fourthCardInHand.setImage(newCard.getImage());
        Card AISelectedCard = game.playRoundAgainstAI(selectedCard);
        updateSelectedCardsDisplay(selectedCard, AISelectedCard);
        updateRoundResult(selectedCard, AISelectedCard);
        exitOnGameWin();
    }

    public void fifthCardOnClick() {
        Card selectedCard = player1Hand.get(4);
        Card newCard = player1Deck.dealNewCard(selectedCard);
        fifthCardInHand.setImage(newCard.getImage());
        Card AISelectedCard = game.playRoundAgainstAI(selectedCard);
        updateSelectedCardsDisplay(selectedCard, AISelectedCard);
        updateRoundResult(selectedCard, AISelectedCard);
        exitOnGameWin();
    }
}
