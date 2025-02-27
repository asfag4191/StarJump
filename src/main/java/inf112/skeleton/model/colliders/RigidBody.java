package inf112.skeleton.model.colliders;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

/**
 * A wrapper class of the Box2D {@link Body}
 */
public class RigidBody {
    protected final Body body;

    /**
     * Constructs a new RigidBody with the given parameters.
     * @param world The world in which the body will exist.
     * @param bodyDef The definition of the body, which includes properties such as position, type, and other attributes.
     * @param shape The shape of the body, which defines its physical properties (e.g., collision shape, density).
     * @param doDispose A flag indicating whether to dispose of the shape after applying it to the body.
     *                  If true, the shape will be disposed to free up resources.
     */
    public RigidBody(World world, BodyDef bodyDef, Shape shape, boolean doDispose) {
        this.body = world.createBody(bodyDef);
        applyShape(this.body, shape, doDispose);
    }

    /**
     * An overloaded constructor with {@code doDispose} set to true by default.
     */
    public RigidBody(World world, BodyDef bodyDef, Shape shape) {
        this(world, bodyDef, shape, true);
    }

    /**
     * Applies a rotational force, a.k.a. torque to the body.
     * @param torque the amount of torque to apply.
     */
    public void applyTorque(float torque) {
        this.body.applyTorque(torque, true);
    }

    /**
     * Applies a force to the body in the specified direction.
     * This force will not generate torque since its applied at the center of the body.
     * @param direction a vector representing the direction and the magnitude of the force.
     */
    public void applyForce(Vector2 direction) {
        this.body.applyForceToCenter(direction, true);
    }

    /**
     * Applies an impulse to the body in the specified direction.
     * The impulse affects the linear velocity of the body.
     * @param direction a vector representing the direction and the magnitude of the impulse.
     */
    public void applyImpulse(Vector2 direction) {
        this.body.applyLinearImpulse(direction, this.body.getWorldCenter(), true);
    }

    /**
     * Sets the linear velocity of the body in the specified direction.
     * This method directly modifies the body's current velocity.
     *
     * @param direction a vector representing the direction and the magnitude of the velocity.
     */
    public void setVelocity(Vector2 direction) {
        this.body.setLinearVelocity(direction);
    }

    /**
     * Sets the type of the body, which determines its behavior in the physics simulation.
     * The body type can be static, dynamic, or kinematic, each affecting how the body responds to forces and collisions.
     * @param type The type of the body, defined by the {@link BodyDef.BodyType} enum.
     */

    public void setType(BodyDef.BodyType type) {
        this.body.setType(type);
    }

    /**
     * Retrieves the current transform of the body, which includes its position and rotation in the world.
     * This can be used to change the body position and rotation.
     * @return The current {@link Transform} of the body, including both its position and rotation.
     */
    public Transform getTransform() {
        return this.body.getTransform();
    }

    private static void applyShape(Body bdy, Shape shape, boolean doDispose) {
        bdy.createFixture(shape, 1);
        if (doDispose) shape.dispose();
    }
}
