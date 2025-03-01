package inf112.skeleton.model.character;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;
import inf112.skeleton.model.colliders.RigidBody;

public class HumanoidBody extends RigidBody {
    private static final PolygonShape SHAPE = new PolygonShape();
    private static final BodyDef BODY_DEF = getBodyDef();

    public HumanoidBody(World world, Vector2 size) {
        super(world, getBodyDef(), getShape(size), false);
    }

    private static BodyDef getBodyDef() {
        BodyDef def = new BodyDef();
        def.type = BodyDef.BodyType.DynamicBody;
        def.fixedRotation = true;
        return def;
    }

    private static Shape getShape(Vector2 size) {
        SHAPE.setAsBox(size.x / 2, size.y / 2); // box2d takes in size from the center
        return SHAPE;
    }

}
