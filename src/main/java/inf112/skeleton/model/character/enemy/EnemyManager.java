package inf112.skeleton.model.character.enemy;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Vector2;

import inf112.skeleton.model.iUpdateable;
import inf112.skeleton.view.Renderable;
import inf112.skeleton.view.screen.GameScreen;

/**
 * This class creates <code>SimpleEnemies</code> and manages
 * updating and rendering of them
 */
public class EnemyManager implements iUpdateable, Renderable {
    List<SimpleEnemy> enemies = new ArrayList<>();
    private GameScreen screen;
    private EnemyFactory enemyFactory;

    public EnemyManager(GameScreen screen) {
        this.screen = screen;
        this.enemyFactory = new EnemyFactory(screen);
    }

    private void addEnemy(SimpleEnemy enemy) {
        enemies.add(enemy);
    }

    /**
     * Generate enemy of type <code>enemyType</code> at <code>position</code>
     *
     * @param enemyType type of enemy to generate
     *                  possible types: "black hole", "sentry"
     * @param position  position of enemy
     */
    public void addEnemy(String enemyType, Vector2 position) {
        SimpleEnemy enemy = null;

        switch (enemyType) {
            case "black hole":
                enemy = enemyFactory.getNextBlackHole(position);
                break;
            case "sentry":
                enemy = enemyFactory.getNextSentryEnemy(position);
                break;
            default:
                System.err.println("Unknown enemy type: " + enemyType);
                return;
        }
        addEnemy(enemy);
    }

    /**
     * Load enemies from the Tiled map
     *
     * @param map the TiledMap to load enemies from
     */
    public void loadEnemiesFromMap(TiledMap map) {

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
        int enemiesToSpawn = Math.min(spawnPositions.size(), 3 + (int) (Math.random() * (spawnPositions.size() - 2)));

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
        for (int i = 0; i < enemies.size(); i++) {
            SimpleEnemy enemy = enemies.get(i);
            enemy.update(dt);

            // removed the enemy if jumped on
            if (enemy instanceof BlackHole blackHole && blackHole.shouldBeRemoved()) {
                blackHole.getCharacter().getBody().getWorld().destroyBody(blackHole.getCharacter().getBody());
                enemies.remove(i);
                i--;
            }
        }
    }

    @Override
    public void render(Batch batch, float dt) {
        for (SimpleEnemy enemy : enemies) {
            enemy.render(batch, dt);
        }
    }

    /**
     * @return the list of enemies
     */
    public List<SimpleEnemy> getEnemies() {
        return enemies;
    }

}
