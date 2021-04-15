package de.h3ad.mamba.math;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

public class LineIntersectionUtils {

    /**
     * Lines must be horizontal or vertical! Point that is shared as end and start point is not treated as intersection.
     * @params line1 oldest line
     * @params line2 newest line
     * @return intersection of lines or null
     */

    public static Vector3 intersectionOfHorizontalAndVerticalLines(final LineSegment lineSegment1, final LineSegment lineSegment2) {
        // horizontal and parallel
        if (lineSegment1.isHorizontal() && lineSegment2.isHorizontal()) {
            return intersectionOfHorizontalLines(lineSegment1, lineSegment2);
        }

        // vertical and parallel
        if (lineSegment1.isVertical() && lineSegment2.isVertical()) {
            return intersectionOfVerticalLines(lineSegment1, lineSegment2);
        }

        // perpendicular, line1 is horizontal
        if (isOnVerticalLine(lineSegment1.getV1(), lineSegment2) && isOnHorizontalLine(lineSegment2.getV1(), lineSegment1)) {
            return new Vector3(lineSegment2.getV1().getX(), lineSegment1.getV1().getY());
        }

        // perpendicular, line2 is horizontal
        if (isOnVerticalLine(lineSegment2.getV1(), lineSegment1) && isOnHorizontalLine(lineSegment1.getV1(), lineSegment2)) {
            return new Vector3(lineSegment1.getV1().getX(), lineSegment2.getV1().getY());
        }

        return null;
    }

    private static Vector3 intersectionOfHorizontalLines(final LineSegment lineSegment1, final LineSegment lineSegment2) {
        if (lineSegment1.distanceTo(lineSegment2) > 0.0) {
            return null;
        }

        if (isOnHorizontalLine(lineSegment2.getLeftPoint(), lineSegment1)) {
            return lineSegment2.getLeftPoint();
        }

        return null;
    }

    /**
     * Returns true, if x is between a and b, but x is neither a or b
     */
    private static boolean isOnHorizontalLine(final Vector3 point, final LineSegment lineSegment) {
        if (!(Vector3.compareDouble(point.getY(), lineSegment.getV1().getY()) == 0)) {
            return false;
        }
        if (point.getX() <= lineSegment.getLeftPoint().getX()) {
            return false;
        }

        if (point.getX() >= lineSegment.getRightPoint().getX()) {
            return false;
        }

        return true;
    }

    /**
     * Returns true, if x is between a and b, or x is a or b
     */
    private static boolean isOnHorizontalLineStrict(final Vector3 point, final LineSegment lineSegment) {
        if (!(Vector3.compareDouble(point.getY(), lineSegment.getV1().getY()) == 0)) {
            return false;
        }
        if (point.getX() < lineSegment.getLeftPoint().getX()) {
            return false;
        }

        if (point.getX() > lineSegment.getRightPoint().getX()) {
            return false;
        }

        return true;
    }

    /**
     * Returns true, if x is between a and b, but x is neither a or b
     */
    private static Vector3 intersectionOfVerticalLines(final LineSegment lineSegment1, final LineSegment lineSegment2) {
        if (lineSegment1.distanceTo(lineSegment2) > 0.0) {
            return null;
        }

        if (isOnVerticalLine(lineSegment2.getTopPoint(), lineSegment1)) {
            return lineSegment2.getTopPoint();
        }

        return null;
    }

    // Returns true, if x is between a and b, AND not a and b
    private static boolean isOnVerticalLine(final Vector3 point, final LineSegment lineSegment) {
        if (point.getY() <= lineSegment.getTopPoint().getY()) {
            return false;
        }

        if (point.getY() >= lineSegment.getBottomPoint().getY()) {
            return false;
        }

        return true;
    }

    private static boolean isOnVerticalLineStrict(final Vector3 point, final LineSegment lineSegment) {
        if (!(Vector3.compareDouble(point.getX(), lineSegment.getV1().getX()) == 0)) {
            return false;
        }
        if (point.getY() < lineSegment.getTopPoint().getY()) {
            return false;
        }

        if (point.getY() > lineSegment.getBottomPoint().getY()) {
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

    /**
     * Assumes that first and last point of polygon are equal.
     *
     * All lines need to be vertical or horizontal.
     */
    public static List<Pair<Vector3, LineSegment>> getIntersections(LineSegment lineSegment, List<Vector3> polygon) {
        List<Pair<Vector3, LineSegment>> intersections = new ArrayList<>();
        for (int i = 0; i < polygon.size() - 1; i++) {
            Vector3 intersection =  intersectionOfHorizontalAndVerticalLines(new LineSegment(polygon.get(i), polygon.get(i+1)), lineSegment);
            if (intersection != null) {
                intersections.add(new Pair<>(intersection, new LineSegment(polygon.get(i), polygon.get(i+1))));
            }
        }
        return intersections;
    }

    /**
     * Returns true if inside polygon or on the edge of the polygon.
     *
     * Assumes that (-10, point.getY()) is not inside the polygon.
     */
    public static boolean isInsidePolygon(Vector3 point, List<Vector3> polygon) {
        LineSegment outsideToInsideLine = new LineSegment(new Vector3(-10, point.getY()), point);
        return !((getIntersections(outsideToInsideLine, polygon).size() % 2) == 0) || isOnPolygonEdge(point, polygon);
    }

    public static boolean isOnPolygonEdge(Vector3 point, List<Vector3> polygon) {
        for (int i = 0; i < polygon.size() - 1; i++) {
            LineSegment polygonPart = new LineSegment(polygon.get(i), polygon.get(i+1));
            if (polygonPart.isHorizontal() && isOnHorizontalLineStrict(point, polygonPart)) {
                return true;
            }
            if (polygonPart.isVertical() && isOnVerticalLineStrict(point, polygonPart)) {
                return true;
            }
        }
        return false;
    }


}
