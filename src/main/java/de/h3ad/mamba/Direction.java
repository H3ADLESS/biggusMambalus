package de.h3ad.mamba;

import de.h3ad.mamba.math.Vector3;

public enum Direction {

    LEFT(new Vector3(-1, 0)),
    RIGHT(new Vector3(1, 0)),
    TOP(new Vector3(0, 1)),
    DOWN(new Vector3(0, -1));

    private final Vector3 vector;

    private Direction(final Vector3 vector) {
        this.vector = vector;
    }

    public Vector3 getVector() {
        return vector;
    }
}
