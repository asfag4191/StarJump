package inf112.skeleton.model.character.controllable_characters;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyFloat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

public class TestPlayer extends Player {

    public float gravityScale = 1f;
    public boolean collisionEnabled = true;
    public Vector2 linearVelocity = new Vector2(0, 0);
    public Body mockedBody;

    public TestPlayer(Vector2 size, World world) {
        super(size, mockWorldAndBody(world));
    }

    /**
     * Mocks World and Body completely before Player constructor runs.
     */
    private static World mockWorldAndBody(World world) {
        Body mockedBody = mock(Body.class);

        Fixture mockedFixture = mock(Fixture.class);
        Array<Fixture> fixtures = new Array<>();
        fixtures.add(mockedFixture);

        when(mockedBody.getFixtureList()).thenReturn(fixtures);
        when(mockedBody.createFixture(any(Shape.class), anyFloat())).thenReturn(mockedFixture);
        
        // Mocking body methods
        doNothing().when(mockedBody).setUserData(any());

        // Mock the World's createBody to immediately return mockedBody
        World mockedWorld = mock(World.class);
        when(mockedWorld.createBody(any(BodyDef.class))).thenReturn(mockedBody);

        // Mock LibGDX static calls here as well (Texture loading)
        Gdx.files = mock(Files.class);
        FileHandle mockedFileHandle = mock(FileHandle.class);
        when(Gdx.files.internal(any())).thenReturn(mockedFileHandle);
        when(mockedFileHandle.exists()).thenReturn(true);
        when(mockedFileHandle.name()).thenReturn("sprites/star.png");
        when(mockedFileHandle.extension()).thenReturn("png");

        Texture mockedTexture = mock(Texture.class);
        TextureRegion mockedTextureRegion = mock(TextureRegion.class);
        when(mockedTextureRegion.getTexture()).thenReturn(mockedTexture);

        return mockedWorld;
    }

    // Override methods Player uses to verify interactions
    @Override
    public void setCollisionEnabled(boolean enabled) {
        collisionEnabled = enabled;
    }

    @Override
    public Body getBody() {
        // now Player.getBody() will return our mockedBody directly from the start
        return mockedBody;
    }
}
