package inf112.skeleton.model.character;

import static org.junit.jupiter.api.Assertions.assertEquals;

import inf112.skeleton.model.SimpleEnemy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SimpleEnemyTest {
    private Character enemy;
    /**
	 * Setup method called before each of the test methods
	 */
	@BeforeEach
	void setUpBeforeEach() {
        enemy = new SimpleEnemy("star", 100, 10);
	}

    @Test
    void playerSanityTest() {
        Character enemy1 = new SimpleEnemy("enemy", 100, 5);

        assertEquals("enemy", enemy1.getName());
        assertEquals(100, enemy1.getMaxHp());
        assertEquals(100, enemy1.getHp());
        assertEquals(5, enemy1.getStrength());
    }

    @Test
    void takeDamageTest() {
        enemy.takeDamage(3);
        assertEquals(97, enemy.getHp());

        enemy.takeDamage(-10);
        assertEquals(97, enemy.getHp());

        enemy.takeDamage(97);
        assertEquals(0, enemy.getHp());

        enemy.takeDamage(5);
        assertEquals(0, enemy.getHp());
    }

    @Test
    void toStringTest() {
        assertEquals("star", enemy.toString());
    }
}
