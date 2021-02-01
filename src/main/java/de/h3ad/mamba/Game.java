package de.h3ad.mamba;

import de.h3ad.mamba.gameobjects.text.FpsCounter;
import de.h3ad.mamba.math.Vector3;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Game {

    private static Game GAME;

    private final Input input;
    private final GameLoop gameLoop;
    private final GraphicsContext graphicsContext;
    private final List<GameObject> gameObjects = new ArrayList<>();

    boolean running = false;
    boolean paused = false;

    FpsCounter fpsCounter;

    private Game(Scene scene, GraphicsContext graphicsContext) {
        this.graphicsContext = graphicsContext;
        this.input = new Input(this, scene);
        gameLoop = new GameLoop();
    }

    public static Game init(Scene scene, GraphicsContext graphicsContext) {
        if (GAME == null) {
            GAME = new Game(scene, graphicsContext);
            GAME.setup();
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

    /**
     * Is called before first frame and after graphics initalization
     */
    public void setup() {
        fpsCounter = new FpsCounter(new Vector3(10,20));
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
