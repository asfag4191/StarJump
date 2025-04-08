package inf112.skeleton.model.game_objects;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

import inf112.skeleton.model.iUpdateable;
import inf112.skeleton.model.character.CharacterAttributes;
import inf112.skeleton.model.character.Character;
import inf112.skeleton.view.screen.GameScreen;

public class EnemyManager implements iUpdateable {
    List<SimpleEnemy> enemies = new ArrayList<>();
    private GameScreen screen;
    ShapeRenderer shapeRenderer = new ShapeRenderer();

    public EnemyManager(GameScreen screen) {
        this.screen = screen;
    }

    private void addEnemy(SimpleEnemy enemy) {
        enemies.add(enemy);
    }

    public void createEnemy() {

        CharacterAttributes attributes = new CharacterAttributes(
                100,
                10,
                2,
                3.5f,
                5);

        SimpleEnemy enemy = new SentryEnemy(new Character("enemy", attributes, new Vector2(1, 1), screen.getWorld()),
                screen.getPlayer());
        addEnemy(enemy);
        enemy.character.setPosition(new Vector2(3, 4));
        Texture texture1 = new Texture(Gdx.files.internal("sprites/star.png"));
        enemy.character.animator.addAnimation("idle", texture1, 1, 1, 0);
        enemy.character.animator.play("idle");
    }

    @Override
    public void update(float dt) {
        for (SimpleEnemy enemy : enemies) {
            enemy.update(dt);
        }
    }

    public void render(SpriteBatch batch, float dt) {
        for (SimpleEnemy enemy : enemies) {
            enemy.character.render(batch, dt);
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
        Vector2 enemyPos = sentry.character.getPosition();

        Vector2 playerPosCalc = enemyPos.cpy().add(playerDir.scl(10));
        shapeRenderer.line(sentry.character.getPosition(), playerPosCalc);

        shapeRenderer.end();
    }

}
