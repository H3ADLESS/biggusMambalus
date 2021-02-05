package de.h3ad.mamba.math;

public class LineIntersectionUtils {

    /**
     * Lines must be horizontal or vertical! Point that is shared as end and start point is not treated as intersection.
     * @params p1, p2 points of oldest line
     * @params p3, p4 points of newest line
     * @return intersection of lines or null
     */

    public static Vector3 intersectionOfHorizontalAndVerticalLines(final Line line1, final Line line2) {

        final Vector3 p1 = line1.getV1();
        final Vector3 p2 = line1.getV2();
        final Vector3 p3 = line2.getV1();
        final Vector3 p4 = line2.getV2();

        boolean line1horizontal = line1.isHorizontal();
        boolean line2horizontal = line2.isHorizontal();

        // horizontal and parallel
        if (line1horizontal && line2horizontal) {
            return intersectionOfHorizontalLines(line1, line2);
        }

        // vertical and parallel
        if (!line1horizontal && !line2horizontal) {
            return intersectionOfVerticalLines(line1, line2);
        }

        // perpendicular
        if (line1horizontal != line2horizontal) {
            if (line1horizontal) {
                // line 1 is horizontal (y stays same), line 2 is vertical (x stays same)
                if (isBetween(p1.getY(), p3.getY(), p4.getY())) {
                    if (isBetween(p3.getX(), p1.getX(), p2.getX())) {
                        System.out.println(p1 + ", " + ", " + p2 + ", " + p3 + ", " + p4);
                        return new Vector3(p3.getX(), p1.getY());
                    }
                }
            } else {
                // line 2 is horizontal (y stays same), line 1 is vertical (x stays same)
                if (isBetween(p3.getY(), p1.getY(), p2.getY())) {
                    if (isBetween(p1.getX(), p3.getX(), p4.getX())) {
                        System.out.println(p1 + ", " + ", " + p2 + ", " + p3 + ", " + p4);
                        return new Vector3(p1.getX(), p3.getY());
                    }
                }
            }
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
