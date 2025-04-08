package inf112.skeleton.model.items.powerup;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.EllipseMapObject;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.World;

import inf112.skeleton.model.character.Character;
import inf112.skeleton.model.character.CharacterAttributes;
import inf112.skeleton.view.screen.GameScreen;



/**
 * Test for PowerUpObject
 */
class PowerUpObjectTest {

    private PowerUpObject powerUpObject;
    private iPowerUp mockPowerUp;
    private Character character;
    private GameScreen mockScreen;
    private MapObject mockMapObject;
    private Sprite mockSprite;
    private World world;
    private Body body;
    private Fixture fixture;

    @BeforeEach
    void setUp() {
        world = new World(new Vector2(0, -9.81f), true);
    
        character = new Character(
            "test",
            new CharacterAttributes(10.0f, 1.0f, 2, 1.0f, 1.0f),
            new Vector2(1, 1),
            world
        );
    
        mockPowerUp = mock(iPowerUp.class);
        mockScreen = mock(GameScreen.class);
        mockSprite = mock(Sprite.class);
    
        when(mockScreen.getWorld()).thenReturn(world);
        when(mockPowerUp.getSprite()).thenReturn(mockSprite);

        EllipseMapObject ellipseMapObject = new EllipseMapObject(100, 200, 16, 16);
    
        body = world.createBody(new BodyDef());
        fixture = body.createFixture(new CircleShape(), 1.0f);
    
        powerUpObject = new PowerUpObject(mockScreen, ellipseMapObject, mockPowerUp, character, mockSprite);
        powerUpObject.setBody(body);
    
        try {
            var field = powerUpObject.getClass().getSuperclass().getDeclaredField("fixture");
            field.setAccessible(true);
            field.set(powerUpObject, fixture);
        } catch (Exception e) {
            throw new RuntimeException("Failed to inject fixture", e);
        }
    }

    @Test
    void testInitialState() {
        assertFalse(powerUpObject.isCollected());
        assertEquals(character, powerUpObject.getCharacter());
        assertEquals(mockPowerUp, powerUpObject.getPowerUp());
        assertEquals(mockSprite, powerUpObject.getSprite());
        assertEquals(world, powerUpObject.getWorld());
        assertEquals(body, powerUpObject.getBody());
    }

    @Test
    void testSetCollected() {
        powerUpObject.setCollected(true);
        assertTrue(powerUpObject.isCollected());
    }

    @Test
    void testOnPlayerCollideOnlyOnce() {
        PowerUpManager mockManager = mock(PowerUpManager.class);
        when(mockScreen.getPowerUpManager()).thenReturn(mockManager);

        powerUpObject.onPlayerCollide();
        powerUpObject.onPlayerCollide();

        verify(mockPowerUp, times(1)).applyPowerUpEffect();
        verify(mockManager, times(1)).markForRemoval(powerUpObject);
        assertTrue(powerUpObject.isCollected());
    }

    @Test
    void testDisposeRemovesBody() {
        assertNotNull(powerUpObject.getBody());
        powerUpObject.dispose();
        assertNull(powerUpObject.getBody());
    }

    @Test
    void testUpdateCallsDisposeIfCollected() {
        powerUpObject.setCollected(true);
        powerUpObject.update(0.016f);

        assertNull(powerUpObject.getBody());
    }

    @Test
    void testSetBody() {
        Body newBody = world.createBody(new BodyDef());
        powerUpObject.setBody(newBody);
        assertEquals(newBody, powerUpObject.getBody());
    }
}
