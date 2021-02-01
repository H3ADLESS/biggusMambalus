package de.h3ad.mamba.gameobjects.text;

import de.h3ad.mamba.GameObject;
import de.h3ad.mamba.math.Vector3;
import javafx.geometry.VPos;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public abstract class Text extends GameObject {

    String text;
    public Vector3 position;
    Font font = new Font(20);
    TextAlignment textAlignment = TextAlignment.LEFT;
    Color color = Color.BLACK;
    double width = 2.0;
    boolean strokeText = false; // otherwise fillText

    @Override
    public void update(double deltaTime) {

    }

    @Override
    public void draw() {
        GraphicsContext gc = game.getGraphicsContext();

        gc.setFont(font);
        gc.setTextAlign(textAlignment);
        gc.setTextBaseline(VPos.CENTER);
        gc.setFill(color);
        if (strokeText) {
            gc.strokeText(text, position.getX(), position.getY(), width);
        } else {
            gc.fillText(text, position.getX(), position.getY());
        }

        gc.setFill(Color.BLACK);

    }

}
