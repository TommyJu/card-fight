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


public class MainApplication extends Application {
    private static final int HEIGHT = 800;
    private static final int WIDTH = 800;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        stage.setTitle("Elemental Warfare");

        Button button = new Button ();
        button.setOnAction(e -> {System.out.println("click");});
        // 785 x 1100
        Image image = new Image("fire10.png");
        ImageView imageView = new ImageView(image);
//        imageView.setViewport(new Rectangle2D(0, 0, 200, 200));
        imageView.setFitWidth(400);
        imageView.setFitHeight(550);
        imageView.setX(0);
        imageView.setY(0);

        imageView.setOnMouseClicked((MouseEvent e) -> {
            System.out.println("Hello World");
        });

//        imageView.addEventHandler(CardEvent.ANY, new EventHandler<CardEvent>() {
//
//        });

//        EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent e) {
//                System.out.println("Hello World");
//            }
//        };

        StackPane pane = new StackPane(imageView);

        Scene scene = new Scene(pane, WIDTH, HEIGHT);
        stage.setScene(scene);
        stage.show();
    }
}
