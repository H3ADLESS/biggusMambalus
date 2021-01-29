package de.h3ad.mamba.gameobjects;

import de.h3ad.mamba.GameObject;
import de.h3ad.mamba.math.Vector3;
import static de.h3ad.mamba.gameobjects.Board.BOARD_HEIGHT;
import static de.h3ad.mamba.gameobjects.Board.BOARD_WIDTH;

public class Spider extends GameObject {

    private static final double SPIDER_WIDTH = 5;

    private double velocity = 100;
    private Vector3 direction = Vector3.RIGHT;

    public Spider() {
        super();
    }

    public void update(double deltaTime) {
        final var distance = velocity * deltaTime;

        if (direction.equals(Vector3.LEFT)) {
            moveLeft(distance);
        }

        if (direction.equals(Vector3.RIGHT)) {
            moveRight(distance);
        }

        if (direction.equals(Vector3.UP)) {
            moveUp(distance);
        }

        if (direction.equals(Vector3.DOWN)) {
            moveDown(distance);
        }
    }

    public void draw() {
        getGraphics().fillOval(position.x, position.y, SPIDER_WIDTH, SPIDER_WIDTH);
    }

    private void moveLeft(final double distance) {
        this.position.x -= distance;
        if (this.position.x < 0) {
            this.position.x = BOARD_WIDTH;
        }
    }

    private void moveRight(final double distance) {
        this.position.x += distance;
        if (this.position.x > BOARD_WIDTH) {
            this.position.x = 0;
        }
    }

    private void moveUp(final double distance) {
        this.position.y += distance;
        if (this.position.y > BOARD_HEIGHT) {
            this.position.y = 0;
        }
    }

    private void moveDown(final double distance) {
        this.position.y -= distance;
        if (this.position.y < 0) {
            this.position.y = BOARD_HEIGHT;
        }
    }
}
