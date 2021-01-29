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
        this.z = 0;
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
        return y;
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
            return Objects.equals(x, other.getX())
                    && Objects.equals(y, other.getY())
                    && Objects.equals(z, other.getZ());
        }

        return false;
    }



    @Override
    public int hashCode() {
        return Objects.hash(x, y, z);
    }
}
