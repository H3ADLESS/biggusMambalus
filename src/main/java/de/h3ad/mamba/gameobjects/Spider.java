package de.h3ad.mamba.gameobjects;

import de.h3ad.mamba.Direction;
import de.h3ad.mamba.GameObject;
import de.h3ad.mamba.Input;
import de.h3ad.mamba.math.Vector3;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;

import static de.h3ad.mamba.gameobjects.Board.BOARD_HEIGHT;
import static de.h3ad.mamba.gameobjects.Board.BOARD_LEFT;
import static de.h3ad.mamba.gameobjects.Board.BOARD_TOP;
import static de.h3ad.mamba.gameobjects.Board.BOARD_WIDTH;

public class Spider extends GameObject {

    private static final double SPIDER_WIDTH = 5;

    private Direction direction = Direction.RIGHT;
    private double velocity = 100;
    private double spiderWidth = 5;
    private double spiderHeight = 5;

    SpiderPath spiderPath = new SpiderPath();

    public Spider() {
        super();
    }

    public void update(double deltaTime) {
        final var distance = velocity * deltaTime;

        Direction lastDirection = direction;

        handleInput();
        move(direction, distance);

        if (lastDirection != direction) {
            spiderPath.add(position);
            System.out.println(spiderPath.toString());
        } else {
            spiderPath.updateLast(position);
        }
    }

    public void draw() {
        getGraphics().fillOval(position.getX() - (spiderWidth/2.0), position.getY() - (spiderHeight/2.0), SPIDER_WIDTH, SPIDER_WIDTH);
        SpiderPath.SpiderPathArray path = spiderPath.getSpiderPathArray();
        if (path.size() > 0) {
            getGraphics().setStroke(Color.GRAY);
            getGraphics().strokePolyline(path.getXPositions(), path.getYPositions(), path.size());
            getGraphics().setStroke(Color.BLACK);
        }

    }

    private void handleInput() {
        Input input = game.getInput();

        if (input.isKeyDown(KeyCode.LEFT)) {
            direction = Direction.LEFT;
        }

        if (input.isKeyDown(KeyCode.RIGHT)) {
            direction = Direction.RIGHT;
        }

        if (input.isKeyDown(KeyCode.UP)) {
            direction = Direction.UP;
        }

        if (input.isKeyDown(KeyCode.DOWN)) {
            direction = Direction.DOWN;

        }
    }

    private void move(final Direction direction, final double distance) {
        final Vector3 movement = direction.getVector().multiply(distance);
        this.position = this.position.add(movement);

        if (this.position.getX() > BOARD_WIDTH + 1 && this.direction == Direction.RIGHT) {
            this.position = new Vector3(BOARD_LEFT + BOARD_WIDTH, this.position.getY());
            this.direction = Direction.NONE;
//            onBorderWalk();
        }

        if (this.position.getX() < BOARD_LEFT && this.direction == Direction.LEFT) {
            this.position = new Vector3(BOARD_LEFT, this.position.getY());
            this.direction = Direction.NONE;
//            onBorderWalk();
        }

        if (this.position.getY() > BOARD_HEIGHT && this.direction == Direction.DOWN) {
            this.position = new Vector3(this.position.getX(), BOARD_TOP + BOARD_HEIGHT);
            this.direction = Direction.NONE;
//            onBorderWalk();
        }

        if (this.position.getY() < BOARD_TOP && this.direction == Direction.UP) {
            this.position = new Vector3(this.position.getX(), BOARD_TOP);
            this.direction = Direction.NONE;
//            onBorderWalk();
        }

        if (this.position.getY() <= BOARD_TOP || this.position.getY() >= (BOARD_TOP + BOARD_HEIGHT) || this.position.getX() <= BOARD_LEFT || this.position.getX() >= (BOARD_LEFT + BOARD_WIDTH)) {
            onBorderWalk();
        }

    }

    private void onBorderWalk() {
        this.spiderPath = new SpiderPath();
        this.spiderPath.add(position);
    }

}
