package de.h3ad.mamba.math;

import java.util.Comparator;
import java.util.List;
import java.util.Vector;
import java.util.stream.Collectors;

public class VectorUtils {

    Vector3 intersectionOfOrthogonalLines(Vector3 p1, Vector3 p2, Vector3 p3, Vector3 p4) {

        boolean line1horizontal = (Double.compare(p1.getX(), p2.getX()) == 0);
        boolean line2horizontal = (Double.compare(p3.getX(), p4.getX()) == 0);

        if (line1horizontal && line2horizontal) {
            List<Vector3> l = List.of(p1, p2, p3, p4);
            l.sort(Comparator.comparing(Vector3::getX));
            Vector3 a = l.get(0);
            Vector3 b = l.get(0);
            if ((a == p1 && b == p2) || a == p2 && b == p1) return p4;
        }

        if (!line1horizontal && !line2horizontal) {
            List<Vector3> l = List.of(p1, p2, p3, p4);
            l.sort(Comparator.comparing(Vector3::getY));
            Vector3 a = l.get(0);
            Vector3 b = l.get(0);
            if ((a == p1 && b == p2) || a == p2 && b == p1) return p4;
        }

        if (line1horizontal != line2horizontal) {
            //TODO!
        }

        // TODO: Test

        return null;
    }

}
