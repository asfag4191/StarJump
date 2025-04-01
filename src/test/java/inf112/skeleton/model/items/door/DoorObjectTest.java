package inf112.skeleton.model.items.door;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;

import inf112.skeleton.model.character.controllable_characters.Player;
import inf112.skeleton.view.screen.GameScreen;

/**
 * Test class for DoorObject
 */
class DoorObjectTest {
    private DoorObject doorObject;
    private GameScreen screen;
    private RectangleMapObject mapObject;
    private TiledMap map;
    private Player player;
    private World world;

    @BeforeAll
    static void initGdx() {
        if (Gdx.app == null) {
            new HeadlessApplication(new ApplicationAdapter() {}, new HeadlessApplicationConfiguration());
            Gdx.gl = mock(GL20.class);
            Gdx.gl20 = Gdx.gl;
            Gdx.files = mock(Files.class);
            when(Gdx.files.internal(anyString())).thenReturn(mock(FileHandle.class));
        }
    }

    @BeforeEach
    void setUp() {
        world = new World(new Vector2(0, -9.81f), true);

        // Mock Player
        player = mock(Player.class);
        var body = world.createBody(new BodyDef());
        when(player.getBody()).thenReturn(body);

        map = new TiledMap();
        screen = mock(GameScreen.class);

        when(screen.getMap()).thenReturn(map);
        when(screen.getWorld()).thenReturn(world);
        when(screen.getPlayer()).thenReturn(player);

        mapObject = new RectangleMapObject(32, 48, 16, 16);
        doorObject = new DoorObject(screen, mapObject, player);
    }
    @Test
    void testConstructorInitializesCorrectly() {
        assertNotNull(doorObject);
    }

    @Test
    void testCheckPlayerAtMiddleTriggersCorrectly() {
        float doorCenterX = (mapObject.getRectangle().x + mapObject.getRectangle().width / 2f) / 16f;
        float doorBottomY = mapObject.getRectangle().y / 16f;
    
        // Move player to the door center
        player.getBody().setTransform(new Vector2(doorCenterX, doorBottomY), 0);
        doorObject.checkPlayerAtMiddle();
        assertTrue(doorObject.isTriggered(), "Door should trigger when player is at middle");
    }

    @Test
    void testCheckPlayerAtMiddleDoesNotTriggerWhenFar() {
        player.getBody().setTransform(new Vector2(10f, 10f), 0);
        doorObject.checkPlayerAtMiddle();
        assertFalse(doorObject.isTriggered(), "Door should not trigger when player is far away");
    }
}