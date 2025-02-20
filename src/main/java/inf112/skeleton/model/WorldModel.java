package inf112.skeleton.model;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import inf112.skeleton.model.colliders.BoxCollider;

public class WorldModel{
    public World world;

    public WorldModel(Vector2 gravity, boolean doSleep) {
        world = new World(gravity, doSleep);
    }

    // game logic
    public void onStep(float dt) {
        world.step(dt, 3, 3);
    }

    public BoxCollider createTile(Vector2 position, Vector2 size) {
        BodyDef def = new BodyDef();
        def.type = BodyDef.BodyType.StaticBody;
        def.position.set(position);
        def.fixedRotation = true;
        return new BoxCollider(world, def, size);
    }

    public BoxCollider createTile(Vector2 size) {
        return createTile(new Vector2(0,0), size);
    }
}
