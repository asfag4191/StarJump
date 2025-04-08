package inf112.skeleton.model.game_objects;

import com.badlogic.gdx.math.Vector2;
import inf112.skeleton.model.character.Character;

public abstract class SimpleEnemy {
    public final Character character;
    private Vector2 target;

    public SimpleEnemy(Character character) {
        this.character = character;
    }

    public Vector2 getTarget() {
        return target;
    }

    public void setTarget(Vector2 point) {
        target = point;
    }

    public void update(float dt) {
    }

}
