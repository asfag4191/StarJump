package inf112.skeleton.model.character.controllable_characters;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

import inf112.skeleton.model.character.Character;
import inf112.skeleton.model.character.CharacterAttributes;

public class PlayerTest {
    private World world;

    @BeforeEach
    void setup() {
        world = new World(new Vector2(0, -9.8f), true);
    }

    @AfterEach
    void tearDown() {
        world.dispose();
    }

    @Test
    void playerConstructorSetsIsPlayerTrue() {
        CharacterAttributes attributes = new CharacterAttributes(100, 10, 2, 5, 1);
        Character character = new Character("AnotherPlayer", attributes, new Vector2(1, 1), world);
        Player player = new Player(character);

        assertEquals("AnotherPlayer", player.character.getName());
        assertTrue(player.character.isPlayer());
    }
}