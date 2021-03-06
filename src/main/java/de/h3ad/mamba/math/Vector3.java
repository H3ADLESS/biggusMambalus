package de.h3ad.mamba.math;

import java.util.Objects;

public class Vector3 {

    private final double x;
    private final double y;
    private final double z;

    private final double DELTA = 0.000001; // margin used to check doubles for equality

    public Vector3() {
        x = 0;
        y = 0;
        z = 0;
    }

    public Vector3(double x, double y) {
        this.x = x;
        this.y = y;
        this.z = 0.0;
    }

    public Vector3(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    public Vector3 add(final Vector3 other) {
        return new Vector3(
                getX() + other.x,
                getY() + other.y,
                getZ() + other.z);
    }

    public Vector3 multiply(final double value) {
        return new Vector3(
                getX() * value,
                getY() * value,
                getZ() * value);
    }

    public boolean isZero() {
        return compareDouble(getX(), 0.0) == 0 && compareDouble(getY(), 0.0) == 0 && compareDouble(getZ(), 0.0) == 0;
    }

    @Override
    public boolean equals(final Object o) {
        if (o instanceof Vector3) {
            var other = (Vector3) o;
            return compareDouble(x, other.getX()) == 0
                    && compareDouble(y, other.getY()) == 0
                    && compareDouble(z, other.getZ()) == 0;
        }

        return false;
    }

    /**
     * Checks if this Vector3 is on the line defined by start and end (ignoring z values). Returns true even if it is
     * not between the reference vectors.
     *
     * @param start reference start Vector
     * @param end reference end Vector
     */
    public boolean isOnLine(Vector3 start, Vector3 end) {
        if (compareDouble(this.getX(), start.getX()) == 0 && compareDouble(this.getX(), end.getX()) == 0) {
            return true;
        } else if (compareDouble(this.getY(), start.getY()) == 0 && compareDouble(this.getY(), end.getY()) == 0) {
            return true;
        }
        return false;
    }

    /**
     * Checks if this Vector3 is on the line defined by start and end (ignoring z values). Returns true even if it is
     * not between the reference vectors.
     *
     * @param start reference start Vector
     * @param end reference end Vector
     */
    public boolean isOnLineSegment(Vector3 start, Vector3 end) {
        if (compareDouble(this.getX(), start.getX()) == 0 && compareDouble(this.getX(), end.getX()) == 0) {
            return start.getY() <= this.getY() && this.getY() <= end.getY();
        } else if (compareDouble(this.getY(), start.getY()) == 0 && compareDouble(this.getY(), end.getY()) == 0) {
            return start.getX() <= this.getX() && this.getX() <= end.getX();
        }
        return false;
    }


    public double squaredDistance(Vector3 position) {
        return Math.pow(position.getX() - this.getX(), 2) + Math.pow(position.getY() - this.getY(), 2) + Math.pow(position.getZ() - this.getZ(), 2);
    }

    public double distance(Vector3 position) {
        return Math.sqrt(this.squaredDistance(position));
    }

    private int compareDouble(double a, double b) {
        if (Math.abs(a - b) < DELTA) {
            return 0;
        }
        if (a < b) {
            return -1;
        }
        return 1;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, z);
    }

    public String toString() {
        return "(" + this.x + ", " + this.y + ")";
    }

}
