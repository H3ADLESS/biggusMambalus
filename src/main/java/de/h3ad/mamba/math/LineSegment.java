package de.h3ad.mamba.math;

import javax.sound.sampled.Line;

public class LineSegment {

    private final Vector3 v1;
    private final Vector3 v2;

    public LineSegment(final Vector3 v1, final Vector3 v2) {
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

    public double distanceTo(final LineSegment other) {
        if (isHorizontal() && other.isHorizontal()) {
            return Math.abs(this.v1.getY() - other.getV1().getY());
        }

        if (isVertical() && other.isVertical()) {
            return Math.abs(this.v1.getX() - other.getV1().getX());
        }

        return 0.0;
    }

    public boolean containsLineSegment(LineSegment inner) {
        if (this.isHorizontal() && inner.isHorizontal() && Double.compare(inner.v1.getY(), this.v1.getY()) == 0) {
            double left = Math.min(this.v1.getX(), this.v2.getX());
            double right = Math.max(this.v1.getX(), this.v2.getX());
            return left <= inner.v1.getX() && left <= inner.v2.getX() && right >= inner.v1.getX() && right >= inner.v2.getX();
        }
        if (this.isVertical() && inner.isVertical() && Double.compare(inner.v1.getX(), this.v1.getX()) == 0) {
            double top = Math.min(this.v1.getY(), this.v2.getY());
            double bot = Math.max(this.v1.getY(), this.v2.getY());
            return top <= inner.v1.getY() && top <= inner.v2.getY() && bot >= inner.v1.getY() && bot >= inner.v2.getY();
        }
        return false;
    }
}
