package de.h3ad.mamba.gameobjects;

import de.h3ad.mamba.Direction;
import de.h3ad.mamba.GameObject;
import de.h3ad.mamba.math.Vector3;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.util.concurrent.ThreadLocalRandom;

import static de.h3ad.mamba.gameobjects.Board.*;

public class Snake extends GameObject {

    private final int MAX_SNAKE_LENGTH = 200;
    private final double SNAKE_WIDTH = 5;
    private final double VELOCITY = 100;

    private Direction direction = Direction.RIGHT;
    private double distanceSinceLastDirectionChange = Double.MAX_VALUE;
    private final SnakeList body;



    public Snake(Vector3 startPosition) {
        body = new SnakeList(startPosition, MAX_SNAKE_LENGTH);
        this.position = startPosition;
    }


    @Override
    public void update(double deltaTime) {
        final var distance = VELOCITY * deltaTime;
        Direction newDirection = direction;
        if (distanceSinceLastDirectionChange > MAX_SNAKE_LENGTH / 10.0) {
            if (ThreadLocalRandom.current().nextDouble(MAX_SNAKE_LENGTH) < distanceSinceLastDirectionChange) {
                newDirection = changeDirection();
                distanceSinceLastDirectionChange = 0.0;
            }
        }
        Vector3 movement = direction.getVector().multiply(distance);
        if (checkIfWallCollision(this.position.add(movement))) {
            newDirection = changeDirection();
            distanceSinceLastDirectionChange = 0.0;
            movement = direction.getVector().multiply(distance);
        }
        direction = newDirection;
        distanceSinceLastDirectionChange += distance;
        move(direction, distance);
        body.addNewPosition(position);
    }

    private boolean checkIfWallCollision(Vector3 position) {
        return position.getX() > BOARD_WIDTH - BOARD_MARGIN
                || position.getX() < BOARD_MARGIN
                || position.getY() > BOARD_HEIGHT - BOARD_MARGIN
                || position.getY() < BOARD_MARGIN;
    }

    private Direction changeDirection() {
        Direction newDirection = direction;
        if (direction.equals(Direction.DOWN) || direction.equals(Direction.UP)) {
            if (BOARD_WIDTH/20 > position.getX()) {
                return Direction.RIGHT;
            }
            if (BOARD_WIDTH - BOARD_WIDTH/20 < position.getX()) {
                return Direction.LEFT;
            }
            switch (ThreadLocalRandom.current().nextInt(0, 2)) {
                case 0 -> newDirection = Direction.RIGHT;
                case 1 -> newDirection = Direction.LEFT;
                default -> throw new RuntimeException("Can't happen");
            }
        } else if (direction.equals(Direction.RIGHT) || direction.equals(Direction.LEFT)) {
            if (BOARD_HEIGHT/20 > position.getY()) {
                return Direction.DOWN;
            }
            if (BOARD_HEIGHT - BOARD_HEIGHT/20 < position.getY()) {
                return Direction.UP;
            }
            newDirection = switch (ThreadLocalRandom.current().nextInt(0, 2)) {
                case 0 -> Direction.UP;
                case 1 -> Direction.DOWN;
                default -> throw new RuntimeException("Can't happen");
            };
        } else {
            throw new RuntimeException("Not a valid direction");
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
            this.position = new Vector3(this.position.getX(), BOARD_HEIGHT-BOARD_MARGIN);
        }

        if (this.position.getY() < BOARD_MARGIN) {
            this.position = new Vector3(this.position.getX(), BOARD_MARGIN);
        }
    }

    @Override
    public void draw() {
        Vector3 lastV = null;
        for (Vector3 v : body.getAllNodes()) {
            if (lastV == null) {
                lastV = v;
                continue;
            }
            // remember old setup
            double oldLineWidth = getGraphics().getLineWidth();
            Paint oldStroke = getGraphics().getStroke();

            // change to snake setup and draw snake
            getGraphics().setStroke(Color.RED);
            getGraphics().setLineWidth(SNAKE_WIDTH);
            getGraphics().strokePolyline(new double[]{lastV.getX(), v.getX()}, new double[]{lastV.getY(), v.getY()}, 2);

            // reset to old setup
            getGraphics().setLineWidth(oldLineWidth);
            getGraphics().setStroke(oldStroke);
            lastV = v;
        }
    }
}
