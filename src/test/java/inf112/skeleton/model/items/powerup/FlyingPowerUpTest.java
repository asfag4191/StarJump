package inf112.skeleton.model.items.powerup;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.mock;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.badlogic.gdx.backends.headless.HeadlessApplicationConfiguration;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

import inf112.skeleton.model.character.Character;
import inf112.skeleton.model.character.CharacterAttributes;


/**
 * Test for FlyingPowerUp
 */
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
    public void testFlyingEffectAppliesAndRestoresProperly() {
        flyingPowerUp.applyPowerUpEffect();
    
        assertEquals(0f, character.getGravityScale(), 0.001f, "Gravity should be zero immediately.");
        assertTrue(character.getVelocity().y > 0f, "Character should fly upward.");
        assertTrue(character.isSensor(), "Character should become a sensor (no collision).");
    
        character.setGravityScale(1f);
        character.setAsSensor(false);
        character.applyForce(new Vector2(0f, -5f));
    
        assertEquals(1f, character.getGravityScale(), 0.001f, "Gravity should be restored.");
        assertFalse(character.isSensor(), "Collision should be re-enabled.");
        
    }

}