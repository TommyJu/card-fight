package ca.bcit.comp2522.termproject.cards;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.paint.Color;

public class MainApplication extends Application {
    private static final int SCREEN_HEIGHT = 800;
    private static final int SCREEN_WIDTH = 800;
    @Override
    public void start(final Stage primaryStage) {
        Deck deck = new Deck();

        // Setting the stage
        Image backgroundImage = new Image("background.jpg");
        BackgroundImage background = new BackgroundImage(backgroundImage,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                new BackgroundSize(1.0, 1.0, true, true, false, false));
        Pane root = new Pane(imageView); // under construction
        root.setBackground(new Background(background));
        Scene scene = new Scene(root, SCREEN_WIDTH, SCREEN_HEIGHT, Color.WHITE);
        primaryStage.setTitle("Elemental Warfare");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public static void main(final String[] args) {
        launch(args);
    }
}
