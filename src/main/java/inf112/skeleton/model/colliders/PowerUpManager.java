package inf112.skeleton.model.colliders;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

public class PowerUpManager {
    private final World world;
    private final Array<Body> bodiesToRemove = new Array<>();

    public PowerUpManager(World world) {
        this.world = world;
    }

    public void addPowerUpToRemove(Body body) {
        bodiesToRemove.add(body);
    }

    public void update(float dt) {
        // Remove collected power-ups from the world
        for (Body body : bodiesToRemove) {
            world.destroyBody(body);
        }
        bodiesToRemove.clear();
    }
}

