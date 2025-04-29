package inf112.skeleton.model.character.enemy;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.badlogic.gdx.backends.headless.HeadlessApplicationConfiguration;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.EllipseMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

import inf112.skeleton.model.WorldModel;
import inf112.skeleton.model.character.controllable_characters.Player;
import inf112.skeleton.view.screen.GameScreen;

import static org.mockito.Mockito.*;

import java.util.List;

public class EnemyManagerTest {
    private GameScreen screen;
    private World world;
    private WorldModel worldModel;
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

        world = new World(new Vector2(0, -9.81f), false);
        when(screen.getWorld()).thenReturn(world);

        worldModel = new WorldModel(world);
        when(screen.getWorldModel()).thenReturn(worldModel);

        Player player = worldModel.createPlayer();
        when(screen.getPlayer()).thenReturn(player);

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

    @Test
    void testAddUnknownEnemy() {
        enemyManager.addEnemy("unknown", new Vector2(0, 0));

        assertTrue(enemyManager.getEnemies().isEmpty());
    }

    @Test
    void testLoadEnemiesFromMap() {
        TiledMap map = setUpEnemyMap();
        MapLayer enemyLayer = map.getLayers().get("Enemy");
        enemyManager.loadEnemiesFromMap(map);

        assertFalse(enemyManager.getEnemies().isEmpty());
        assertEquals(enemyManager.getEnemies().size(), 3);
    }

    private TiledMap setUpEnemyMap() {
        TiledMap map = new TiledMap();
        TiledMapTileLayer layer = new TiledMapTileLayer(10, 10, 16, 16);
        layer.setName("Enemy");

        // Add a black hole enemy to the map
        EllipseMapObject enemy1 = new EllipseMapObject(1, 1, 1, 1);
        enemy1.getProperties().put("x", 1f);
        enemy1.getProperties().put("y", 1f);
        EllipseMapObject enemy2 = new EllipseMapObject(2, 2, 1, 1);
        enemy2.getProperties().put("x", 2f);
        enemy2.getProperties().put("y", 2f);
        EllipseMapObject enemy3 = new EllipseMapObject(3, 3, 1, 1);
        enemy3.getProperties().put("x", 3f);
        enemy3.getProperties().put("y", 3f);

        layer.getObjects().add(enemy1);
        layer.getObjects().add(enemy2);
        layer.getObjects().add(enemy3);

        map.getLayers().add(layer);

        return map;
    }

    @Test
    void testUpdate() {
        enemyManager.addEnemy("black hole", new Vector2(0, 0));
        enemyManager.addEnemy("sentry", new Vector2(5, 3));

        List<SimpleEnemy> enemies = enemyManager.getEnemies();
        // Get positions of the enemies before update
        SimpleEnemy enemy1 = enemies.get(0);
        SimpleEnemy enemy2 = enemies.get(1);

        if (enemy1 instanceof BlackHole) {
        }
        if (enemy2 instanceof SentryEnemy) {
        }

        Vector2 blackHolePosBefore = enemy1.getCharacter().getPosition().cpy();
        float sentryShootingStateBefore = ((SentryEnemy) enemy2).getShootingState();

        // Update the enemy manager
        enemyManager.update(0.1f);
        worldModel.onStep(0.1f);

        // Check if black hole has moved
        Vector2 blackHolePosAfter = enemy1.getCharacter().getPosition().cpy();
        float sentryShootingStateAfter = ((SentryEnemy) enemy2).getShootingState();

        assertNotEquals(blackHolePosBefore, blackHolePosAfter);
        assertNotEquals(sentryShootingStateBefore, sentryShootingStateAfter);

        assertNotEquals(sentryShootingStateAfter, 0);
        for (int i = 0; i < 5; i++) {
            enemyManager.update(1f);
        }
        enemyManager.update(0.01f);
        float sentryShootingState = ((SentryEnemy) enemy2).getShootingState();
        System.out.println(sentryShootingState);
        assertEquals(sentryShootingState, -1);
    }
}
