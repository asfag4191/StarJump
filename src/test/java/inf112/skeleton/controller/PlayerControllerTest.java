package inf112.skeleton.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import inf112.skeleton.model.character.Character;
import inf112.skeleton.model.character.CharacterAttributes;
import inf112.skeleton.model.character.controllable_characters.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.Map;

public class PlayerControllerTest {

    private Character character;
    private CharacterAttributes attributes;
    private InputBinder binder;
    private Player player;

    @BeforeEach
    void setUp() {
        character = mock(Character.class);
        attributes = mock(CharacterAttributes.class);
        when(character.getAttributes()).thenReturn(attributes);
        when(character.getVelocity()).thenReturn(new Vector2(0, 0));

        binder = new InputBinder();
        player = new Player(character) {
            {
                controller = binder;
            }
        };
    }

    @Test
    void keyPressedD_movesRight() throws Exception {
        when(attributes.getSpeed()).thenReturn(3f);

        PlayerController.setupWASD(player);

        Runnable moveRight = getBoundRunnable(binder, "keyHoldBindings", Input.Keys.D);
        moveRight.run();

        verify(character).setVelocity(new Vector2(3f, 0));
    }

    @Test
    void keyPressedRightArrow_movesRight() throws Exception {
        when(attributes.getSpeed()).thenReturn(3f);

        PlayerController.setupWASD(player);

        Runnable moveRight = getBoundRunnable(binder, "keyHoldBindings", Input.Keys.RIGHT);
        moveRight.run();

        verify(character).setVelocity(new Vector2(3f, 0));
    }

    @Test
    void pressingA_movesLeft() throws Exception {
        when(attributes.getSpeed()).thenReturn(2f);

        PlayerController.setupWASD(player);

        Runnable moveLeft = getBoundRunnable(binder, "keyHoldBindings", Input.Keys.A);
        moveLeft.run();

        verify(character).setVelocity(new Vector2(-2f, 0));
    }

    @Test
    void pressingLeftArrow_movesLeft() throws Exception {
        when(attributes.getSpeed()).thenReturn(2f);

        PlayerController.setupWASD(player);

        Runnable moveLeft = getBoundRunnable(binder, "keyHoldBindings", Input.Keys.LEFT);
        moveLeft.run();

        verify(character).setVelocity(new Vector2(-2f, 0));
    }

    @Test
    void pressingSpace_jumpsIfJumpsLeft() throws Exception {
        when(attributes.getJumpPower()).thenReturn(6f);
        when(attributes.getJumpsLeft()).thenReturn(1);

        PlayerController.setupWASD(player);

        Runnable jump = getBoundRunnable(binder, "keyPressBindings", Input.Keys.SPACE);
        jump.run();

        verify(character).setVelocity(new Vector2(0, 6f));
        verify(attributes).addJumps(-1);
    }

    @Test
    void pressingSpace_doesNothingIfNoJumps() throws Exception {
        when(attributes.getJumpsLeft()).thenReturn(0);

        PlayerController.setupWASD(player);

        Runnable jump = getBoundRunnable(binder, "keyPressBindings", Input.Keys.SPACE);
        jump.run();

        verify(character, never()).setVelocity(any());
        verify(attributes, never()).addJumps(anyInt());
    }

    private Runnable getBoundRunnable(InputBinder binder, String fieldName, int key) throws Exception {
        Field field = InputBinder.class.getDeclaredField(fieldName);
        field.setAccessible(true);
        Map<Integer, Runnable> bindings = (Map<Integer, Runnable>) field.get(binder);
        return bindings.get(key);
    }
}