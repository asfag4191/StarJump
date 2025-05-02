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
        assertDoesNotThrow(() -> attributes.setMaxHp(10));
        assertEquals(10, attributes.getMaxHp());
    }

    @Test
    void setJumpsLeftTest() {
        assertThrows(IllegalArgumentException.class, () -> attributes.setJumpsLeft(-1));
        assertDoesNotThrow(() -> attributes.setJumpsLeft(1));
        assertEquals(1, attributes.getJumpsLeft());
        assertThrows(IllegalArgumentException.class, () -> attributes.setJumpsLeft(maxJumps + 1));
    }

    @Test
    void setHpTest() {
        assertThrows(IllegalArgumentException.class, () -> attributes.setHp(-1));
        assertThrows(IllegalArgumentException.class, () -> attributes.setHp(maxHp + 1));
        assertDoesNotThrow(() -> attributes.setHp(1));
        assertEquals(1, attributes.getHp());
    }

    @Test
    void setJumpPowerTest() {
        assertThrows(IllegalArgumentException.class, () -> attributes.setJumpPower(-1));
        assertDoesNotThrow(() -> attributes.setJumpPower(1));
        assertEquals(1, attributes.getJumpPower());
    }

    @Test
    void setMaxJumpsTest() {
        assertThrows(IllegalArgumentException.class, () -> attributes.setMaxJumps(-1));
        assertDoesNotThrow(() -> attributes.setMaxJumps(1));
        assertEquals(1, attributes.getMaxJumps());
    }

    @Test
    void setSpeedTest() {
        assertThrows(IllegalArgumentException.class, () -> attributes.setSpeed(-1));
        assertDoesNotThrow(() -> attributes.setSpeed(1));
        assertEquals(1, attributes.getSpeed());
    }

    @Test
    void addJumpsTest() {
        attributes.setJumpsLeft(0);
        attributes.addJumps(-1);
        assertEquals(0, attributes.getJumpsLeft());
        attributes.addJumps(1);
        assertEquals(1, attributes.getJumpsLeft());
    }

    @Test
    void copyingAttributes() {
        CharacterAttributes copiedAttributes = new CharacterAttributes(attributes);
        assertFalse(copiedAttributes == attributes);
        assertEquals(attributes.getMaxHp(), copiedAttributes.getMaxHp());
        assertEquals(attributes.getJumpPower(), copiedAttributes.getJumpPower());
        assertEquals(attributes.getMaxJumps(), copiedAttributes.getMaxJumps());
        assertEquals(attributes.getSpeed(), copiedAttributes.getSpeed());
        assertEquals(attributes.getStrength(), copiedAttributes.getStrength());
    }
}
