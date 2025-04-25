package inf112.skeleton.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class InputBinderTest {
    private InputBinder binder;
    private boolean pressCalled;
    private boolean holdCalled;

    @BeforeEach
    void setup() {
        binder = new InputBinder();
        pressCalled = false;
        holdCalled = false;
    }

    @Test
    void bindKeyPress_runsWhenTriggered() throws Exception {
        binder.bindKeyPress(1, () -> pressCalled = true);

        Runnable press = getBinding("keyPressBindings", 1);
        assertNotNull(press);

        press.run();
        assertTrue(pressCalled);
    }

    @Test
    void bindKeyHold_runsWhenTriggered() throws Exception {
        binder.bindKeyHold(2, () -> holdCalled = true);

        Runnable hold = getBinding("keyHoldBindings", 2);
        assertNotNull(hold);

        hold.run();
        assertTrue(holdCalled);
    }

    @Test
    void bindKeyPress_overwritesKeyHoldBinding() throws Exception {
        binder.bindKeyHold(3, () -> {
        });
        binder.bindKeyPress(3, () -> pressCalled = true);

        assertNull(getBinding("keyHoldBindings", 3));
        assertNotNull(getBinding("keyPressBindings", 3));
    }

    @Test
    void unbindKey_removesAllBindings() throws Exception {
        binder.bindKeyPress(4, () -> {
        });
        binder.bindKeyHold(5, () -> {
        });
        binder.unbindKey(4);
        binder.unbindKey(5);

        assertNull(getBinding("keyPressBindings", 4));
        assertNull(getBinding("keyHoldBindings", 5));
    }

    private Runnable getBinding(String fieldName, int key) throws Exception {
        var field = InputBinder.class.getDeclaredField(fieldName);
        field.setAccessible(true);
        var map = (java.util.Map<Integer, Runnable>) field.get(binder);
        return map.get(key);
    }
}
