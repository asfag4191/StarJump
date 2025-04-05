//package inf112.skeleton.model.items.door;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.times;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//
//import com.badlogic.gdx.ApplicationAdapter;
//import com.badlogic.gdx.Gdx;
//import com.badlogic.gdx.backends.headless.HeadlessApplication;
//import com.badlogic.gdx.backends.headless.HeadlessApplicationConfiguration;
//import com.badlogic.gdx.graphics.GL20;
//import com.badlogic.gdx.maps.MapLayer;
//import com.badlogic.gdx.maps.objects.RectangleMapObject;
//import com.badlogic.gdx.maps.tiled.TiledMap;
//import com.badlogic.gdx.math.Vector2;
//import com.badlogic.gdx.physics.box2d.World;
//
//import inf112.skeleton.model.character.controllable_characters.Player;
//import inf112.skeleton.view.screen.GameScreen;
//
///**
// * Test class for DoorManager, minimized mocking
// */
//class DoorManagerTest {
//    private GameScreen screen;
//    private TiledMap map;
//    private World world;
//    private Player player;
//    private DoorManager manager;
//
//    @BeforeAll
//    static void initGdx() {
//        if (Gdx.app == null) {
//            new HeadlessApplication(new ApplicationAdapter() {}, new HeadlessApplicationConfiguration());
//        }
//        Gdx.gl = mock(GL20.class);
//        Gdx.gl20 = Gdx.gl;
//    }
//
//    @BeforeEach
//    void setUp() {
//        world = new World(new Vector2(0, -9.81f), true);
//        player = new Player(new Vector2(1, 1), world);
//
//        map = new TiledMap();
//        screen = mock(GameScreen.class);
//
//        when(screen.getMap()).thenReturn(map);
//        when(screen.getWorld()).thenReturn(world);
//        when(screen.getPlayer()).thenReturn(player);
//    }
//
//    @Test
//    void testNoDoorsLoadedWhenLayerMissing() {
//        manager = new DoorManager(screen);
//        assertEquals(0, manager.getDoors().size(), "No doors should be loaded when layer is missing");
//    }
//
//    @Test
//    void testDoorsLoaded() {
//        MapLayer doorLayer = new MapLayer();
//        RectangleMapObject doorObject = new RectangleMapObject(50, 100, 16, 16);
//        doorLayer.getObjects().add(doorObject);
//        map.getLayers().add(doorLayer);
//        map.getLayers().get(map.getLayers().size() - 1).setName("Door");
//
//        manager = new DoorManager(screen);
//
//        assertEquals(1, manager.getDoors().size(), "One door should be loaded from map");
//    }
//
//    @Test
//    void testUpdateCallsCheckPlayerAtMiddle() {
//        DoorObject door = mock(DoorObject.class);
//
//        manager = new DoorManager(screen);
//        manager.getDoors().add(door);
//
//        manager.update(0.016f);
//
//        verify(door, times(1)).checkPlayerAtMiddle();
//    }
//}