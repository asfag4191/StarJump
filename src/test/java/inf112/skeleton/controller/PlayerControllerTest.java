package inf112.skeleton.controller;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.*;

import com.badlogic.gdx.Input;

import inf112.skeleton.app.StarJump;
import inf112.skeleton.model.GameState;
import inf112.skeleton.model.character.Character;

public class PlayerControllerTest {
    private StarJump game;
    private Character player;
    private IControllablePlayer controller;
    private PlayerController playerController;

    @BeforeEach
    public void setUp() {
        game = mock(StarJump.class);
        player = mock(Character.class);
        controller = mock(IControllablePlayer.class);
        playerController = new PlayerController(game, player, controller);
    }

    @Test
    public void testKeyDownLeft() {
        when(controller.getGameState()).thenReturn(GameState.GAME_ACTIVE);
        playerController.keyDown(Input.Keys.LEFT);
        verify(controller).movePlayer(0, -1);
    }

    @Test
    public void testKeyDownRight() {
        when(controller.getGameState()).thenReturn(GameState.GAME_ACTIVE);
        playerController.keyDown(Input.Keys.RIGHT);
        verify(controller).movePlayer(0, 1);
    }

    @Test
    public void testKeyDownSpace() {
        when(controller.getGameState()).thenReturn(GameState.GAME_ACTIVE);

        playerController.keyDown(Input.Keys.SPACE);

        verify(controller).movePlayer(2, 0);
    }

    @Test
    public void testKeyDownInactiveGame() {
        when(controller.getGameState()).thenReturn(GameState.GAME_OVER);

        playerController.keyDown(Input.Keys.LEFT);

        verify(controller, never()).movePlayer(anyInt(), anyInt());
    }

}
