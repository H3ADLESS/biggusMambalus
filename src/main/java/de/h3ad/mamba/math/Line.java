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

    public Vector3 getLeftPoint() {
        if (v1.getX() <= v2.getX()) {
            return v1;
        }
        return v2;
    }

    public Vector3 getRightPoint() {
        if (v1.getX() >= v2.getX()) {
            return v1;
        }
        return v2;
    }

    public Vector3 getTopPoint() {
        if (v1.getY() <= v2.getY()) {
            return v1;
        }
        return v2;
    }

    public Vector3 getBottomPoint() {
        if (v1.getY() >= v2.getY()) {
            return v1;
        }
        return v2;
    }

    public boolean isHorizontal() {
        return (Double.compare(v1.getY(), v2.getY()) == 0);
    }

    public boolean isVertical() {
        return (Double.compare(v1.getX(), v2.getX()) == 0);
    }

    public double distanceTo(final Line other) {
        if (isHorizontal() && other.isHorizontal()) {
            return Math.abs(this.v1.getY() - other.getV1().getY());
        }

        if (isVertical() && other.isVertical()) {
            return Math.abs(this.v1.getX() - other.getV1().getX());
        }

        return 0.0;
    }
}
