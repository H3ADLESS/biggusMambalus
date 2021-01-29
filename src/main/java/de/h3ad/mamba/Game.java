package de.h3ad.mamba;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

public class Game {

    private static Game GAME;

    private final Input input;
    private final GameLoop gameLoop;
    private final GraphicsContext graphicsContext;
    private final List<GameObject> gameObjects = new ArrayList<>();

    boolean running = false;

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

    public void pause() {
        this.running = false;
        gameLoop.stop();
    }

    public GraphicsContext getGraphicsContext() {
        return graphicsContext;
    }

    public void register(GameObject gameObject) {
        gameObjects.add(gameObject);
    }

    public void unregister(GameObject gameObject) {
        gameObjects.remove(gameObject);
    }

    private class GameLoop extends AnimationTimer {
        double lastTime = System.nanoTime() / 1000000000.0;

        @Override
        public void handle(final long now) {
            final Canvas c = graphicsContext.getCanvas();
            graphicsContext.clearRect(0,0, c.getWidth(),c.getHeight());
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

            gameObjects.sort(Comparator.comparingDouble(o -> o.position.getZ()));

            for (GameObject gameObject : gameObjects) {
                gameObject.update(deltaTime);
                gameObject.draw();
            }

            lastTime = nowInSeconds;
        }

    }

}
