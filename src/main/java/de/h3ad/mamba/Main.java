package de.h3ad.mamba;

import de.h3ad.mamba.gameobjects.Board;
import de.h3ad.mamba.gameobjects.Spider;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) {

        Group group = new Group();
        Canvas canvas = new Canvas(800, 600);
        group.getChildren().add(canvas);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        Scene scene = new Scene(group);
        stage.setScene(scene);

        stage.setTitle("Norman ist doof und passt nie auf!!!");


        gc.setFill(Color.BLACK);
        gc.setStroke(Color.BLACK);
//        gc.fillRect(10,10,100,100);
//        gc.moveTo(110,100);
        gc.strokeLine(110, 110, 120, 120);

        Game g = Game.init(scene, gc);
        new Board();
        new Spider();
        g.start();

        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {

            }
        });

//        button.setOnKeyPressed(new EventHandler<KeyEvent>() {
//            @Override
//            public void handle(KeyEvent event) {
//                if (event.getCode() == KeyCode.ENTER) {
//                    System.out.println("Enter Pressed");
//                }
//            }
//        });

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                Canvas c = gc.getCanvas();
                gc.clearRect(0,0, c.getWidth(),c.getHeight());
                g.loop(now);
            }
        };

        timer.start();
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}