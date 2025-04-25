package inf112.skeleton.model.game_objects;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.RayCastCallback;
import inf112.skeleton.model.character.Character;

public class SimpleEnemy{
    public final Character character;
    private Vector2 target;
    private float roamingRadius;
    private boolean roaming;
    private RayCastCallback rayCallback;

    public SimpleEnemy(Character character) {
        this.character = character;
        this.rayCallback = new RayCastCallback() {
            @Override
            public float reportRayFixture(Fixture fixture, Vector2 point, Vector2 normal, float fraction) {
                return 0;
            }
        };
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
