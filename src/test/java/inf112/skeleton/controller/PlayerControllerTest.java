package inf112.skeleton.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import org.mockito.Mockito;

import com.badlogic.gdx.Gdx;

import inf112.skeleton.model.GameState;
import inf112.skeleton.model.StarJump;
import inf112.skeleton.model.character.Character;
import inf112.skeleton.view.screen.MainMenuScreen;

import java.awt.event.KeyEvent;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

public class PlayerControllerTest {
    private StarJump game;
    private inf112.skeleton.model.character.Character player;
    private iControllableGameModel controller;
    private PlayerController playerController;

    @BeforeEach
    public void setUp() {
        game = mock(StarJump.class);
        player = mock(Character.class);
        controller = mock(iControllableGameModel.class);
        playerController = new PlayerController(game, player, controller);
    }

    @Test
    public void testKeyPressed_Left() {
        when(controller.getGameState()).thenReturn(GameState.GAME_ACTIVE);
        KeyEvent leftKeyEvent = new KeyEvent(new java.awt.Button(), KeyEvent.KEY_PRESSED,
                System.currentTimeMillis(), 0, KeyEvent.VK_LEFT, ' ');

        playerController.keyPressed(leftKeyEvent);

        verify(controller).movePlayer(0, -1);
    }

    @Test
    public void testKeyPressed_Right() {
        when(controller.getGameState()).thenReturn(GameState.GAME_ACTIVE);
        KeyEvent rightKeyEvent = new KeyEvent(new java.awt.Button(), KeyEvent.KEY_PRESSED,
                System.currentTimeMillis(), 0, KeyEvent.VK_RIGHT, ' ');

        playerController.keyPressed(rightKeyEvent);

        verify(controller).movePlayer(0, 1);
    }

    @Test
    public void testKeyPressed_Space() {
        when(controller.getGameState()).thenReturn(GameState.GAME_ACTIVE);
        KeyEvent spaceKeyEvent = new KeyEvent(new java.awt.Button(), KeyEvent.KEY_PRESSED,
                System.currentTimeMillis(), 0, KeyEvent.VK_SPACE, ' ');

        playerController.keyPressed(spaceKeyEvent);

        verify(controller).movePlayer(2, 0);
    }

    @Test
    public void testKeyPressed_Enter() {
        when(controller.getGameState()).thenReturn(GameState.GAME_ACTIVE);
        KeyEvent enterKeyEvent = new KeyEvent(new java.awt.Button(), KeyEvent.KEY_PRESSED,
                System.currentTimeMillis(), 0, KeyEvent.VK_ENTER, ' ');

        playerController.keyPressed(enterKeyEvent);
        // For when powerUp is implemented
        // verify(controller).usePowerUp();
    }

    @Test
    public void testKeyPressed_InactiveGame() {
        when(controller.getGameState()).thenReturn(GameState.GAME_OVER);
        KeyEvent leftKeyEvent = new KeyEvent(new java.awt.Button(), KeyEvent.KEY_PRESSED,
                System.currentTimeMillis(), 0, KeyEvent.VK_LEFT, ' ');

        playerController.keyPressed(leftKeyEvent);

        verify(controller, never()).movePlayer(anyInt(), anyInt());
    }

    @Test
    public void testKeyPressed_GamePaused() {
        when(controller.getGameState()).thenReturn(GameState.GAME_PAUSED);
        KeyEvent leftKeyEvent = new KeyEvent(new java.awt.Button(), KeyEvent.KEY_PRESSED,
                System.currentTimeMillis(), 0, KeyEvent.VK_LEFT, ' ');

        playerController.keyPressed(leftKeyEvent);

        verify(controller, never()).movePlayer(anyInt(), anyInt());
    }
}
