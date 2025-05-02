package inf112.skeleton.model.character;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CharacterAttributesTest {

    float maxHp = 5;
    float jumpPower = 3.5f;
    int maxJumps = 2;
    float speed = 2.5f;
    float strength = 1;
    CharacterAttributes attributes;

    @BeforeEach
    void setUpBeforeEach() {
        
        attributes = new CharacterAttributes(maxHp, jumpPower, maxJumps, speed, strength);
    }

    @Test
    void sanityTest() {
        assertNotNull(attributes);
        assertEquals(maxHp, attributes.getMaxHp());
        assertEquals(maxHp, attributes.getHp());
        assertEquals(jumpPower, attributes.getJumpPower());
        assertEquals(maxJumps, attributes.getMaxJumps());
        assertEquals(speed, attributes.getSpeed());
        assertEquals(strength, attributes.getStrength());
    }

    @Test
    void toStringTest() {
        String expected = "CharacterAttributes{maxHp=5.0, jumpPower=3.5, maxJumps=2, speed=2.5, strength=1.0, hp=5.0, jumpsLeft=2}";
        String actual = attributes.toString();
        assertEquals(expected, actual);
    }

    @Test
    void setMaxHpTest() {
        assertThrows(IllegalArgumentException.class, () -> attributes.setMaxHp(-5));
    }

    // TODO: not finished here ...
}
