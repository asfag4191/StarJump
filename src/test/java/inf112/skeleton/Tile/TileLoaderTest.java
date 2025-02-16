package inf112.skeleton.Tile;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.badlogic.gdx.backends.headless.HeadlessApplicationConfiguration;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;

import inf112.skeleton.model.tiles.TileLoader;

public class TileLoaderTest {
    private static TileLoader tileLoader;

    /**
     * Initializes LibGDX in a headless mode before running tests.
     */
    @BeforeAll
    static void setUpBeforeAll() {
        HeadlessApplicationConfiguration config = new HeadlessApplicationConfiguration();

        // Initialize LibGDX with a headless application
        new HeadlessApplication(new ApplicationListener() {
            @Override public void create() {}
            @Override public void resize(int width, int height) {}
            @Override public void render() {}
            @Override public void pause() {}
            @Override public void resume() {}
            @Override public void dispose() {}
        }, config);

        // prevent null pointer exceptions
        Gdx.gl = Mockito.mock(GL20.class);
        Gdx.gl20 = Gdx.gl;
    }

    /**
     * Runs before each test to create a new TileLoader instance.
     */
    @BeforeEach
    void setUp() {
        tileLoader = new TileLoader();
    }


    /**
     * Tests that a valid tile ID returns a texture.
     */
    @Test
    void testGetTileTexture_validTile() {
        Texture texture = tileLoader.getTileTexture(1);
        assertNotNull(texture, "Tile ID 1 should return a texture.");
    }

    /**
     * Tests that an empty tile (ID 0) returns null.
     */
    @Test
    void testGetTileTexture_emptyTile() {
        Texture texture = tileLoader.getTileTexture(0);
        assertNull(texture, "Tile ID 0 should return null (transparent tile).");
    }

    /**
     * Tests that an invalid tile ID returns null.
     */
    @Test
    void testGetTileTexture_invalidTile() {
        Texture texture = tileLoader.getTileTexture(99);
        assertNull(texture, "Unknown Tile ID should return null.");
    }

    /**
     * Tests that the dispose method releases assets properly.
     */
    @Test
    void testDispose() {
        tileLoader.dispose();
        assertFalse(Gdx.app == null, "LibGDX should still be running after dispose.");
    }
}
