package de.h3ad.mamba;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Input {

    Set<KeyCode> pressedKeys = (HashSet<KeyCode>) Collections.synchronizedCollection(new HashSet<KeyCode>());

    public Input(Game game, Scene scene) {
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                pressedKeys.add(event.getCode());
            }
        });

        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                pressedKeys.remove(event.getCode());
            }
        });
    }

    public boolean isKeyPressed(KeyCode key) {
        return pressedKeys.contains(key);
    }

}
