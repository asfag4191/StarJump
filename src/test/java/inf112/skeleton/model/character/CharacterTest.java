package inf112.skeleton.model.character;

import static org.junit.jupiter.api.Assertions.*;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import org.junit.jupiter.api.*;

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
        world = new World(new Vector2(0,0), true);
        // setting up character
        character = new Character(name, attributes, size, world);
    }

    @Test
    void getName() {
        assertEquals(name, character.getName());
    }

    @Test
    void getState_default() {
        // default state is idle
        assertSame(CharacterState.IDLE, character.getState());
    }

    @Test
    void getAttributes() {
        assertSame(attributes, character.getAttributes());
    }

    @Test
    void getSize() {
        assertEquals(size, character.getSize());
    }

    @Test
    void setState() {
        // set to moving
        character.setState(CharacterState.MOVING);
        assertSame(CharacterState.MOVING, character.getState());
        // set to freefall
        character.setState(CharacterState.FREEFALL);
        assertSame(CharacterState.FREEFALL, character.getState());
        // shouldn't be able to change to moving in freefall
        character.setState(CharacterState.MOVING);
        assertSame(CharacterState.FREEFALL, character.getState());
        // set to dead
        character.setState(CharacterState.DEAD);
        assertSame(CharacterState.DEAD, character.getState());
        // characterstate is locked after death
        character.setState(CharacterState.IDLE);
        assertSame(CharacterState.DEAD, character.getState());
    }

    @Test
    void takeDamage_not_killing() {
        float damage = 1;

        character.takeDamage(damage);

        float currentHp = character.getAttributes().getHp();
        float maxHp = character.getAttributes().getMaxHp();

        assertEquals(maxHp - damage, currentHp);
        assertSame(character.getState(), CharacterState.IDLE);
    }

    @Test
    void takeDamage_killing() {
        float damage = character.getAttributes().getHp();

        character.takeDamage(damage);

        float currentHp = character.getAttributes().getHp();

        assertEquals(0, currentHp);
        assertSame(character.getState(), CharacterState.DEAD);
    }

    @Test
    void setGrounded() {
        // not grounded
        character.setGrounded(true);
        assertNotSame(character.getState(), CharacterState.FREEFALL);
        // grounded
        character.setGrounded(false);
        assertSame(character.getState(), CharacterState.FREEFALL);
    }

    @Test
    void setPlayer_and_isPlayer() {
        character.setPlayer(true);
        assertTrue(character.isPlayer());
    }
}
