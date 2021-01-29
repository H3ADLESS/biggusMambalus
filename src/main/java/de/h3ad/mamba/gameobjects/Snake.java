package de.h3ad.mamba.gameobjects;

import de.h3ad.mamba.Direction;
import de.h3ad.mamba.GameObject;
import de.h3ad.mamba.math.Vector3;
import org.apache.commons.collections4.queue.CircularFifoQueue;

import java.util.concurrent.ThreadLocalRandom;

import static de.h3ad.mamba.gameobjects.Board.*;

public class Snake extends GameObject {

    private final double SNAKE_LINK_WIDTH = 5;
    private final double SNAKE_LINK_HEIGHT = 5;

    private double velocity = 100;
    private Direction direction = Direction.RIGHT;

    private int lastDirectionChange = Integer.MAX_VALUE;


    CircularFifoQueue<Vector3> body;

    private final int MAX_SNAKE_LENGTH = 200;

    public Snake(Vector3 startPosition) {
        body = new CircularFifoQueue<>(MAX_SNAKE_LENGTH);
        this.position = startPosition;
        addBodyLink(startPosition);
    }

    private void addBodyLink(Vector3 position) {
        this.body.add(position);
    }

    @Override
    public void update(double deltaTime) {
        final var distance = velocity * deltaTime;
        if (lastDirectionChange > 20) {
            Direction newDirection = changeDirection();
            // only change direction if it isn't the opposite of last direction
            if (!newDirection.getVector().add(direction.getVector()).isZero()) {
                direction = newDirection;
                lastDirectionChange = 0;
            }
        } else {
            lastDirectionChange++;
        }
        move(direction, distance);
        addBodyLink(position);
    }

    private Direction changeDirection() {
        Direction newDirection;
        switch (ThreadLocalRandom.current().nextInt(0,4)) {
            case 0:
                newDirection = Direction.RIGHT;
                break;
            case 1:
                newDirection =  Direction.LEFT;
                break;
            case 2:
                newDirection = Direction.UP;
                break;
            case 3:
                newDirection = Direction.DOWN;
                break;
            default:
                throw new RuntimeException("jajaja");
        }
        return newDirection;
    }

    private void move(final Direction direction, final double distance) {
        final Vector3 movement = direction.getVector().multiply(distance);
        this.position = this.position.add(movement);

        if (this.position.getX() > BOARD_WIDTH-BOARD_MARGIN) {
            this.position = new Vector3(BOARD_WIDTH-BOARD_MARGIN, this.position.getY());
        }

        if (this.position.getX() < BOARD_MARGIN) {
            this.position = new Vector3(BOARD_MARGIN, this.position.getY());
        }

        if (this.position.getY() > BOARD_HEIGHT-BOARD_MARGIN) {
            this.position = new Vector3(this.position.getX(), BOARD_MARGIN);
        }

        if (this.position.getY() < BOARD_MARGIN) {
            this.position = new Vector3(this.position.getX(), BOARD_MARGIN);
        }
    }

    @Override
    public void draw() {
        body.stream().forEach(v -> {
            getGraphics().fillRect(v.getX(), v.getY(), SNAKE_LINK_WIDTH, SNAKE_LINK_HEIGHT);
        });
    }
}
