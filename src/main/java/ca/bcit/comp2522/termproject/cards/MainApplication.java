package ca.bcit.comp2522.termproject.cards;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Iterator;
import java.util.List;

public class MainApplication extends Application {
    private static final int SCREEN_HEIGHT = 800;
    private static final int SCREEN_WIDTH = 800;
    private static final int ENEMY_X_POSITION = 350;
    private static final int ENEMY_HEIGHT = 200;
    private static final int ENEMY_Y_POSITION = 320;

    private static final int CARDS_IN_HAND_HEIGHT = 500;
    private static final int CARDS_IN_HAND_GAP = 155;
    private static final int GAME_TITLE_HEIGHT = 500;
    private static final int GAME_TITLE_X_POSITION = 135;
    private static final int PLAY_BUTTON_X_POSITION = 365;
    private static final int PLAY_BUTTON_Y_POSITION = 500;

    private static Player player1 = new HumanPlayer("hey", null);
    MediaPlayer mediaPlayer;
    public void music() {
        String musicFileName = "sound-tracks/background_music.mp3";
        Media backgroundMusic = new Media (Path.of(musicFileName).toUri().toString());
        mediaPlayer = new MediaPlayer(backgroundMusic);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.play();
    }
    public static BackgroundImage createBackgroundImage(String fileName) {
        Image backgroundImage = new Image(fileName);
        BackgroundImage background = new BackgroundImage(backgroundImage,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                new BackgroundSize(1.0, 1.0, true, true, false, false));
        return background;
    }
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
    public static void placeEnemy(final Pane root) {
        Image enemyImage = new Image("master_splinter.png",
                ENEMY_HEIGHT, ENEMY_HEIGHT,
                true, true);
        ImageView enemyImageView = new ImageView(enemyImage);
        enemyImageView.setX(ENEMY_X_POSITION);
        enemyImageView.setY(ENEMY_Y_POSITION);
        root.getChildren().add(enemyImageView);
    }

    public static Pane createGameWindow(Deck deck) {
        Pane root = new Pane();
        placeCardsInHand(root, deck);
        placeEnemy(root);
        BackgroundImage background = createBackgroundImage("game_background.jpg");
        root.setBackground(new Background(background));
        return root;
    }

    public static void createStartMenu(Stage primaryStage, Deck deck) {
        // Create game title
        Image gameTitleImage = new Image("game_title.png",
                GAME_TITLE_HEIGHT, GAME_TITLE_HEIGHT,
                true, true);
        ImageView gameTitleImageView = new ImageView(gameTitleImage);
        gameTitleImageView.setX(GAME_TITLE_X_POSITION);
        // Play button element and event handler
        Button playButton = new Button("PLAY");
        playButton.getStyleClass().add("button");
        playButton.setTranslateX(PLAY_BUTTON_X_POSITION);
        playButton.setTranslateY(PLAY_BUTTON_Y_POSITION);
        playButton.setOnAction(e -> {
            Pane gameWindow = createGameWindow(deck);
            Scene gameScene = new Scene(gameWindow, SCREEN_WIDTH, SCREEN_HEIGHT, Color.WHITE);
            primaryStage.setScene(gameScene);
//            startMatch(player1);
        });
        Pane root = new Pane(gameTitleImageView, playButton);
        // Create background image and scene
        BackgroundImage background = createBackgroundImage("start_background.jpg");
        root.setBackground(new Background(background));
        Scene startScene = new Scene(root, SCREEN_WIDTH, SCREEN_HEIGHT, Color.WHITE);
        // Apply CSS to scene
        startScene.getStylesheets().add("styles.css");
        primaryStage.setScene(startScene);
    }

//    public static void startMatch(Player p1) {
//        System.out.println("test start?");
//        new Game(p1);
//    }

    @Override
    public void start(final Stage primaryStage) {
        music();
        Deck deck = new Deck();

        // Setting the stage
//        createStartMenu(primaryStage, deck);
//        Pane gameWindow = createGameWindow(deck);
//        Scene scene = new Scene(gameWindow, SCREEN_WIDTH, SCREEN_HEIGHT, Color.WHITE);
//        primaryStage.setScene(scene);
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("StartScene.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
//            StartSceneController startController = loader.getController();

//            scene.getStylesheets().add(getClass().getResource("start_scene.css").toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.setTitle("Card Fight!");
            primaryStage.show();
        } catch (IOException e) {
            System.err.println("Failed to open FXML file");
        } catch (Exception e) {
            System.err.println("Error loading starting screen");
        }
    }
    public static void main(final String[] args) {
        launch(args);
    }
}
