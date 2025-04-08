package inf112.skeleton.model.items.door;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.badlogic.gdx.backends.headless.HeadlessApplicationConfiguration;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

import inf112.skeleton.model.character.Character;
import inf112.skeleton.model.character.CharacterAttributes;
import inf112.skeleton.view.screen.GameScreen;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;


class DoorManagerTest {

    private GameScreen screen;
    private TiledMap map;
    private World world;
    private Character character;
    private DoorManager manager;

    @BeforeAll
    static void initLibGDX() {
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
        world = new World(new Vector2(0, -9.81f), true);
        character = new Character(
                "test",
                new CharacterAttributes(10.0f, 1.0f, 2, 1.0f, 1.0f),
                new Vector2(1, 1),
                world
        );

        screen = mock(GameScreen.class);
        when(screen.getWorld()).thenReturn(world);

        map = new TiledMap();
        when(screen.getMap()).thenReturn(map);
    }

    @Test
    void testNoDoorsLoadedWhenLayerMissing() {
        manager = new DoorManager(screen);
        assertTrue(manager.getDoors().isEmpty(), "No doors should be loaded if Door layer is missing");
    }

    @Test
    void testDoorObjectsLoadedCorrectly() {
        MapLayer doorLayer = new MapLayer();
        doorLayer.setName("Door");

        RectangleMapObject doorObject = new RectangleMapObject(100, 100, 16, 16);
        doorLayer.getObjects().add(doorObject);

        map.getLayers().add(doorLayer);

        manager = new DoorManager(screen);

        List<DoorObject> doors = manager.getDoors();
        assertEquals(1, doors.size(), "Should load one door from map");
    }

    @Test
    void testUpdateCallsCheckPlayerAtMiddle() {
        DoorObject doorMock = mock(DoorObject.class);
        manager = new DoorManager(screen);
        manager.getDoors().add(doorMock);
        manager.update(1/60f);
        verify(doorMock, times(1)).checkPlayerAtMiddle();
    }
}