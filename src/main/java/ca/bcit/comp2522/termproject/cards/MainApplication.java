package ca.bcit.comp2522.termproject.cards;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.paint.Color;

import java.util.Iterator;
import java.util.List;

public class MainApplication extends Application {
    private static final int SCREEN_HEIGHT = 800;
    private static final int SCREEN_WIDTH = 800;

    private static final int CARDS_IN_HAND_HEIGHT = 500;
    private static final int CARDS_IN_HAND_GAP = 155;
    public static void placeCardsInHand(final Pane root, final Deck deck) {
        List<ImageView> cardsInHand = deck.createCardsInHand();
        Iterator<ImageView> cardsInHandIterator = cardsInHand.iterator();
        int newCardXPosition = 0;
        while(cardsInHandIterator.hasNext()) {
            ImageView cardInHand = cardsInHandIterator.next();
            cardInHand.setY(CARDS_IN_HAND_HEIGHT);
            // create a row of cards
            cardInHand.setX(newCardXPosition);
            newCardXPosition += CARDS_IN_HAND_GAP;
            root.getChildren().add(cardInHand);
        }

    }
    @Override
    public void start(final Stage primaryStage) {
        Deck deck = new Deck();
        List<ImageView> cardsInHand = deck.createCardsInHand();

        // Setting the stage
        Image backgroundImage = new Image("background.jpg");
        BackgroundImage background = new BackgroundImage(backgroundImage,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                new BackgroundSize(1.0, 1.0, true, true, false, false));
        Pane root = new Pane();
        placeCardsInHand(root, deck);

        root.setBackground(new Background(background));
        Scene scene = new Scene(root, SCREEN_WIDTH, SCREEN_HEIGHT, Color.WHITE);
        primaryStage.setTitle("Card Fight!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public static void main(final String[] args) {
        launch(args);
    }
}
