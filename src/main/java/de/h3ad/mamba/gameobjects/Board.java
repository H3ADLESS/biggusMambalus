package de.h3ad.mamba.gameobjects;

import de.h3ad.mamba.GameObject;

public class Board extends GameObject {

    private static final double BOARD_WIDTH = 790;
    private static final double BOARD_HEIGHT = 590;
    private static final double LEFT = 5;
    private static final double TOP = 5;

    public Board() {
        super();
    }

    @Override
    public void update(double deltaTime) {

    }

    @Override
    public void draw() {
        getGraphics().strokeRect(LEFT, TOP, BOARD_WIDTH, BOARD_HEIGHT);
    }
}
