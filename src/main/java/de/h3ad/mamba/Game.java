package de.h3ad.mamba;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javafx.animation.AnimationTimer;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public class Game {

    private static Game GAME;

    private final Input input;
    private final GameLoop gameLoop;
    private final GraphicsContext graphicsContext;
    private final List<GameObject> gameObjects = new ArrayList<>();

    boolean running = false;
    boolean paused = false;

    private Game(Scene scene, GraphicsContext graphicsContext) {
        this.graphicsContext = graphicsContext;
        this.input = new Input(this, scene);
        gameLoop = new GameLoop();
    }

    public static Game init(Scene scene, GraphicsContext graphicsContext) {
        if (GAME == null) {
            GAME = new Game(scene, graphicsContext);
        }
        return GAME;
    }

    public static Game getInstance() {
        if (GAME == null) {
            throw new IllegalStateException("No game context exists yet.");
        }
        return GAME;
    }

    public void start() {
        this.running = true;
        gameLoop.start();
    }

    public void stop() {
        this.running = false;
        gameLoop.stop();
    }

    public void togglePause() {
        this.paused = !this.paused;
    }

    public GraphicsContext getGraphicsContext() {
        return graphicsContext;
    }

    public Input getInput() {
        return input;
    }

    public void register(GameObject gameObject) {
        gameObjects.add(gameObject);
    }

    public void unregister(GameObject gameObject) {
        gameObjects.remove(gameObject);
    }

    private class GameLoop extends AnimationTimer {
        double lastTime = System.nanoTime() / 1000000000.0;
        final Canvas c = graphicsContext.getCanvas();

        @Override
        public void handle(final long now) {
            nextTick(now);
        }

        public void start() {
            super.start();
        }

        public void stop() {
            super.stop();
        }

        private void nextTick(long now) {
            double nowInSeconds = now / 1000000000.0;
            double deltaTime = nowInSeconds - lastTime;

            if (input.isKeyPressed(KeyCode.ESCAPE)) {
                togglePause();
            }

            if (!paused) {

                graphicsContext.setFont(new Font(30));
                graphicsContext.setTextAlign(TextAlignment.CENTER);
                graphicsContext.setTextBaseline(VPos.CENTER);
                graphicsContext.setFill(Color.GREEN);
                graphicsContext.fillText(
                        "Text centered on your Canvas",
                        Math.round(c.getWidth()  / 2),
                        Math.round(c.getHeight() / 2)
                );
                graphicsContext.setFill(Color.BLACK);

//                graphicsContext.fillText("hallo", 50,50, 10);

                graphicsContext.clearRect(0,0, c.getWidth(),c.getHeight());
                gameObjects.sort(Comparator.comparingDouble(o -> o.position.getZ()));

                for (GameObject gameObject : gameObjects) {
                    gameObject.update(deltaTime);
                    gameObject.draw();
                }
            }

            input.onAfterUpdate();

            lastTime = nowInSeconds;
        }

    }

}
