package de.h3ad.mamba;

import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Game {

    private static Game GAME;
    boolean running = false;
    List<GameObject> gameObjects = new ArrayList<>();
    GraphicsContext graphicsContext;
    Scene scene;
    Input input;
    double lastTime = System.nanoTime() / 1000000000.0;

    private Game(Scene scene, GraphicsContext graphicsContext) {
        this.scene = scene;
        this.graphicsContext = graphicsContext;
        this.input = new Input(this, scene);
    }

    public void start() {
        this.running = true;
        System.out.println("Called");
    }

    public void pause() {
        this.running = false;
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

    public void loop(long now) {
        double nowInSeconds = now / 1000000000.0;
        double deltaTime = nowInSeconds - lastTime;

        gameObjects.sort(Comparator.comparingDouble(o -> o.position.z));

        for (GameObject gameObject : gameObjects) {
            gameObject.update(deltaTime);
            gameObject.draw();
        }

        lastTime = nowInSeconds;
    }

    public void register(GameObject go) {
        gameObjects.add(go);
    }

    public void unregister(GameObject go) {
        gameObjects.add(go);
    }

}
