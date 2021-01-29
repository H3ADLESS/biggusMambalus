package de.h3ad.mamba.gameobjects;

import de.h3ad.mamba.Direction;
import de.h3ad.mamba.GameObject;
import de.h3ad.mamba.Input;
import de.h3ad.mamba.math.Vector3;
import javafx.scene.input.KeyCode;

import static de.h3ad.mamba.gameobjects.Board.BOARD_HEIGHT;
import static de.h3ad.mamba.gameobjects.Board.BOARD_LEFT;
import static de.h3ad.mamba.gameobjects.Board.BOARD_TOP;
import static de.h3ad.mamba.gameobjects.Board.BOARD_WIDTH;

public class Spider extends GameObject {

    private static final double SPIDER_WIDTH = 5;

    private double velocity = 100;
    private Direction direction = Direction.RIGHT;

    public Spider() {
        super();
    }

    public void update(double deltaTime) {
        final var distance = velocity * deltaTime;

        Input input = game.getInput();
        if (input.isKeyPressed(KeyCode.LEFT)) {
            direction = Direction.LEFT;
        }
        if (input.isKeyPressed(KeyCode.RIGHT)) {
            direction = Direction.RIGHT;
        }
        if (input.isKeyPressed(KeyCode.UP)) {
            direction = Direction.UP;
        }
        if (input.isKeyPressed(KeyCode.DOWN)) {
            direction = Direction.DOWN;
        }

        move(direction, distance);
    }

    public void draw() {
        getGraphics().fillOval(position.getX(), position.getY(), SPIDER_WIDTH, SPIDER_WIDTH);
    }

    private void move(final Direction direction, final double distance) {
        final Vector3 movement = direction.getVector().multiply(distance);
        this.position = this.position.add(movement);

        if (this.position.getX() > BOARD_WIDTH) {
            this.position = new Vector3(BOARD_LEFT, this.position.getY());
        }

        if (this.position.getX() < BOARD_LEFT) {
            this.position = new Vector3(BOARD_WIDTH, this.position.getY());
        }

        if (this.position.getY() > BOARD_HEIGHT) {
            this.position = new Vector3(this.position.getX(), BOARD_TOP);
        }

        if (this.position.getY() < BOARD_TOP) {
            this.position = new Vector3(this.position.getX(), BOARD_HEIGHT);
        }
    }
}
