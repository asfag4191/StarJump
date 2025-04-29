package inf112.skeleton.model.character.enemy;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.physics.box2d.World;

import inf112.skeleton.model.character.Character;

public abstract class SimpleEnemy {
    public final Character enemyCharacter;
    public World world;

    public SimpleEnemy(Character enemy, World world) {
        this.enemyCharacter = enemy;
        this.world = world;
        setupAnimation();
    }

    public void update(float dt) {
    }

    public void render(Batch batch, float dt) {
        enemyCharacter.render(batch, dt);
    }

    // This forces subclasses to define animation setup
    // every enemy need this as a override method
    protected abstract void setupAnimation();

}
