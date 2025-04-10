package inf112.skeleton.controller;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import inf112.skeleton.model.character.Character;
import inf112.skeleton.model.character.CharacterAttributes;
import inf112.skeleton.model.character.controllable_characters.Player;

/**
 * this class will move the player with keystrokes
 */
public final class PlayerController {

    //// NOTE: this is a simple temp solution, can be implemented much better. ////
    public static void setupWASD(Player player) {
        InputBinder controller = player.controller;
        Character character = player.character;

        controller.bindKeyHold(Input.Keys.D, () -> move(character, 1));
        controller.bindKeyHold(Input.Keys.RIGHT, () -> move(character, 1));

        controller.bindKeyHold(Input.Keys.A, () -> move(character, -1));
        controller.bindKeyHold(Input.Keys.LEFT, () -> move(character, -1));

        controller.bindKeyPress(Input.Keys.SPACE, () -> jump(character));
        controller.bindKeyPress(Input.Keys.UP, () -> jump(character));
    }

    private static void move(Character character, int velocityMultiplier) {
        CharacterAttributes attributes = character.getAttributes();
        float xVelocity = attributes.getSpeed() * velocityMultiplier;
        float yVelocity = character.getVelocity().y;

        character.setVelocity(new Vector2(xVelocity, yVelocity));
    }

    private static void jump(Character character) {
        CharacterAttributes attributes = character.getAttributes();

        if (attributes.getJumpsLeft() == 0) return;

        float xVelocity = character.getVelocity().x;
        float yVelocity = attributes.getJumpPower();

        character.setVelocity(new Vector2(xVelocity, yVelocity));
        attributes.addJumps(-1);
    }
}
