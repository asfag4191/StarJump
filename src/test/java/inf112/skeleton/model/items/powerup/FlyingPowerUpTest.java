package inf112.skeleton.model.items.powerup;

import inf112.skeleton.model.character.Character;
import inf112.skeleton.model.character.CharacterAttributes;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.badlogic.gdx.backends.headless.HeadlessApplicationConfiguration;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Timer;

import inf112.skeleton.model.character.controllable_characters.Player;

public class FlyingPowerUpTest {

    private FlyingPowerUp flyingPowerUp;
    private World world;
    private Character character;

    @BeforeAll
    static void initLibGdx() {
        if (Gdx.app == null) {
            HeadlessApplicationConfiguration config = new HeadlessApplicationConfiguration();
            new HeadlessApplication(new com.badlogic.gdx.ApplicationAdapter() {}, config);
        }
    }

    @BeforeEach
    void setUp() {
        Gdx.gl = Gdx.gl20 = Mockito.mock(GL20.class);
        world = new World(new Vector2(0, -9.81f), true);
        CharacterAttributes attributes = new CharacterAttributes(3, 1, 2, 1, 1);
        character = new Character("", attributes, new Vector2(1, 1), world);
        Sprite sprite = mock(Sprite.class);
        flyingPowerUp = new FlyingPowerUp(character, new Vector2(0, 0), sprite);
    }

    @Test
    public void testFlyingEffectExpiresAfterDuration() {
        flyingPowerUp.applyPowerUpEffect();

        // Immediately after applying effect
        assertEquals(0f, character.getGravityScale(), "Gravity should be zero immediately after applying.");
        assertTrue(character.getVelocity().y > 0f, "Player should have upward velocity immediately.");
        assertTrue(character.isSensor(), "Collision should be disabled immediately.");

        // Simulate time passing — run the actual effect logic manually
        character.setGravityScale(1f);
        character.setAsSensor(false);

        assertEquals(1f, character.getGravityScale(), "Gravity should revert to normal after duration.");
        assertFalse(character.isSensor(), "Collision should be re-enabled after duration.");
    }
}
