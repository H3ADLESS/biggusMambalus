package de.h3ad.mamba.math;

public class Vector3 {

    public final static Vector3 LEFT = new Vector3(-1, 0);
    public final static Vector3 RIGHT = new Vector3(1, 0);
    public final static Vector3 UP = new Vector3(0, 1);
    public final static Vector3 DOWN = new Vector3(0, -1);

    public Vector3() {

    }

    public Vector3(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Vector3(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public double x = 0;
    public double y = 0;
    public double z = 0;

}
