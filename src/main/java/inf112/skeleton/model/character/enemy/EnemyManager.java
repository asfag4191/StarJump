package inf112.skeleton.model.character.enemy;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Vector2;

import inf112.skeleton.model.iUpdateable;
import inf112.skeleton.view.screen.GameScreen;

/**
 * This class creates <code>SimpleEnemies</code> and manages
 * updating and rendering of them
 */
public class EnemyManager implements iUpdateable {
    List<SimpleEnemy> enemies = new ArrayList<>();
    private GameScreen screen;
    ShapeRenderer shapeRenderer = new ShapeRenderer();
    private EnemyFactory enemyFactory;

    public EnemyManager(GameScreen screen) {
        this.screen = screen;
        this.enemyFactory = new EnemyFactory(screen);
        loadEnemiesFromMap();
    }

    private void addEnemy(SimpleEnemy enemy) {
        enemies.add(enemy);
    }

    public void createTestSentries() {
        List<Vector2> positions = List.of(new Vector2(3, 3), new Vector2(12, 10));
        for (Vector2 pos : positions) {
            SimpleEnemy sentryEnemy = enemyFactory.getNextSentryEnemy(pos);
            addEnemy(sentryEnemy);
        }
    }

    public void loadEnemiesFromMap() {
        TiledMap map = screen.getMap();
    
        var enemyLayer = map.getLayers().get("Enemy");
        if (enemyLayer == null) {
            System.err.println("No 'Enemies' layer found in Tiled map!");
            return;
        }
    
        List<Vector2> spawnPositions = new ArrayList<>();
    
        for (MapObject object : enemyLayer.getObjects()) {
            Float x = object.getProperties().get("x", Float.class);
            Float y = object.getProperties().get("y", Float.class);
    
            if (x != null && y != null) {
                Vector2 pos = new Vector2((x + 8f) / 16f, (y + 8f) / 16f);
                spawnPositions.add(pos);
            }
        }
         // Shuffle the enemies
    
        java.util.Collections.shuffle(spawnPositions);
        int enemiesToSpawn = Math.min(spawnPositions.size(), 3 + (int)(Math.random() * (spawnPositions.size() - 2)));
    
        for (int i = 0; i < enemiesToSpawn; i++) {
            Vector2 position = spawnPositions.get(i);
            SimpleEnemy enemy;
    
            // Randomly choose enemy type
            if (Math.random() < 0.5) {
                enemy = enemyFactory.getNextBlackHole(position);
            } else {
                enemy = enemyFactory.getNextSentryEnemy(position);
            }
    
            addEnemy(enemy);
        }
    }
    @Override
    public void update(float dt) {
        for (SimpleEnemy enemy : enemies) {
            enemy.update(dt);
        }
    }

    public void render(SpriteBatch batch, float dt) {
        for (SimpleEnemy enemy : enemies) {
            enemy.enemyCharacter.render(batch, dt);
            if (GameScreen.DEBUG_MODE) {
                debug(batch, enemy);
            }

        }
    }

    /*
     * DEBUG FOR ENEMIES
     */
    private void debug(SpriteBatch batch, SimpleEnemy enemy) {
        if (enemy instanceof SentryEnemy) {
            SentryEnemy sentry = (SentryEnemy) enemy;
            renderAim(batch, sentry);
        }
    }

    private void renderAim(SpriteBatch batch, SentryEnemy sentry) {

        // Render the player direction
        shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(1, 0, 0, 1); // Red color

        // Create a line showing enemy target direction
        if (sentry.rStart != null && sentry.rEnd != null) {
            shapeRenderer.line(sentry.rStart, sentry.rEnd);
        }
        // Draw a circle at the hit point
        if (sentry.hitPoint != null) {
            shapeRenderer.setColor(0, 1, 0, 1); // Green color
            shapeRenderer.circle(sentry.hitPoint.x, sentry.hitPoint.y, 0.2f);
        }

        shapeRenderer.end();
    }

}
