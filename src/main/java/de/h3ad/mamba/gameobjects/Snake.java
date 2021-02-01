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
    private int lastDirectionChange = Integer.MAX_VALUE;
    private final SnakeList body;



    public Snake(Vector3 startPosition) {
        body = new SnakeList(startPosition, MAX_SNAKE_LENGTH);
        this.position = startPosition;
    }


    @Override
    public void update(double deltaTime) {
        final var distance = VELOCITY * deltaTime;
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
        body.addNewPosition(position);
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
