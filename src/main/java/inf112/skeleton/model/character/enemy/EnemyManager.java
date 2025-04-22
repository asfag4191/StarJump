package inf112.skeleton.model.character.enemy;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
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
    }

    private void addEnemy(SimpleEnemy enemy) {
        enemies.add(enemy);
    }

    public void createEnemy() {
        SimpleEnemy sentryEnemy = enemyFactory.getNextSentryEnemy();
        SimpleEnemy blackHole = enemyFactory.getNextBlackHole();
        addEnemy(sentryEnemy);
        addEnemy(blackHole);
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
        // Draw a line from the enemy to the player
        Vector2 playerDir = sentry.getPlayerDirection();
        Vector2 enemyPos = sentry.enemyCharacter.getPosition();

        Vector2 playerPosCalc = enemyPos.cpy().add(playerDir.scl(10));
        shapeRenderer.line(sentry.enemyCharacter.getPosition(), playerPosCalc);

        shapeRenderer.end();
    }

}
