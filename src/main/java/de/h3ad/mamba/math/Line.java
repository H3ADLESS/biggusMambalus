package de.h3ad.mamba.math;

public class Line {

    private final Vector3 v1;
    private final Vector3 v2;

    public Line(final Vector3 v1, final Vector3 v2) {
        this.v1 = v1;
        this.v2 = v2;
    }

    public Vector3 getV1() {
        return v1;
    }

    public Vector3 getV2() {
        return v2;
    }

    public boolean isHorizontal() {
        return (Double.compare(v1.getY(), v2.getY()) == 0);
    }
}
