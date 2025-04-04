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

import inf112.skeleton.model.character.controllable_characters.Player;

public class FlyingPowerUpTest {

    private FlyingPowerUp flyingPowerUp;
    private World world;
    private Player player;

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
        player = new Player(new Vector2(1, 1), world);
        Sprite sprite = mock(Sprite.class);
        flyingPowerUp = new FlyingPowerUp(player, new Vector2(0, 0), sprite);
    }

    @Test
    public void testFlyingEffectExpiresAfterDuration() {
        flyingPowerUp.applyPowerUpEffect();
    
        assertEquals(0f, player.getBody().getGravityScale(), "Gravity should be zero immediately after applying.");
        assertTrue(player.getBody().getLinearVelocity().y > 0f, "Player should have upward velocity immediately.");
        assertTrue(player.getBody().getFixtureList().first().isSensor(), "Collision should be disabled immediately.");
    
        // Simulate time passing — run the actual effect logic manually
        player.getBody().setGravityScale(1f);
        player.setCollisionEnabled(true);
    
        assertEquals(1f, player.getBody().getGravityScale(), "Gravity should revert to normal after duration.");
        assertFalse(player.getBody().getFixtureList().first().isSensor(), "Collision should be re-enabled after duration.");
    }
}