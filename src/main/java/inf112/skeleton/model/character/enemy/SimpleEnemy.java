package inf112.skeleton.model.character.enemy;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

import inf112.skeleton.model.character.Character;

public abstract class SimpleEnemy {
    public final Character enemyCharacter;
    public World world;
    private Vector2 target;
    

    public SimpleEnemy(Character enemy, World world) {
        this.enemyCharacter = enemy;
        this.world = world;
        setupAnimation();
    }

    public Vector2 getTarget() {
        return target;
    }

    public void setTarget(Vector2 point) {
        target = point;
    }

    public void update(float dt) {
    }

    // This forces subclasses to define animation setup
    //every enemy need this as a override method
    protected abstract void setupAnimation();
   
}

