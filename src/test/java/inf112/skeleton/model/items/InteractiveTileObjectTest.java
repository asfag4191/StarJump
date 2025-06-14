package inf112.skeleton.model.items;

import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.badlogic.gdx.backends.headless.HeadlessApplicationConfiguration;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.EllipseMapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.World;

import inf112.skeleton.app.StarJump;
import inf112.skeleton.view.screen.GameScreen;

/**
 * Test class for InteractiveTileObject
 */
class InteractiveTileObjectTest {
    private TestInteractiveTileObject testObject;
    private GameScreen screen;
    private World world;
    private MapObject rectangleObject;
    private MapObject ellipseObject;

    @BeforeAll
    static void initLibGdx() {
        if (Gdx.app == null) {
            HeadlessApplicationConfiguration config = new HeadlessApplicationConfiguration();
            new HeadlessApplication(new com.badlogic.gdx.ApplicationAdapter() {}, config);
        }
    }

    @BeforeEach
    void setUp() {
        screen = mock(GameScreen.class);

        world = new World(new Vector2(0, -9.81f), true);
        when(screen.getWorld()).thenReturn(world);

        rectangleObject = new RectangleMapObject(50, 100, 16, 16);
        ellipseObject = new EllipseMapObject(30, 60, 10, 10);
    }

    @Test
    void testRectangleObjectInitialization() {
        testObject = new TestInteractiveTileObject(screen, rectangleObject);
        assertNotNull(testObject.getBody(), "Body should be created");
        assertEquals(50 / 16f + 0.5f, testObject.getBody().getPosition().x, 0.1);
        assertEquals(100 / 16f + 0.5f, testObject.getBody().getPosition().y, 0.1);
    }

    @Test
    void testEllipseObjectInitialization() {
        testObject = new TestInteractiveTileObject(screen, ellipseObject);
        assertNotNull(testObject.getBody(), "Body should be created");
        assertEquals(30 / 16f + 0.5f, testObject.getBody().getPosition().x, 0.2);
        assertEquals(60 / 16f + 0.5f, testObject.getBody().getPosition().y, 0.2);
    }

    @Test
    void testSetCategoryFilter() {
        testObject = new TestInteractiveTileObject(screen, rectangleObject);
        testObject.setCategoryFilter((short) 2);
        assertEquals(2, testObject.getFixture().getFilterData().categoryBits);
    }

    @Test
    void testUnsupportedMapObjectThrowsException() {
    MapObject mockObject = mock(MapObject.class); 
    IllegalArgumentException exception = org.junit.jupiter.api.Assertions.assertThrows(
            IllegalArgumentException.class,
            () -> new TestInteractiveTileObject(screen, mockObject)
    );
    assertEquals(
            "Unsupported object type: " + mockObject.getClass().getSimpleName(),
            exception.getMessage()
    );
}

    @Test
    void testDispose() {
        testObject = new TestInteractiveTileObject(screen, rectangleObject);
        testObject.dispose();
        assertEquals(0, world.getBodyCount(), "Body should be removed from world");
    }


    @AfterEach
    void tearDown() {
        world.dispose();
    }

    /**
     *  Concrete class to test InteractiveTileObject.
     *  Need this because InteractiveTileObject is abstract
     *  and cannot be instantiated directly.
     *  Acts as a minimal implementation of InteractiveTileObject 
     *  just for testing.
     */
    private static class TestInteractiveTileObject extends InteractiveTileObject {
        public TestInteractiveTileObject(GameScreen screen, MapObject object) {
            super(screen, object, StarJump.POWERUP);
        }

        @Override
        public void onPlayerCollide() {
            // No-op for testing
        }

        @Override
        public void update(float dt) {
            // No-op for testing
        }

        @Override
        public void dispose() {
            world.destroyBody(body);
        }

        public Body getBody() {
            return body;
        }

        public Fixture getFixture() {
            return fixture;
        }
    }
}
