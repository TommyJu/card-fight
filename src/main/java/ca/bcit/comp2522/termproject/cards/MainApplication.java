package ca.bcit.comp2522.termproject.cards;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.canvas.*;
import javafx.scene.paint.Color;

import java.util.List;


public class MainApplication extends Application {
    private static final int SCREEN_HEIGHT = 800;
    private static final int SCREEN_WIDTH = 800;
    @Override
    public void start(final Stage primaryStage) {
        primaryStage.setTitle("Elemental Warfare");

//        ImageView backgroundImage = new ImageView("background.jpg");
//        backgroundImage.setViewport(new Rectangle2D(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT));

        Image backgroundImage = new Image("background.jpg");
        BackgroundImage background = new BackgroundImage(backgroundImage,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                new BackgroundSize(1.0, 1.0, true, true, false, false));
//        background.setViewport(new Rectangle2D(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT));


        Deck deck = new Deck();

        Image image = new Image(deck.getHand().getFirst().getImage(), 128, 179, true, true);
        ImageView imageView = new ImageView(image);
        imageView.setOnMouseClicked((MouseEvent e) -> {
            deck.discardCard(deck.getHand().getFirst());
            deck.dealCard();
            imageView.setImage(new Image(deck.getHand().getFirst().getImage(), 128, 179, true, true));
        });

//        StackPane pane = new StackPane(imageView);
//
//        Scene scene = new Scene(pane, SCREEN_WIDTH, SCREEN_HEIGHT);
//        primaryStage.setScene(scene);
//        primaryStage.show();

        // Setting the stage
        Pane root = new Pane(imageView);
        root.setBackground(new Background(background));
        Scene scene = new Scene(root, SCREEN_WIDTH, SCREEN_HEIGHT, Color.WHITE);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public static void main(final String[] args) {
        launch(args);
    }
}
