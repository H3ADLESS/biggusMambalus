package de.h3ad.mamba.gameobjects.text;

import de.h3ad.mamba.math.Vector3;

public class FpsCounter extends Text {

    private int updateCounter = 0;
    private double deltaTimes = 0.0;
    private final int UPDATE_DELTA_IN_FRAMES = 20;


    public FpsCounter(Vector3 position) {
        this.position = position;
    }

    @Override
    public void update(double deltaTime) {

        super.update(deltaTime);
        deltaTimes += deltaTime;
        updateCounter++;
        if (updateCounter % UPDATE_DELTA_IN_FRAMES == 0) {
            double fps = 1.0 / (deltaTimes/UPDATE_DELTA_IN_FRAMES);
            text = "FPS: " + ((int)Math.floor(fps));
            deltaTimes = 0.0;
        }
    }

}
