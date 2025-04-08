package inf112.skeleton.model.items.powerup;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.badlogic.gdx.backends.headless.HeadlessApplicationConfiguration;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.objects.EllipseMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;

import inf112.skeleton.model.character.Character;
import inf112.skeleton.model.character.CharacterAttributes;
import inf112.skeleton.view.screen.GameScreen;


/**
 * Test for PowerUpManager
 */
class PowerUpManagerTest {

    private PowerUpManager powerUpManager;
    private GameScreen screen;
    private World world;
    private Character character;

    @BeforeAll
    static void initGdx() {
        if (Gdx.app == null) {
            new HeadlessApplication(new ApplicationListener() {
                public void create() {}
                public void resize(int width, int height) {}
                public void render() {}
                public void pause() {}
                public void resume() {}
                public void dispose() {}
            }, new HeadlessApplicationConfiguration());
        }
        Gdx.gl = mock(GL20.class);
        Gdx.gl20 = Gdx.gl;
    }

    @BeforeEach
    void setUp() {
        screen = mock(GameScreen.class);
        world = new World(new Vector2(0, -9.81f), true);
        when(screen.getWorld()).thenReturn(world);

        TiledMap map = new TiledMap();
        MapLayer layer = new MapLayer();
        layer.setName("PowerUp");

        EllipseMapObject powerUpObj = new EllipseMapObject(100, 200, 16, 16);
        powerUpObj.getProperties().put("type", "FLYING");

        layer.getObjects().add(powerUpObj);
        map.getLayers().add(layer);

        when(screen.getMap()).thenReturn(map);

        character = new Character("test", new CharacterAttributes(10.0f, 1.0f, 2, 1.0f, 1.0f), new Vector2(1, 1), world);
        powerUpManager = new PowerUpManager(screen, character);
    }

    @Test
    void testNoPowerUpsWhenNoLayers() {
        TiledMap emptyMap = new TiledMap();
        when(screen.getMap()).thenReturn(emptyMap);

        powerUpManager = new PowerUpManager(screen, character);

        assertTrue(powerUpManager.getPowerUps().isEmpty(), "Expected no power-ups to be loaded");
    }

    @Test
    void testPowerUpsAreLoaded() {
        assertEquals(1, powerUpManager.getPowerUps().size(), "One power-up should be loaded from map");
    }

    @Test
    void testPowerUpIsMarkedForRemoval() {
        PowerUpObject powerUp = mock(PowerUpObject.class);
        powerUpManager.getPowerUps().add(powerUp);

        powerUpManager.markForRemoval(powerUp);

        assertTrue(powerUpManager.getRemovalQueue().contains(powerUp));
    }

    @Test
    void testUpdateRemovesPowerUpWithBody() {
        PowerUpObject powerUp = mock(PowerUpObject.class);
        Body body = world.createBody(new BodyDef());
        when(powerUp.getBody()).thenReturn(body);
        when(powerUp.getSprite()).thenReturn(mock(Sprite.class));

        powerUpManager.getPowerUps().add(powerUp);
        powerUpManager.markForRemoval(powerUp);
        powerUpManager.update(0.016f);

        assertFalse(powerUpManager.getPowerUps().contains(powerUp), "Power-up should be removed from active list");
        assertTrue(powerUpManager.getRemovalQueue().isEmpty(), "Removal queue should be cleared");
    }

    @Test
    void testUpdateRemovesPowerUpWithoutBody() {
        PowerUpObject powerUp = mock(PowerUpObject.class);
        when(powerUp.getBody()).thenReturn(null);
        when(powerUp.getSprite()).thenReturn(mock(Sprite.class));

        powerUpManager.getPowerUps().add(powerUp);
        powerUpManager.markForRemoval(powerUp);
        powerUpManager.update(0.016f);

        verify(powerUp.getSprite()).setAlpha(0f);
        assertFalse(powerUpManager.getPowerUps().contains(powerUp));
    }

    @Test
    void testRenderSkipsCollected() {
        SpriteBatch batch = mock(SpriteBatch.class);
        Sprite sprite = mock(Sprite.class);

        PowerUpObject collected = mock(PowerUpObject.class);
        when(collected.isCollected()).thenReturn(true);

        PowerUpObject visible = mock(PowerUpObject.class);
        when(visible.isCollected()).thenReturn(false);
        when(visible.getSprite()).thenReturn(sprite);

        powerUpManager.getPowerUps().clear();
        powerUpManager.getPowerUps().add(collected);
        powerUpManager.getPowerUps().add(visible);

        powerUpManager.render(batch);

        verify(sprite, times(1)).draw(batch);
    }
}
