package inf112.skeleton.controller;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.backends.lwjgl3.audio.Mp3.Sound;

import inf112.skeleton.app.StarJump;
import inf112.skeleton.model.GameState;
import inf112.skeleton.model.character.Character;
import inf112.skeleton.view.screen.GameScreen;
import inf112.skeleton.view.screen.MainMenuScreen;

/**
 * this class will move the player with keystrokes
 */
public class PlayerController extends ApplicationAdapter implements InputProcessor {
    final StarJump game;
    Character player;
    GameScreen gameScreen;
    private Sound jumpSound;
    private IControllablePlayer controller;

    public PlayerController(StarJump game, inf112.skeleton.model.character.Character player,
            IControllablePlayer controller) {
        this.game = game;
        this.controller = controller;
        this.player = player;
    }

    @Override
    public boolean keyDown(int keycode) {
        if (controller.getGameState() == GameState.GAME_ACTIVE) {
            switch (keycode) {
                case Input.Keys.LEFT:
                    controller.movePlayer(0, -1);
                    break;
                case Input.Keys.RIGHT:
                    controller.movePlayer(0, 1);
                    break;
                case Input.Keys.SPACE:
                    controller.movePlayer(2, 0);
                    break;
                case Input.Keys.ESCAPE:
                    game.setScreen(new MainMenuScreen(game));
                    break;
                default:
                    break;
            }
        }
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        // not relevant yet
        // used when an action is set in motion when a key is released
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        // not relevant for this task
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        // for mouseClicks, not relevant for player (yet)
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        // for mouseClicks, not relevant for player (yet)
        return false;
    }

    @Override
    public boolean touchCancelled(int screenX, int screenY, int pointer, int button) {
        // for dragging mouseTouch over screen, not relevant for player
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        // for dragging mouseTouch over screen, not relevant for player
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        // not relevant for this task
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        // not relevant for this task
        return false;
    }
}
