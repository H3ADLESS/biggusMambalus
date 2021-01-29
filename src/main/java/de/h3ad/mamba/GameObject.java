package de.h3ad.mamba;

import de.h3ad.mamba.gameobjects.Board;
import de.h3ad.mamba.math.Vector3;
import javafx.scene.canvas.GraphicsContext;

public abstract class GameObject {

    protected Game game;
    protected Vector3 position = new Vector3(Board.BOARD_LEFT, Board.BOARD_TOP);

    public GameObject() {
        this.game = Game.getInstance();
        game.register(this);
    }

    abstract public void update(double deltaTime);

    abstract public void draw();

    public void destroy() {
        game.unregister(this);
    }

    public GraphicsContext getGraphics() {
        return game.getGraphicsContext();
    }

}
