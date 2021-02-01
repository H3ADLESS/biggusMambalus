package de.h3ad.mamba;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class Input {

    Set<KeyCode> pressedKeys = ConcurrentHashMap.newKeySet();

    Map<KeyCode, List<EventHandler<KeyEvent>>> registeredOnPressEventHandler = new HashMap<>();
    Map<KeyCode, List<EventHandler<KeyEvent>>> registeredOnReleaseEventHandler = new HashMap<>();

    Map<KeyCode, KeyEvent> keyDownEventsSinceLastUpdate = new ConcurrentHashMap<>();
//    List<KeyEvent> keyDownEventsSinceLastUpdate = Collections.synchronizedList(new ArrayList<>());

    public Input(Game game, Scene scene) {
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                pressedKeys.add(event.getCode());
                keyDownEventsSinceLastUpdate.put(event.getCode(), event);
            }
        });

        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                pressedKeys.remove(event.getCode());
            }
        });
    }

    public boolean isKeyDown(KeyCode key) {
        return pressedKeys.contains(key);
    }

    public boolean isKeyPressed(KeyCode key) {
        return (keyDownEventsSinceLastUpdate.get(key) != null);
    }

    public void onAfterUpdate() {
        // clears all events
        keyDownEventsSinceLastUpdate.clear();
    }

}
