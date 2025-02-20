package inf112.skeleton.model.colliders;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;

public class RigidBody extends BasePart{
    public RigidBody(World world, BodyDef bodyDef, Shape shape, boolean doDispose) {
        super(world, bodyDef, shape, doDispose);
    }
}
