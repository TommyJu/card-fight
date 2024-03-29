package ca.bcit.comp2522.termproject.cards;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.scene.layout.StackPane;
import javafx.scene.Scene;
import javafx.scene.canvas.*;
import javafx.scene.paint.Color;

import java.util.List;


public class MainApplication extends Application {
    private static final int HEIGHT = 800;
    private static final int WIDTH = 800;


//    public void createCard(Deck deck, Pane pane) {
//        Card firstCardInHand = deck.getHand().getFirst();
//        // 785 x 1100 -> 128 x 179
//        Image image = new Image(firstCardInHand.getImage(), 128, 179, true, true);
//        ImageView imageView = new ImageView(image);
//        imageView.setOnMouseClicked((MouseEvent e) -> {
//            deck.discardCard(firstCardInHand);
//            deck.dealCard();
//            pane.getChildren().clear();
//            createCard(deck, pane);
//        });
//        pane.getChildren().add(imageView);
//    }

    @Override
    public void start(final Stage stage) {
        stage.setTitle("Elemental Warfare");

        Deck deck = new Deck();

        Image image = new Image(deck.getHand().getFirst().getImage(), 128, 179, true, true);
        ImageView imageView = new ImageView(image);
        imageView.setOnMouseClicked((MouseEvent e) -> {
            deck.discardCard(deck.getHand().getFirst());
            deck.dealCard();
            imageView.setImage(new Image(deck.getHand().getFirst().getImage(), 128, 179, true, true));
        });

        StackPane pane = new StackPane(imageView);

        Scene scene = new Scene(pane, WIDTH, HEIGHT);
        stage.setScene(scene);
        stage.show();
    }
    public static void main(final String[] args) {
        launch(args);
    }
}
