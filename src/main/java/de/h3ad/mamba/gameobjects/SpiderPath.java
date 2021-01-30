package de.h3ad.mamba.gameobjects;

import de.h3ad.mamba.math.LineIntersectionUtils;
import de.h3ad.mamba.math.Vector3;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class SpiderPath {

    private List<Vector3> path = new ArrayList<>();

    public void add(Vector3 position) {
        this.path.add(position);
        handleCollisions(position);
    }

    public void updateLast(Vector3 position) {
        if (this.path.size() > 0) {
            path.set(path.size() - 1, position);
        }

        handleCollisions(position);
    }

    private void handleCollisions(Vector3 currentPosition) {
        // without 5 waypoints (start included) no intersection of own path possible
        if (path.size() > 5) {

            Vector3 newestLineP1 = path.get(path.size() - 2);
            Vector3 newestLineP2 = path.get(path.size() - 1);


            Vector3 last = path.get(0);

            for (int i = 1; i < path.size(); i++) {
                Vector3 v1 = last;
                Vector3 v2 = path.get(i);

                if (v2 == newestLineP1) return;

                Vector3 intersection = LineIntersectionUtils.intersectionOfHorizontalAndVerticalLines(v1, v2, newestLineP1, newestLineP2);
                if (intersection != null) {
                    int lastValidWaypoint = path.indexOf(v1);
                    path = path.subList(0, lastValidWaypoint+1);
                    path.add(intersection);
                    path.add(currentPosition);
                    System.out.println("Intersection detected @ " + intersection.toString());
                    return;
                }

                last = v2;
            }

//            while (iterator.hasNext()) {
//                Vector3 v1 = last;
//                Vector3 v2 = iterator.next();
//
//                if (v2 == newestLineP1) return;
//
//                Vector3 intersection = LineIntersectionUtils.intersectionOfHorizontalAndVerticalLines(v1, v2, newestLineP1, newestLineP2);
//                if (intersection != null) {
//                    int lastValidWaypoint = path.indexOf(v2);
//                    path = path.subList(0, lastValidWaypoint);
//                    path.add(intersection);
//                    System.out.println("Intersection detected @ " + intersection.toString());
//                    return;
//                }
//
//                last = v2;
//            }
        }
    }

    public SpiderPathArray getSpiderPathArray() {
        return new SpiderPathArray(path);
    }

    public class SpiderPathArray {
        private final double[] xPositions;
        private final double[] yPositions;
        private final int size;

        public SpiderPathArray(List<Vector3> waypoints) {
            size = waypoints.size();
            xPositions = new double[size];
            yPositions = new double[size];

            for (int i = 0; i < size; i++) {
                xPositions[i] = waypoints.get(i).getX();
                yPositions[i] = waypoints.get(i).getY();
            }
        }

        public double[] getXPositions() {
            return this.xPositions;
        }

        public double[] getYPositions() {
            return this.yPositions;
        }

        public int size() {
            return this.size;
        }

    }

    public String toString() {
        return path.stream().map(Vector3::toString).collect(Collectors.joining(", "));
    }

}
