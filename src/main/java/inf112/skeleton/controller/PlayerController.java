package inf112.skeleton.controller;

import java.awt.event.KeyEvent;

import com.badlogic.gdx.backends.lwjgl3.audio.Mp3.Sound;

import inf112.skeleton.model.GameState;
import inf112.skeleton.model.StarJump;
import inf112.skeleton.model.character.Character;
import inf112.skeleton.view.screen.GameScreen;
import inf112.skeleton.view.screen.MainMenuScreen;

/**
 * this class will move the player with keystrokes
 */
public class PlayerController implements java.awt.event.KeyListener {
    final StarJump game;
    Character player;
    GameScreen gameScreen;
    private Sound jumpSound; // sound when player jumps, add later
    private iControllableGameModel controller;

    public PlayerController(StarJump game, inf112.skeleton.model.character.Character player,
            iControllableGameModel controller) {
        this.game = game;
        this.controller = controller;
        this.player = player;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (controller.getGameState() == GameState.GAME_ACTIVE) {
            if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                controller.movePlayer(0, -1);
            } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                controller.movePlayer(0, 1);
            } else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                controller.movePlayer(2, 0);
            } else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                // player use powerUp
            } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                game.setScreen(new MainMenuScreen(game));
            }

        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // empty for now
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // empty for now
    }

}
