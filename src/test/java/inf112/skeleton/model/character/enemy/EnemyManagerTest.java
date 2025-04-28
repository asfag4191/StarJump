package inf112.skeleton.model.character.enemy;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.badlogic.gdx.backends.headless.HeadlessApplicationConfiguration;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

import inf112.skeleton.model.WorldModel;
import inf112.skeleton.model.character.controllable_characters.Player;
import inf112.skeleton.view.screen.GameScreen;
import inf112.skeleton.model.character.enemy.EnemyManager;

import static org.mockito.Mockito.*;

public class EnemyManagerTest {
    private GameScreen screen;
    private World world;
    private Character character;
    private WorldModel worldModel;
    private Player player;
    private EnemyManager enemyManager;

    @BeforeAll
    static void initGdx() {
        if (Gdx.app == null) {
            new HeadlessApplication(new ApplicationListener() {
                public void create() {
                }

                public void resize(int width, int height) {
                }

                public void render() {
                }

                public void pause() {
                }

                public void resume() {
                }

                public void dispose() {
                }
            }, new HeadlessApplicationConfiguration());
        }
        Gdx.gl = mock(GL20.class);
        Gdx.gl20 = Gdx.gl;
    }

    @BeforeEach
    void setUp() {
        screen = mock(GameScreen.class);

        world = new World(new Vector2(0, -9.81f), true);
        when(screen.getWorld()).thenReturn(world);

        worldModel = new WorldModel(world);
        when(screen.getWorldModel()).thenReturn(worldModel);

        player = worldModel.createPlayer();

        enemyManager = new EnemyManager(screen);
    }

    @Test
    void testEnemyManagerInitialization() {
        // Test if the EnemyManager is initialized correctly
        assertNotNull(enemyManager);
        assertTrue(enemyManager.getEnemies().isEmpty());
    }

    @Test
    void testAddBlackHole() {
        enemyManager.addEnemy("black hole", new Vector2(0, 0));

        assertFalse(enemyManager.getEnemies().isEmpty());
        assertEquals(enemyManager.getEnemies().size(), 1);
    }

    @Test
    void testAddSentry() {
        enemyManager.addEnemy("sentry", new Vector2(0, 0));

        assertFalse(enemyManager.getEnemies().isEmpty());
        assertEquals(enemyManager.getEnemies().size(), 1);
    }

}
