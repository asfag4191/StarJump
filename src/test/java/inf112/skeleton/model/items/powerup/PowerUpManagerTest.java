package inf112.skeleton.model.items.powerup;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.mock;
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
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.objects.EllipseMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

import inf112.skeleton.model.character.controllable_characters.Player;
import inf112.skeleton.view.screen.GameScreen;



/**
 * Test class for PowerUpManager
 */
class PowerUpManagerTest {
    private PowerUpManager powerUpManager;
    private GameScreen screen;
    private World world;
    private Player player;

@BeforeAll
static void initGdx() {
    if (Gdx.app == null) {
        new HeadlessApplication(new ApplicationListener() {
            @Override public void create() {}
            @Override public void resize(int width, int height) {}
            @Override public void render() {}
            @Override public void pause() {}
            @Override public void resume() {}
            @Override public void dispose() {}
        }, new HeadlessApplicationConfiguration());
    }
    Gdx.gl = Mockito.mock(GL20.class);
    Gdx.gl20 = Gdx.gl;
}
 
@BeforeEach
void setUp() {
    screen = mock(GameScreen.class);
    world = new World(new Vector2(0, -9.81f), true); 
    when(screen.getWorld()).thenReturn(world);

    // Create real TiledMap
    TiledMap map = new TiledMap();

    MapLayer powerUpLayer = new MapLayer();
    powerUpLayer.setName("PowerUp");
    powerUpLayer.getProperties().put("type", "FLYING");
    EllipseMapObject flyingObj = new EllipseMapObject(100, 200, 16, 16);
    flyingObj.getProperties().put("type", "FLYING");
    powerUpLayer.getObjects().add(flyingObj);
    map.getLayers().add(powerUpLayer);

    when(screen.getMap()).thenReturn(map);

    player = mock(Player.class); 
    powerUpManager = new PowerUpManager(screen, player);
}

@Test
void testNoPowerUps() {
    TiledMap map = new TiledMap();
    when(screen.getMap()).thenReturn(map);
    powerUpManager = new PowerUpManager(screen, player);
    assertTrue(powerUpManager.getPowerUps().isEmpty());
}

@Test
void testPowerUpLoading() {
    TiledMap map = new TiledMap();
    
    MapLayer powerUpLayer = new MapLayer();
    MapProperties powerUpProps = powerUpLayer.getProperties();
    powerUpProps.put("type", "FLYING");

    MapObjects powerUpObjects = powerUpLayer.getObjects();
    EllipseMapObject flyingObj = new EllipseMapObject(100, 200, 16, 16);
    flyingObj.getProperties().put("type", "FLYING");
    powerUpObjects.add(flyingObj);
    map.getLayers().add(powerUpLayer);
    powerUpLayer.setName("PowerUp");

    MapLayer diamondLayer = new MapLayer();
    MapProperties diamondProps = diamondLayer.getProperties();
    diamondProps.put("type", "DIAMOND");

    MapObjects diamondObjects = diamondLayer.getObjects();
    EllipseMapObject diamondObj = new EllipseMapObject(300, 400, 16, 16);
    diamondObjects.add(diamondObj);
    map.getLayers().add(diamondLayer);
    diamondLayer.setName("Diamonds");

    when(screen.getMap()).thenReturn(map);

    powerUpManager = new PowerUpManager(screen, player);
    assertEquals(2, powerUpManager.getPowerUps().size(), "Should load both flying and diamond power-ups");

    PowerUpObject first = powerUpManager.getPowerUps().get(0);
    PowerUpObject second = powerUpManager.getPowerUps().get(1);

    assertTrue(first.getPowerUp() instanceof FlyingPowerUp, "First should be FlyingPowerUp");
    assertTrue(second.getPowerUp() instanceof DiamondPowerUp, "Second should be DiamondPowerUp");
}

@Test
void testMarkForRemoval() {
    EllipseMapObject ellipseMapObject = new EllipseMapObject(100, 200, 16, 16);
    iPowerUp powerUpMock = mock(iPowerUp.class);
    Sprite mockSprite = new Sprite();
    when(powerUpMock.getSprite()).thenReturn(mockSprite);

    PowerUpObject powerUp = new PowerUpObject(screen, ellipseMapObject, powerUpMock, player, mockSprite);
    powerUpManager.markForRemoval(powerUp);

    assertEquals(1, powerUpManager.getRemovalQueue().size(), "Power-up should be added to removal queue.");
}
@Test
void testUpdateRemovesPowerUps() {
    powerUpManager.getPowerUps().clear();
    assertEquals(0, powerUpManager.getPowerUps().size(), "powerUps should be empty before adding any power-ups.");

    EllipseMapObject ellipseMapObject = new EllipseMapObject(100, 200, 16, 16);
    iPowerUp powerUpMock = mock(iPowerUp.class);
    Sprite mockSprite = new Sprite();
    when(powerUpMock.getSprite()).thenReturn(mockSprite);

    PowerUpObject powerUp = new PowerUpObject(screen, ellipseMapObject, powerUpMock, player, mockSprite);
    powerUp.setBody(world.createBody(new com.badlogic.gdx.physics.box2d.BodyDef()));

    powerUpManager.getPowerUps().add(powerUp);
    assertEquals(1, powerUpManager.getPowerUps().size(), "Should contain exactly 1 power-up.");

    powerUpManager.markForRemoval(powerUp);
    assertEquals(1, powerUpManager.getRemovalQueue().size(), "Power-up should be added to removal queue.");

    powerUpManager.update(0.016f);

    assertEquals(0, powerUpManager.getPowerUps().size(), "Power-ups should be removed after update.");
    assertTrue(powerUpManager.getRemovalQueue().isEmpty(), "Removal queue should be cleared after update.");
    }

@Test
void testRender() {
    Sprite sprite = mock(Sprite.class);
    SpriteBatch batch = mock(SpriteBatch.class);
    iPowerUp powerUpMock = mock(iPowerUp.class);

    when(powerUpMock.getSprite()).thenReturn(sprite);

    EllipseMapObject mapObject = new EllipseMapObject(100, 100, 16, 16);
    PowerUpObject visible = new PowerUpObject(screen, mapObject, powerUpMock, player, sprite);
    visible.setCollected(false);

    PowerUpObject hidden = new PowerUpObject(screen, mapObject, powerUpMock, player, sprite);
    hidden.setCollected(true);

    powerUpManager.getPowerUps().clear();
    powerUpManager.getPowerUps().add(visible);
    powerUpManager.getPowerUps().add(hidden);

    powerUpManager.render(batch);

    verify(sprite).draw(batch); 
    }

@Test
void testUpdateHandlesPowerUpWithoutBody() {
    powerUpManager.getPowerUps().clear();
    iPowerUp powerUpMock = mock(iPowerUp.class);
    Sprite sprite = mock(Sprite.class);
    when(powerUpMock.getSprite()).thenReturn(sprite);
    EllipseMapObject object = new EllipseMapObject(100, 200, 16, 16);

    PowerUpObject powerUp = new PowerUpObject(screen, object, powerUpMock, player, sprite);
    powerUp.setBody(null); // <-- this will skip the destroyBody block

    powerUpManager.getPowerUps().add(powerUp);
    powerUpManager.markForRemoval(powerUp);
    powerUpManager.update(0.016f);

    assertEquals(0, powerUpManager.getPowerUps().size());
    verify(sprite).setAlpha(0f);
    }
}