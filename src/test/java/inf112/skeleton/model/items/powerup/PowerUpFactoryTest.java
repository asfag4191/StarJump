package inf112.skeleton.model.items.powerup;

import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.badlogic.gdx.backends.headless.HeadlessApplicationConfiguration;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

import inf112.skeleton.model.character.controllable_characters.Player;
import inf112.skeleton.view.screen.GameScreen;

class PowerUpFactoryTest {
    private PowerUpFactory factory;
    private Player player;
    private GameScreen screen;
    private World world;

    private GL20 mockGL;

    @BeforeAll
    static void initLibGdx() {
        if (Gdx.app == null) {
            HeadlessApplicationConfiguration config = new HeadlessApplicationConfiguration();
            new HeadlessApplication(new com.badlogic.gdx.ApplicationAdapter(){}, config);
        }
    }

    @BeforeEach
    void setUp() {
        Gdx.gl = Gdx.gl20 = Mockito.mock(com.badlogic.gdx.graphics.GL20.class);

        world = new World(new Vector2(0, -9.81f), true);
        GameScreen mockScreen = Mockito.mock(GameScreen.class);
        Mockito.when(mockScreen.getWorld()).thenReturn(world);

        player = new Player(new Vector2(1, 1), world);
        factory = new PowerUpFactory(mockScreen);
    }

    @Test
    void createFlyingPowerUpTest() {
        AbstractPowerUp powerUp = factory.createFlyingPowerUp(
                PowerUpEnum.FLYING, 
                player, 
                new Vector2(5, 5)
        );

        assertNotNull(powerUp);
        assertTrue(powerUp instanceof FlyingPowerUp);
        assertNotNull(powerUp.getSprite());
        assertEquals(5, powerUp.getSprite().getX());
        assertEquals(5, powerUp.getSprite().getY());
    }

    @AfterEach
    void tearDown() {
        world.dispose();
        Gdx.gl = null;
        Gdx.gl20 = null;
    }
}
