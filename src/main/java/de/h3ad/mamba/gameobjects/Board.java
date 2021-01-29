package de.h3ad.mamba.gameobjects;

import de.h3ad.mamba.GameObject;

public class Board extends GameObject {

    public static final double BOARD_WIDTH = 790;
    public static final double BOARD_HEIGHT = 590;
    public static final double BOARD_LEFT = 5;
    public static final double BOARD_TOP = 5;

    public Board() {
        super();
    }

    @Override
    public void update(double deltaTime) {

    }

    @Override
    public void draw() {
        getGraphics().strokeRect(BOARD_LEFT, BOARD_TOP, BOARD_WIDTH, BOARD_HEIGHT);
    }
}
