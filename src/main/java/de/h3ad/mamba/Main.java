package de.h3ad.mamba;

import de.h3ad.mamba.gameobjects.Board;
import de.h3ad.mamba.gameobjects.SafeZone;
import de.h3ad.mamba.gameobjects.Snake;
import de.h3ad.mamba.gameobjects.Spider;
import de.h3ad.mamba.math.Vector3;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {

    private static final String GAME_TITLE = "Timo mag Blumen";

    @Override
    public void start(Stage stage) {
        Group group = new Group();
        Canvas canvas = new Canvas(800, 600);
        group.getChildren().add(canvas);

        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.BLACK);
        gc.setStroke(Color.BLACK);

        Scene scene = new Scene(group);
        stage.setScene(scene);
        stage.setTitle(GAME_TITLE);

        initGameSetup(scene, gc);

        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    private void initGameSetup(final Scene scene, final GraphicsContext graphicsContext) {
        Game g = Game.init(scene, graphicsContext);
        new Board();
        new SafeZone();
        new Spider();
        new Snake(new Vector3(Board.BOARD_WIDTH/2, Board.BOARD_HEIGHT/2));
        g.start();
    }

}
