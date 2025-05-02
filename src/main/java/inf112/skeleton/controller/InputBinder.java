package inf112.skeleton.controller;

import com.badlogic.gdx.Gdx;

import java.util.HashMap;

public class InputBinder {
    private final HashMap<Integer, Runnable> keyPressBindings = new HashMap<>();
    private final HashMap<Integer, Runnable> keyHoldBindings = new HashMap<>();

    /**
     * Binds a key to an action that runs once when the key is pressed.
     * If the key is already set for hold, it is removed first.
     *
     * @param key    the key code
     * @param action the action to run
     */
    public void bindKeyPress(int key, Runnable action) {
        if (keyHoldBindings.containsKey(key)) { // a key shouldn't be binded to press and hold at the same time
            unbindKey(key);
        }
        keyPressBindings.put(key, action);
    }

    /**
     * Binds a key to an action that runs while the key is held down.
     * If the key is already set for press, it is removed first.
     *
     * @param key    the key code
     * @param action the action to run
     */
    public void bindKeyHold(int key, Runnable action) {
        if (keyPressBindings.containsKey(key)) { // a key shouldn't be binded to press and hold at the same time
            unbindKey(key);
        }
        keyHoldBindings.put(key, action);
    }

    /**
     * Removes any action bound to the given key.
     *
     * @param key the key code
     */
    public void unbindKey(int key) {
        keyPressBindings.remove(key);
        keyHoldBindings.remove(key);
    }

    /**
     * Checks for key presses and holds and runs the bound actions.
     * Should be called every frame.
     */
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
