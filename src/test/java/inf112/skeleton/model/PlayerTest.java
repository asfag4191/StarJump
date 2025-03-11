package inf112.skeleton.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.badlogic.gdx.graphics.Pixmap;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

import inf112.skeleton.app.ExampleIT;
import inf112.skeleton.model.character.BlankApplication;
import inf112.skeleton.model.character.controllable_characters.Player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.badlogic.gdx.backends.headless.HeadlessApplicationConfiguration;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;


public class PlayerTest {

    private static HeadlessApplication headlessApplication;
        /**
     * Initialise a test headless application.
     */
    public static void initApplication() {
        //HeadlessApplication headlessApplication;
        if (headlessApplication == null) {
            HeadlessApplicationConfiguration config = new HeadlessApplicationConfiguration();
            headlessApplication = new HeadlessApplication(new BlankApplication(), config);
            assertNotNull(Gdx.files);
        }
}

    /**
     * Helper method for {@link Files#internal(String) Gdx.files.internal(String)}.
     * Returns a {@link FileHandle}.
     */
    public static @NonNull FileHandle internal(String path) {
        initApplication();
        FileHandle handle = Gdx.files.internal(path);
        if (handle == null) {
            throw new NullPointerException(String.format("internal(%s) returned null: %s", path, handle));
        }
        return handle;
}

    @BeforeEach
    public void setup() {
        // Mock necessary libGDX components
        Gdx.files = mock(com.badlogic.gdx.Files.class);
        FileHandle mockFileHandle = mock(FileHandle.class);
        Pixmap mockPixmap = mock(Pixmap.class);
        Texture mockTexture = mock(Texture.class);

        when(mockFileHandle.name()).thenReturn("star.png");
        when(mockFileHandle.file()).thenReturn(new java.io.File("star.png"));
        when(Gdx.files.internal(anyString())).thenReturn(mockFileHandle);

        // Mock the Pixmap to avoid it trying to read actual data
        when(mockFileHandle.readBytes()).thenReturn(new byte[10]);  // Return some dummy data
        when(new Pixmap(any(byte[].class), anyInt(), anyInt())).thenReturn(mockPixmap);
        when(new Texture(any(Pixmap.class))).thenReturn(mockTexture);
        }

 


    @Test
    public void testPosition() {
        World world = new World(new Vector2(0, -9.8f), true);
        Player player = new Player(new Vector2(50, 50), world);
        Vector2 newPosition = new Vector2(100, 100);
        player.setPosition(newPosition);
        assertEquals(newPosition, player.getPosition());
    }
}
