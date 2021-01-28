package de.h3ad.mamba.gameobjects;

import de.h3ad.mamba.Game;
import de.h3ad.mamba.GameObject;
import de.h3ad.mamba.math.Vector3;

public class Spider extends GameObject {

    private Vector3 direction;

    public Spider() {
        super();
    }

    public void update(double deltaTime) {
        this.position.x += (100 * deltaTime);
        if (this.position.x > 800.0) this.position.x = 0;
        System.out.println(this.position.x);



    }

    public void draw() {
        getGraphics().fillOval(position.x, position.y, 5, 5);
    }
}
