package ca.bcit.comp2522.termproject.cards;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.IOException;
import java.nio.file.Path;


public class MainApplication extends Application {
    public static HumanPlayer player1;
    public static MediaPlayer mediaPlayer;
    public void music() {
        String musicFileName = "sound-tracks/background_music.mp3";
        Media backgroundMusic = new Media (Path.of(musicFileName).toUri().toString());
        mediaPlayer = new MediaPlayer(backgroundMusic);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.play();
    }

    @Override
    public void start(final Stage primaryStage) {
        music();
        // Initialize the player here.
        player1 = new HumanPlayer("Chris", new Deck());
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("StartScene.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
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
