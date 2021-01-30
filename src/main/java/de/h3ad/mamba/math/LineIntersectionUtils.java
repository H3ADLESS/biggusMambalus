package de.h3ad.mamba.math;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class LineIntersectionUtils {

    /**
     * Lines must be horizontal or vertical! Point that is shared as end and start point is not treated as intersection.
     * @params p1, p2 points of oldest line
     * @params p3, p4 points of newest line
     * @return intersection of lines or null
     */
    public static Vector3 intersectionOfHorizontalAndVerticalLines(Vector3 p1, Vector3 p2, Vector3 p3, Vector3 p4) {

        boolean line1horizontal = (Double.compare(p1.getY(), p2.getY()) == 0);
        boolean line2horizontal = (Double.compare(p3.getY(), p4.getY()) == 0);

        if (line1horizontal && line2horizontal) {
            if (p1.getY() != p3.getY()) return null;

            if (p3.getX() < p4.getX()) {
                // check direction form left to right, as we want the leftmost collision of the lines
                if (isBetween(p3.getX(), p1.getX(), p2.getX())) {
                    System.out.println(p1 + ", " + ", " + p2 + ", " + p3 + ", " + p4);
                    return p3;
                }
            } else {
                if (isBetween(p4.getX(), p1.getX(), p2.getX())) {
                    System.out.println(p1 + ", " + ", " + p2 + ", " + p3 + ", " + p4);
                    return p4;
                }
            }
        }

        if (!line1horizontal && !line2horizontal) {
            if (p1.getX() != p3.getX()) return null;

            if (p3.getY() < p4.getY()) {
                // check direction form left to right, as we want the leftmost collision of the lines
                if (isBetween(p3.getY(), p1.getY(), p2.getY())) {
                    System.out.println(p1 + ", " + ", " + p2 + ", " + p3 + ", " + p4);
                    return p3;
                }
            } else {
                if (isBetween(p4.getY(), p1.getY(), p2.getY())) {
                    System.out.println(p1 + ", " + ", " + p2 + ", " + p3 + ", " + p4);
                    return p4;
                }
            }
        }

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

    public static boolean isBetween(double center, double a, double b) {
        return (a < center && center < b || b < center && center < a);
    }

}
