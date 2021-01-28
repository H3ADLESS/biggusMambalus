package de.h3ad.mamba.math;

public class Vector2 {

    public final static Vector2 LEFT = new Vector2(-1, 0);
    public final static Vector2 RIGHT = new Vector2(1, 0);
    public final static Vector2 UP = new Vector2(0, 1);
    public final static Vector2 DOWN = new Vector2(0, -1);

    public double x = 0;
    public double y = 0;

    public Vector2() {

    }

    public Vector2(double x, double y) {
        this.x = x;
        this.y = y;
    }

}
