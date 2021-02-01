package de.h3ad.mamba.math;

import java.util.Objects;

public class Vector3 {

    private final double x;
    private final double y;
    private final double z;

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
        return Double.compare(getX(), 0.0) == 0 && Double.compare(getY(), 0.0) == 0 && Double.compare(getZ(), 0.0) == 0;
    }

    @Override
    public boolean equals(final Object o) {
        if (o instanceof Vector3) {
            var other = (Vector3) o;
            return Double.compare(x, other.getX()) == 0
                    && Double.compare(y, other.getY()) == 0
                    && Double.compare(z, other.getZ()) == 0;
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
        if (Objects.equals(this.getX(), start.getX()) && Objects.equals(this.getX(), end.getX())) {
            return true;
        } else if (Objects.equals(this.getY(), start.getY()) && Objects.equals(this.getY(), end.getY())) {
            return true;
        }
        return false;
    }


    public double squaredDistance(Vector3 position) {
        return Math.pow(position.getX() - this.getX(), 2) + Math.pow(position.getY() - this.getY(), 2) + Math.pow(position.getZ() - this.getZ(), 2);
    }

    public double distance(Vector3 position) {
        return Math.sqrt(this.squaredDistance(position));
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, z);
    }

    public String toString() {
        return "(" + this.x + ", " + this.y + ")";
    }
}
