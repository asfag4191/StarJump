package inf112.skeleton.model.colliders;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

public class BasePart {
    private final Body body;
    private Sprite skin;


    public BasePart(World world, BodyDef bodyDef, Shape shape, boolean doDispose) {
        this.body = world.createBody(bodyDef);
        applyShape(this.body, shape, doDispose);
    }

    public BasePart(World world, BodyDef bodyDef, Shape shape) {
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

    public void setPosition(Vector2 position, float angle) {
        this.body.setTransform(position, angle);
    }

    public void setPosition(Vector2 position) {
        this.body.setTransform(position, 0);
    }

    public void setType(BodyDef.BodyType type) {
        this.body.setType(type);
    }

    private static void applyShape(Body bdy, Shape shape, boolean doDispose) {
        bdy.createFixture(shape, 1);
        if (doDispose) shape.dispose();
    }
}
