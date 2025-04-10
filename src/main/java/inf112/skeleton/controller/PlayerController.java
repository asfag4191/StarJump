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
        CharacterAttributes attributes = character.attributes;

        controller.bindKeyHold(Input.Keys.D, () -> move(character, attributes.getSpeed()));
        controller.bindKeyHold(Input.Keys.RIGHT, () -> move(character, attributes.getSpeed()));

        controller.bindKeyHold(Input.Keys.A, () -> move(character, -attributes.getSpeed()));
        controller.bindKeyHold(Input.Keys.LEFT, () -> move(character, -attributes.getSpeed()));

        controller.bindKeyPress(Input.Keys.SPACE, () -> jump(character, attributes.getJumpPower()));
        controller.bindKeyPress(Input.Keys.UP, () -> jump(character, attributes.getJumpPower()));
    }

    private static void move(Character character, float velocity) {
        character.setVelocity(new Vector2(velocity, character.getVelocity().y));
    }

    private static void jump(Character character, float jumpPower) {
        if (character.attributes.getJumpsLeft() == 0)
            return;
        character.attributes.addJumps(-1);
        character.setVelocity(new Vector2(character.getVelocity().x, jumpPower));
    }
}
