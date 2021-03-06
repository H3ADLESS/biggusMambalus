package de.h3ad.mamba.gameobjects;

import de.h3ad.mamba.Game;
import de.h3ad.mamba.GameObject;
import de.h3ad.mamba.math.Vector3;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class SafeZone extends GameObject {

    private List<Vector3> polygon;

    private static SafeZone SAFE_ZONE;

    public SafeZone() {
        super();
        polygon = new ArrayList<>();
        // x, y

        polygon.add(new Vector3(Board.BOARD_LEFT, Board.BOARD_TOP));
        polygon.add(new Vector3(Board.BOARD_LEFT + Board.BOARD_WIDTH, Board.BOARD_TOP));
        polygon.add(new Vector3(Board.BOARD_LEFT + Board.BOARD_WIDTH, Board.BOARD_TOP + Board.BOARD_HEIGHT));
        polygon.add(new Vector3(Board.BOARD_LEFT, Board.BOARD_TOP + Board.BOARD_HEIGHT));
        polygon.add(new Vector3(Board.BOARD_LEFT, Board.BOARD_TOP));
    }

    public static SafeZone getInstance() {
        if (SAFE_ZONE == null) {
            SAFE_ZONE = new SafeZone();
        }
        return SAFE_ZONE;
    }

    @Override
    public void update(double deltaTime) {

    }

    @Override
    public void draw() {
        double[] xs = new double[polygon.size()];
        double[] ys = new double[polygon.size()];
        for (int i = 0; i <polygon.size(); i++) {
            xs[i] = polygon.get(i).getX();
            ys[i] = polygon.get(i).getY();
        }

        if (xs.length > 0) {
            getGraphics().setStroke(Color.BLUE);
            getGraphics().strokePolyline(xs, ys, xs.length);
            getGraphics().setStroke(Color.BLACK);
        }
    }

    public List<Vector3> getPolygon() {
        return polygon;
    }

}
