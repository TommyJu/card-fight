package ca.bcit.comp2522.termproject.cards;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.scene.layout.StackPane;
import javafx.scene.Scene;
import javafx.scene.canvas.*;
import javafx.scene.paint.Color;

public class MainApplication extends Application {
    private static final int HEIGHT = 1000;
    private static final int WIDTH = 1000;

    private Parent createContent() {
        Rectangle box = new Rectangle (100, 50, Color.RED);
        Button button = new Button ();
        button.setOnAction(e -> {System.out.println("click");});

        return new Pane(button);
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        stage.setTitle("Elemental Warfare");
//        StackPane pane = new StackPane();

        Scene scene = new Scene(createContent(), WIDTH, HEIGHT);
        stage.setScene(scene);
        stage.show();
    }
}
