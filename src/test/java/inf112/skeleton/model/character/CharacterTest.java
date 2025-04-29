package inf112.skeleton.model.character;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;

public class CharacterTest {
    private String name;
    private CharacterAttributes attributes;
    private Vector2 size;
    private World world;
    private Character character;

    @BeforeEach
    void setUp() {
        // necessary attributes
        name = "TestCharacter";
        attributes = new CharacterAttributes(2, 1, 1, 1, 1);
        size = new Vector2(1,1);
        world = mock(World.class);
        // setting up character
        character = new Character(name, attributes, size, world);
    }

    @Test
    void getName() {
        assertEquals(character.getName(), name);
    }

    @Test
    void getState_default() {
        // default state is idle
        assertEquals(character.getState(), CharacterState.IDLE);
    }

    @Test
    void getAttributes() {
        assertSame(character.getAttributes(), attributes);
    }

    @Test
    void getAnimator() {
        //assertSame(character.getAnimator());
    }

    @Test
    void getSize() {
    }

    @Test
    void setState() {
    }

    @Test
    void takeDamage() {
    }

    @Test
    void setGrounded() {
    }

    @Test
    void setPlayer() {
    }

    @Test
    void isPlayer() {
    }
}
