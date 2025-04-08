package inf112.skeleton.model.game_objects;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import inf112.skeleton.model.iUpdateable;
import inf112.skeleton.view.screen.GameScreen;

class EnemyManager implements iUpdateable {
    List<SimpleEnemy> enemies = new ArrayList<>();
    private GameScreen screen;

    public EnemyManager(GameScreen screen) {
        this.screen = screen;
    }

    public void addEnemy(SimpleEnemy enemy) {
        enemies.add(enemy);
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
        }
    }

}
