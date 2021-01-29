package de.h3ad.mamba.math;

import java.util.Objects;

public class Vector3 {

    public final static Vector3 LEFT = new Vector3(-1, 0);
    public final static Vector3 RIGHT = new Vector3(1, 0);
    public final static Vector3 UP = new Vector3(0, 1);
    public final static Vector3 DOWN = new Vector3(0, -1);

    public double x = 0;
    public double y = 0;
    public double z = 0;

    public Vector3() {

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

    @Override
    public boolean equals(final Object o) {
        if (o instanceof Vector3) {
            var other = (Vector3) o;
            return Objects.equals(x, other.x)
                    && Objects.equals(y, other.y)
                    && Objects.equals(z, other.z);
        }

        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, z);
    }
}
