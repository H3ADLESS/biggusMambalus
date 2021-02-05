package de.h3ad.mamba.math;

public class LineIntersectionUtils {

    /**
     * Lines must be horizontal or vertical! Point that is shared as end and start point is not treated as intersection.
     * @params line1 oldest line
     * @params line2 newest line
     * @return intersection of lines or null
     */

    public static Vector3 intersectionOfHorizontalAndVerticalLines(final Line line1, final Line line2) {
        // horizontal and parallel
        if (line1.isHorizontal() && line2.isHorizontal()) {
            return intersectionOfHorizontalLines(line1, line2);
        }

        // vertical and parallel
        if (line1.isVertical() && line2.isVertical()) {
            return intersectionOfVerticalLines(line1, line2);
        }

        // perpendicular, line1 is horizontal
        if (isOnVerticalLine(line1.getV1(), line2) && isOnHorizontalLine(line2.getV1(), line1)) {
            return new Vector3(line2.getV1().getX(), line1.getV1().getY());
        }

        // perpendicular, line2 is horizontal
        if (isOnVerticalLine(line2.getV1(), line1) && isOnHorizontalLine(line1.getV1(), line2)) {
            return new Vector3(line1.getV1().getX(), line2.getV1().getY());
        }

        return null;
    }

    private static Vector3 intersectionOfHorizontalLines(final Line line1, final Line line2) {
        if (line1.distanceTo(line2) > 0.0) {
            return null;
        }

        if (isOnHorizontalLine(line2.getLeftPoint(), line1)) {
            return line2.getLeftPoint();
        }

        return null;
    }

    // Returns true, if x is between a and b, AND not a and b
    private static boolean isOnHorizontalLine(final Vector3 point, final Line line) {
        if (point.getX() <= line.getLeftPoint().getX()) {
            return false;
        }

        if (point.getX() >= line.getRightPoint().getX()) {
            return false;
        }

        return true;
    }

    private static Vector3 intersectionOfVerticalLines(final Line line1, final Line line2) {
        if (line1.distanceTo(line2) > 0.0) {
            return null;
        }

        if (isOnVerticalLine(line2.getTopPoint(), line1)) {
            return line2.getTopPoint();
        }

        return null;
    }

    // Returns true, if x is between a and b, AND not a and b
    private static boolean isOnVerticalLine(final Vector3 point, final Line line) {
        if (point.getY() <= line.getTopPoint().getY()) {
            return false;
        }

        if (point.getY() >= line.getBottomPoint().getY()) {
            return false;
        }

        return true;
    }

    public static boolean isBetween(double center, double start, double end) {
        if (end < start) {
            return end < center && center < start;
        }

        return start < center && center < end;
    }
}
