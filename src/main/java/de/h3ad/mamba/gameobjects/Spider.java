package de.h3ad.mamba.gameobjects;

import de.h3ad.mamba.Direction;
import de.h3ad.mamba.GameObject;
import de.h3ad.mamba.Input;
import de.h3ad.mamba.math.LineIntersectionUtils;
import de.h3ad.mamba.math.LineSegment;
import de.h3ad.mamba.math.Vector3;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.util.Pair;

import java.util.List;

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
            if (!direction.equals(Direction.RIGHT)) {
                direction = Direction.LEFT;
            }
        }

        if (input.isKeyDown(KeyCode.RIGHT)) {
            if (!direction.equals(Direction.LEFT)) {
                direction = Direction.RIGHT;
            }
        }

        if (input.isKeyDown(KeyCode.UP)) {
            if (!direction.equals(Direction.DOWN)) {
                direction = Direction.UP;
            }
        }

        if (input.isKeyDown(KeyCode.DOWN)) {
            if (!direction.equals(Direction.UP)) {
                direction = Direction.DOWN;
            }
        }
    }

    private void move(final Direction direction, final double distance) {
        SafeZone safeZone = SafeZone.getInstance();
        final Vector3 movement = direction.getVector().multiply(distance);
        LineSegment lineSegment = new LineSegment(new Vector3(this.position.getX(), this.position.getY()), this.position.add(movement));

        // check if inside polygon
        if (LineIntersectionUtils.isInsidePolygon(this.position.add(movement), safeZone.getPolygon())) {
            System.out.println("move inside");
            this.position = this.position.add(movement);
        } else {
            System.out.println("Move not inside polygon");
            List<Pair<Vector3, LineSegment>> intersections = LineIntersectionUtils.getIntersections(lineSegment, safeZone.getPolygon());
            if (!intersections.isEmpty() && !intersections.get(0).getValue().containsLineSegment(lineSegment)) {
                Pair<Vector3, LineSegment> intersection = intersections.get(0);
                this.direction = Direction.NONE;
                this.position = intersection.getKey();

                this.spiderPath = new SpiderPath();
                this.spiderPath.add(position);
            }
        }

        /*
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
*/
    }

    private void onBorderWalk() {
        this.spiderPath = new SpiderPath();
        this.spiderPath.add(position);
    }

}
