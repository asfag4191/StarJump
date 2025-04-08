package inf112.skeleton.model.game_objects;

import com.badlogic.gdx.math.Vector2;
import inf112.skeleton.model.character.Character;

public class SimpleEnemy {
    public final Character character;
    private Vector2 target;
    private float roamingRadius;
    private boolean roaming;

    public SimpleEnemy(Character character) {
        this.character = character;
    }

    public Vector2 getTarget() {
        return target;
    }

    public float getRoamingRadius() {
        return roamingRadius;
    }

    public boolean isRoaming() {
        return roaming;
    }

    public void setTarget(Vector2 point) {
        target = point;
    }

    public void setRoamingRadius(float radius) {
        roamingRadius = radius;
    }

    public void setRoaming(boolean on) {
        roaming = on;
    }

}
