package de.h3ad.mamba.gameobjects;

import de.h3ad.mamba.math.Vector3;

import java.util.ArrayList;
import java.util.List;

public class SpiderPath {

    private List<Vector3> path = new ArrayList<>();

    public void add(Vector3 position) {
        this.path.add(position);
    }

    public void updateLast(Vector3 position) {
        if (this.path.size() > 0) {
            path.set(path.size() - 1, position);
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



}
