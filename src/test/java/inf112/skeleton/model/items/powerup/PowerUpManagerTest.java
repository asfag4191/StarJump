package inf112.skeleton.model.items.powerup;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.badlogic.gdx.backends.headless.HeadlessApplicationConfiguration;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
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
    private TiledMap map;
    private MapLayer powerUpLayer;
    private MapObjects mapObjects;
    private PowerUpObject mockPowerUp;



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
    world = new World(new Vector2(0, -9.81f), true); // Create a real physics world
    map = mock(TiledMap.class);
    powerUpLayer = mock(MapLayer.class);
    mapObjects = mock(MapObjects.class);
    player = mock(Player.class);

    when(screen.getWorld()).thenReturn(world);
    when(screen.getMap()).thenReturn(map);

    MapLayers mapLayers = mock(MapLayers.class);
    when(map.getLayers()).thenReturn(mapLayers);
    when(mapLayers.get("PowerUp")).thenReturn(powerUpLayer);
    when(powerUpLayer.getObjects()).thenReturn(mapObjects);
    when(mapObjects.iterator()).thenReturn(List.of(createMockPowerUp(100, 200)).iterator());

    powerUpManager = new PowerUpManager(screen, player);
  
}

    private MapObject createMockPowerUp(float x, float y) {
    EllipseMapObject ellipseMapObject = new EllipseMapObject(x, y, 16, 16);
    
    ellipseMapObject.getProperties().put("type", "FLYING");

    return ellipseMapObject;
}
    

@Test
void testNoPowerUps() {
    when(mapObjects.iterator()).thenReturn(List.<MapObject>of().iterator()); // No power-ups
    powerUpManager = new PowerUpManager(screen, player);
    assertTrue(powerUpManager.getPowerUps().isEmpty(), "Should be empty if no power-ups are defined in TiledMap");
}


@Test
void testPowerUpLoading() {
    when(mapObjects.iterator()).thenReturn(List.of(createMockPowerUp(100, 200)).iterator());
    powerUpManager = new PowerUpManager(screen, player);

    int expectedPowerUps = 1; 

    assertFalse(powerUpManager.getPowerUps().isEmpty(), "PowerUpManager should load at least 1 power-up.");
    assertEquals(expectedPowerUps, powerUpManager.getPowerUps().size(), 
        "PowerUpManager should have exactly " + expectedPowerUps + " power-up.");

    float expectedX = 100 / 16f;
    float expectedY = ((200 + 16 / 2f) / 16f) - (16 / 2f) / 16f; // Adjusted for sprite offset
    
    PowerUpObject loadedPowerUp = powerUpManager.getPowerUps().get(0);
    float actualX = loadedPowerUp.getSprite().getX();
    float actualY = loadedPowerUp.getSprite().getY();

    float delta = 0.1f;
    assertEquals(expectedX, actualX, delta, "Power-up X position should be correct.");
    assertEquals(expectedY, actualY, delta, "Power-up Y position should be correct.");
}


@Test
void testMarkForRemoval() {
    powerUpManager.markForRemoval(mockPowerUp);
    assertEquals(1, powerUpManager.getRemovalQueue().size(), "Power-up should be added to removal queue.");
}
@Test
void testUpdateRemovesPowerUps() {
    powerUpManager.getPowerUps().clear();
    assertEquals(0, powerUpManager.getPowerUps().size(), "powerUps should be empty before adding any power-ups.");
    
    EllipseMapObject ellipseMapObject = new EllipseMapObject(100, 200, 16, 16);
    AbstractPowerUp powerUpMock = mock(AbstractPowerUp.class);
    Sprite mockSprite = new Sprite();
    when(powerUpMock.getSprite()).thenReturn(mockSprite); 
    
    PowerUpObject realPowerUp = new PowerUpObject(screen, ellipseMapObject, powerUpMock, player, mockSprite);
        
    powerUpManager.getPowerUps().add(realPowerUp);
    assertEquals(1, powerUpManager.getPowerUps().size(), "powerUps should contain exactly 1 power-up.");
    powerUpManager.markForRemoval(realPowerUp);
    
    assertEquals(1, powerUpManager.getRemovalQueue().size(), "Power-up should be added to removal queue.");

    powerUpManager.update(0.016f); 

    assertEquals(0, powerUpManager.getPowerUps().size(), "Power-ups should be removed after update.");
    assertTrue(powerUpManager.getRemovalQueue().isEmpty(), "Removal queue should be cleared after update.");
}
}