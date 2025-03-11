package inf112.skeleton.controller;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import inf112.skeleton.model.character.Character;
import inf112.skeleton.model.character.Stats;
import inf112.skeleton.model.game_objects.Player;

/**
 * this class will move the player with keystrokes
 */
public final class PlayerController {

    public static void setupWASD(Player player) {
        InputBinder controller = player.controller;
        Character charac = player.character;
        Stats stats = charac.getStats();

        controller.bindKeyHold(Input.Keys.D, () -> charac.setVelocity(
                new Vector2(stats.speed(), charac.getVelocity().y))
        );
        controller.bindKeyHold(Input.Keys.A, () -> charac.setVelocity(
                new Vector2(-stats.speed(), charac.getVelocity().y))
        );
        controller.bindKeyPress(Input.Keys.SPACE, () -> charac.applyImpulse(
                new Vector2(0, stats.jumpPower()))
        );
    }
}
