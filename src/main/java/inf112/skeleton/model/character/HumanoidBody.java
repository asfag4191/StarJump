package inf112.skeleton.model.character;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import inf112.skeleton.model.colliders.RigidBody;

public class HumanoidBody extends RigidBody {
    private static final PolygonShape SHAPE = new PolygonShape();
    private static final BodyDef BODY_DEF = getBodyDef();

    /**
     * Constructs a new HumanoidBody with the given size.
     *
     * @param world The world in which the body will exist.
     * @param size  The size of the humanoid body, used to define its fixture and shape.
     */
    public HumanoidBody(World world, Vector2 size) {
        super(world, BODY_DEF, getFixtureDef(size), false);
    }

    private static BodyDef getBodyDef() {
        BodyDef def = new BodyDef();
        def.type = BodyDef.BodyType.DynamicBody;
        def.fixedRotation = true;
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
