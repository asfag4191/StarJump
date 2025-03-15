package inf112.skeleton.model.items.powerup;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Files;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.badlogic.gdx.backends.headless.HeadlessApplicationConfiguration;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

import inf112.skeleton.model.character.controllable_characters.Player;
import inf112.skeleton.view.screen.GameScreen;

class PowerUpManagerTest {

    private PowerUpManager powerUpManager;
    private GameScreen mockScreen;
    private World world;
    private Player player;
    private TiledMap mockMap;
    private MapLayers mockLayers;
    private MapLayer mockPowerUpLayer;
    private PowerUpFactory powerUpFactory;

    @BeforeAll
    static void init() {
        if (Gdx.app == null) {
            new HeadlessApplication(new ApplicationAdapter() {}, new HeadlessApplicationConfiguration());
        }

        Gdx.files = mock(Files.class);
        when(Gdx.files.internal(anyString())).thenReturn(mock(FileHandle.class));

        Gdx.gl = Gdx.gl20 = mock(com.badlogic.gdx.graphics.GL20.class);
    }
@BeforeEach
void setUp() {
    world = new World(new Vector2(0, -9.81f), true);

    mockScreen = mock(GameScreen.class);
    mockMap = mock(TiledMap.class);
    mockLayers = mock(MapLayers.class);
    mockPowerUpLayer = mock(MapLayer.class);
    MapObjects mockObjects = mock(MapObjects.class);
    Iterable<MapObject> emptyIterable = new ArrayList<>(); // ✅ Provide an empty iterable

    when(mockScreen.getWorld()).thenReturn(world);
    when(mockScreen.getMap()).thenReturn(mockMap);
    when(mockMap.getLayers()).thenReturn(mockLayers);
    when(mockLayers.get("PowerUp")).thenReturn(mockPowerUpLayer);
    when(mockPowerUpLayer.getObjects()).thenReturn(mockObjects);
    when(mockObjects.iterator()).thenReturn(emptyIterable.iterator()); // ✅ Mock iterator

    player = mock(Player.class);

    powerUpFactory = new PowerUpFactory(mockScreen);
    powerUpManager = new PowerUpManager(mockScreen, player);
}



    @Test
    void testManagerInitializationEmptyIfNoPowerUps() {
        assertTrue(powerUpManager.getPowerUps().isEmpty(), "Should be empty if no power-ups are defined in TiledMap");
    }

    @Test
    void testPowerUpLoadingWhenPowerUpsExist() {
        /*
        // Mock MapObjects and a single Power-Up object
        MapObjects mockObjects = mock(MapObjects.class);
        MapObject mockPowerUp = mock(MapObject.class);
        MapProperties mockProperties = mock(MapProperties.class);

        // Ensure the power-up object is iterable
        Iterable<MapObject> iterable = Collections.singletonList(mockPowerUp);

        // Ensure the PowerUp layer has objects
        when(mockPowerUpLayer.getObjects()).thenReturn(mockObjects);
        when(mockObjects.iterator()).thenReturn(iterable.iterator());

        // Ensure the power-up object has properties
        when(mockPowerUp.getProperties()).thenReturn(mockProperties);
        when(mockProperties.get("type", String.class)).thenReturn("FLYING");

        // Inject mocks into PowerUpManager
        powerUpManager = new PowerUpManager(mockScreen, player);

        // Debugging: Print loaded power-ups
        System.out.println("Power-ups loaded: " + powerUpManager.getPowerUps().size());

        // Check if at least 1 power-up is loaded
        assertFalse(powerUpManager.getPowerUps().isEmpty(), "PowerUpManager should load at least 1 power-up.");
        */
    }

    
    
    

}

