package inf112.skeleton.model.character;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import inf112.skeleton.model.colliders.RigidBody;

public abstract class HumanoidBody extends RigidBody {
    private static final PolygonShape SHAPE = new PolygonShape();

    /**
     * Constructs a new HumanoidBody with the given size.
     *
     * @param world The world in which the body will exist.
     * @param size  The size of the humanoid body, used to define its fixture and shape.
     */
    public HumanoidBody(World world, Vector2 size) {
        super(world, getBodyDef(), getFixtureDef(size), false);
    }

    private static BodyDef getBodyDef() {
        BodyDef def = new BodyDef();
        def.type = BodyDef.BodyType.DynamicBody;
        def.fixedRotation = true;
        def.linearDamping = 1;
        return def;
    }

    private static FixtureDef getFixtureDef(Vector2 size) {
        SHAPE.setAsBox(size.x / 2, size.y / 2); // box2d operates from the center
        FixtureDef fixDef = new FixtureDef();
        fixDef.shape = SHAPE;
        fixDef.density = 1;
        fixDef.friction = 1;
        return fixDef;
    }

}
