package inf112.skeleton.model.items.powerup;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.EllipseMapObject;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;

import inf112.skeleton.model.character.controllable_characters.Player;
import inf112.skeleton.view.screen.GameScreen;

class PowerUpObjectTest {
    private PowerUpObject powerUpObject;
    private GameScreen screen;
    private MapObject mapObject;
    private AbstractPowerUp powerUp;
    private Player player;
    private Sprite sprite;
    private World world;
    private Body body;

@BeforeEach
void setUp() {
    screen = mock(GameScreen.class);
    powerUp = mock(AbstractPowerUp.class);
    sprite = mock(Sprite.class);
    world = new World(new Vector2(0, -9.81f), true); 
    body = world.createBody(new BodyDef()); 

    mapObject = new EllipseMapObject(100, 200, 16, 16);  

    player = mock(Player.class);
    when(player.getBody()).thenReturn(body);


    PowerUpManager powerUpManager = mock(PowerUpManager.class);
    when(screen.getPowerUpManager()).thenReturn(powerUpManager);
    when(screen.getWorld()).thenReturn(world);

    when(powerUp.getSprite()).thenReturn(sprite); 

    powerUpObject = new PowerUpObject(screen, mapObject, powerUp, player, sprite);
    powerUpObject.setBody(body); 
}


    /**
     * Test Constructor Initialization
     * Ensures all fields are correctly initialized
     */
    @Test
    void testConstructorInitializesCorrectly() {
        assertNotNull(powerUpObject);
        assertEquals(player, powerUpObject.getPlayer());
        assertEquals(powerUp, powerUpObject.getPowerUp());
        assertEquals(body, powerUpObject.getBody()); 
    }


    /**
     * Test Collision Handling
     * Ensures it trigger power-up effect and player impulse,
     * and removal.
     */
    @Test
    void testOnPlayerCollide() {
        Body mockBody = mock(Body.class);
        when(player.getBody()).thenReturn(mockBody);
    
        when(mockBody.getLinearVelocity()).thenReturn(new Vector2(0, 0));
    
        powerUpObject.onPlayerCollide();
            verify(powerUp).applyPowerUpEffect();
            verify(screen.getPowerUpManager()).markForRemoval(powerUpObject);
            verify(mockBody).applyLinearImpulse(new Vector2(0, 10f), mockBody.getWorldCenter(), true);
            assertTrue(powerUpObject.isCollected());
    }
    

    /**
     * Test Setting & Checking Collection State
     */
    @Test
    void testSetAndIsCollected() {
        assertFalse(powerUpObject.isCollected()); 
        assertTrue(powerUpObject.isCollected()); 
    }

    /**
     * Test Physics Body Disposal
     */
    @Test
    void testDispose() {
        powerUpObject.dispose();
        assertNull(powerUpObject.getBody()); 
    }

    /**
     * Test Update Behavior
     */
    @Test
    void testUpdateCallsDisposeWhenCollected() {
        powerUpObject.setCollected(true);
        powerUpObject.update(0.1f);

        // Ensure `dispose()` is called when collected
        assertNull(powerUpObject.getBody());
    }

    /**
     * Test Sprite Retrial
     * Ensures it returns the correct sprite.
     */
    @Test
    void testGetSprite() {
        assertEquals(sprite, powerUpObject.getSprite());
    }
}
