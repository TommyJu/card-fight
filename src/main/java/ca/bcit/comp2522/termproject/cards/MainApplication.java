package ca.bcit.comp2522.termproject.cards;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;


public class MainApplication extends Application {
    public static final String DEFAULT_SAVE_FILENAME = "default_player";
    public static final String DEFAULT_SAVE_FILEPATH = "default-save";
    public static final String PLAYER_SAVE_FILEPATH = "player-save";
    public static final String PLAYER_SAVE_FILENAME = "player";
    public static Player player1;
    public static MediaPlayer mediaPlayer;
    public void music() {
        String musicFileName = "sound-tracks/background_music.mp3";
        Media backgroundMusic = new Media (Path.of(musicFileName).toUri().toString());
        mediaPlayer = new MediaPlayer(backgroundMusic);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.play();
    }

    public static void serializeObject(final Object object, final String filePath, final String fileName) {
        // Existing files will be overwritten, not appended to
        try (ObjectOutputStream objectOutputStream =
                     new ObjectOutputStream(
                             new FileOutputStream(filePath + "/" + fileName + ".ser", false))) {
            objectOutputStream.writeObject(object);
        } catch (IOException e) {
            System.err.println("Failed to serialize object.");
        }
    }

    public static Object deserializeObject(final String filePath, final String fileName) {
        try (ObjectInputStream objectInputStream =
                     new ObjectInputStream(new FileInputStream(filePath + "/" + fileName + ".ser"))) {
            return objectInputStream.readObject();
        } catch (IOException e) {
            System.err.println("Failed to deserialize player.");
            return null;
        } catch (ClassNotFoundException e) {
            System.err.println("Deserialization failed, no class found.");
            return null;
        }
    }

    public static void createSaveDirectory() {
        Path directoryPath = Path.of(PLAYER_SAVE_FILEPATH);
        if (Files.exists(directoryPath)) {
            return;
        }
        try {
            Files.createDirectory(directoryPath);
        } catch (IOException e) {
            System.err.println("Failed to create a directory.");
        }
    }

    public static void deleteSaveDirectory() {
        Path directoryPath = Path.of(PLAYER_SAVE_FILEPATH);
        try {
            Files.delete(directoryPath);
        } catch (IOException e) {
            System.err.println("Failed to create a directory.");
        }
    }

    public static boolean isPlayerSaved() {
        return Files.exists(Path.of(PLAYER_SAVE_FILEPATH + "/" + PLAYER_SAVE_FILENAME + ".ser"));
    }

    @Override
    public void start(final Stage primaryStage) {
        music();
        if (isPlayerSaved()) {
            // Cast deserialized Object to Player
            player1 = (Player)deserializeObject(PLAYER_SAVE_FILEPATH, PLAYER_SAVE_FILENAME);
            if (player1 == null) { // deserialization failure returns null
                // Deserialize default player
                player1 = (Player)deserializeObject(DEFAULT_SAVE_FILEPATH, DEFAULT_SAVE_FILENAME);
                createSaveDirectory();
                serializeObject(player1, PLAYER_SAVE_FILEPATH, PLAYER_SAVE_FILENAME);
            }
        } else { // Create a new player and save directory
            player1 = (Player)deserializeObject(DEFAULT_SAVE_FILEPATH, DEFAULT_SAVE_FILENAME);
            createSaveDirectory();
        }
        System.out.println(player1.getDeck().getReserve().size() + "\n");
        System.out.println(player1.getDeck().getHand().size());

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("StartScene.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Card Fight!");
            primaryStage.show();
        } catch (IOException e) {
            System.err.println("Failed to open FXML file");
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Error loading starting screen");
        }
    }
    public static void main(final String[] args) {
        launch(args);
    }
}
