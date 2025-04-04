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

/**
 * Test class for PowerUpObject
 */
class PowerUpObjectTest {
    private PowerUpObject powerUpObject;
    private GameScreen screen;
    private MapObject mapObject;
    private iPowerUp powerUp;
    private Player player;
    private Sprite sprite;
    private World world;
    private Body body;

@BeforeEach
void setUp() {
    screen = mock(GameScreen.class);
    powerUp = mock(iPowerUp.class);
    sprite = mock(Sprite.class);
    world = new World(new Vector2(0, -9.81f), true); 
    body = world.createBody(new BodyDef()); 

    mapObject = new EllipseMapObject(100, 200, 16, 16);  

    PowerUpManager powerUpManager = mock(PowerUpManager.class);
    when(screen.getPowerUpManager()).thenReturn(powerUpManager);
    when(screen.getWorld()).thenReturn(world);

    when(powerUp.getSprite()).thenReturn(sprite); 

    powerUpObject = new PowerUpObject(screen, mapObject, powerUp, player, sprite);
    powerUpObject.setBody(body); 
}

    @Test
    void testConstructorInitializesCorrectly() {
        assertNotNull(powerUpObject);
        assertEquals(powerUp, powerUpObject.getPowerUp());
        assertEquals(body, powerUpObject.getBody()); 
    }

    @Test
    void testOnPlayerCollide() {
        powerUpObject.onPlayerCollide();
        assertTrue(powerUpObject.isCollected(), "Power-up should be marked as collected");
    
        verify(powerUp).applyPowerUpEffect();
    }

    @Test
    void testOnPlayerCollideMarksForRemoval() {
    powerUpObject.onPlayerCollide();
    verify(screen.getPowerUpManager()).markForRemoval(powerUpObject);
}
    @Test
    void testSetAndIsCollected() {
        assertFalse(powerUpObject.isCollected()); 
        powerUpObject.setCollected(true); 
        assertTrue(powerUpObject.isCollected()); 
    }

    @Test
    void testDisposeDestroysBody() {
        assertNotNull(powerUpObject.getBody(), "Body should exist before dispose");
    
        powerUpObject.dispose();
    
        assertNull(powerUpObject.getBody(), "Body should be null after dispose");
    }
    
    @Test
    void testUpdateDoesNotDisposeWhenNotCollected() {
        Body originalBody = powerUpObject.getBody();
        powerUpObject.setCollected(false);
        powerUpObject.update(0.1f);
        assertEquals(originalBody, powerUpObject.getBody());
    }
    @Test
    void testGetSprite() {
        assertEquals(sprite, powerUpObject.getSprite());
    }

    @Test
    void testGetWorldReturnsCorrectWorld() {
        assertEquals(world, powerUpObject.getWorld());
    }

    @Test
    void testGetPlayerReturnsCorrectPlayer() {  
        assertEquals(player, powerUpObject.getPlayer());
    }


}
