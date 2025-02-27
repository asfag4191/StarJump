package inf112.skeleton.model.colliders;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

public class RigidBody {
    protected final Body body;

    public RigidBody(World world, BodyDef bodyDef, Shape shape, boolean doDispose) {
        this.body = world.createBody(bodyDef);
        applyShape(this.body, shape, doDispose);
    }

    public RigidBody(World world, BodyDef bodyDef, Shape shape) {
        this(world, bodyDef, shape, true);
    }

    public void applyTorque(float torque) {
        this.body.applyTorque(torque, true);
    }

    public void applyForce(Vector2 direction) {
        this.body.applyForceToCenter(direction, true);
    }

    public void applyImpulse(Vector2 direction) {
        this.body.applyLinearImpulse(direction, this.body.getWorldCenter(), true);
    }

    public void setVelocity(Vector2 direction) {
        this.body.setLinearVelocity(direction);
    }

    public void setType(BodyDef.BodyType type) {
        this.body.setType(type);
    }

    public Transform getTransform() {
        return this.body.getTransform();
    }

    private static void applyShape(Body bdy, Shape shape, boolean doDispose) {
        bdy.createFixture(shape, 1);
        if (doDispose) shape.dispose();
    }
}
