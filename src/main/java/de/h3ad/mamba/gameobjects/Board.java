package de.h3ad.mamba.gameobjects;

import de.h3ad.mamba.Game;
import de.h3ad.mamba.GameObject;

public class Board extends GameObject {

    public Board() {
        super();
    }

    @Override
    public void update(double deltaTime) {

    }

    @Override
    public void draw() {
        getGraphics().strokeLine(5,5,795, 5);
        getGraphics().strokeLine(795, 5,795, 595);
        getGraphics().strokeLine(795, 595,5, 595);
        getGraphics().strokeLine(5, 595,5, 5);
    }
}
