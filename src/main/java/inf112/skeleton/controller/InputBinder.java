package inf112.skeleton.controller;

import com.badlogic.gdx.Gdx;

import java.util.HashMap;

public class InputBinder {
    private final HashMap<Integer, Runnable> keyPressBindings = new HashMap<>();
    private final HashMap<Integer, Runnable> keyHoldBindings = new HashMap<>();

    public void bindKeyPress(int key, Runnable action) {
        if (keyHoldBindings.containsKey(key)) { // a key shouldn't be binded to press and hold at the same time
            unbindKey(key);
        }
        keyPressBindings.put(key, action);
    }

    public void bindKeyHold(int key, Runnable action) {
        if (keyPressBindings.containsKey(key)) { // a key shouldn't be binded to press and hold at the same time
            unbindKey(key);
        }
        keyHoldBindings.put(key, action);
    }

    public void unbindKey(int key) {
        keyPressBindings.remove(key);
        keyHoldBindings.remove(key);
    }

    public void update() {
        for (Integer key : keyPressBindings.keySet()) {
            if (Gdx.input.isKeyJustPressed(key)) {
                keyPressBindings.get(key).run();
            }
        }
        for (Integer key : keyHoldBindings.keySet()) {
            if (Gdx.input.isKeyPressed(key)) {
                keyHoldBindings.get(key).run();
            }
        }
    }
}
