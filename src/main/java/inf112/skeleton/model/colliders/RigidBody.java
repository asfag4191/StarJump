package inf112.skeleton.model.colliders;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;

/**
 * A wrapper class of the Box2D {@link Body}
 */
public class RigidBody {
    protected final Body body;

    /**
     * Constructs a new RigidBody with the given parameters.
     *
     * @param world      The world in which the body will exist.
     * @param bodyDef    The definition of the body, which includes properties such as position, type, and other attributes.
     * @param fixtureDef The fixture definition, which includes the shape and physical properties (e.g., density, friction, restitution).
     * @param doDispose  A flag indicating whether to dispose of the shape inside the fixture after applying it to the body.
     *                   If true, the shape will be disposed to free up resources.
     */
    public RigidBody(World world, BodyDef bodyDef, FixtureDef fixtureDef, boolean doDispose) {
        this.body = world.createBody(bodyDef);
        applyFixture(this.body, fixtureDef, doDispose);
    }

    /**
     * An overloaded constructor with {@code doDispose} set to true by default.
     */
    public RigidBody(World world, BodyDef bodyDef, FixtureDef fixtureDef) {
        this(world, bodyDef, fixtureDef, true);
    }

    /**
     * Sets all fixtures of this body as either sensors or physical objects, depending on the argument.
     * Sensors detect collisions, but doesn't generate a physical response.
     *
     * @param isSensor {@code true} to enable sensor mode for all fixtures,
     *                 {@code false} to disable it and restore physical behavior.
     */
    public void setAsSensor(boolean isSensor) {
        Array<Fixture> fixtureList= this.body.getFixtureList();
        for (Fixture fixture : fixtureList) {
            if (fixture.getUserData() == "sensor") continue; // continue if a fixture is a pre-defined sensor.d
            fixture.setSensor(isSensor);
        }
    }

    /**
     * Applies a rotational force, a.k.a. torque to the body.
     *
     * @param torque the amount of torque to apply.
     */
    public void applyTorque(float torque) {
        this.body.applyTorque(torque, true);
    }

    /**
     * Applies a force to the body in the specified direction.
     * This force will not generate torque since its applied at the center of the body.
     *
     * @param direction a vector representing the direction and the magnitude of the force.
     */
    public void applyForce(Vector2 direction) {
        this.body.applyForceToCenter(direction, true);
    }

    /**
     * Applies an impulse to the body in the specified direction.
     * The impulse affects the linear velocity of the body.
     *
     * @param direction a vector representing the direction and the magnitude of the impulse.
     */
    public void applyImpulse(Vector2 direction) {
        this.body.applyLinearImpulse(direction, this.body.getWorldCenter(), true);
    }

    /**
     * Sets the gravity scale of the body. The gravity scale is a multiplier
     * that determines how much the gravity will affect the body.
     * @param scale the multiplier.
     */
    public void setGravityScale(float scale) {
        this.body.setGravityScale(scale);
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
     *
     * @param type The type of the body, defined by the {@link BodyDef.BodyType} enum.
     */

    public void setType(BodyDef.BodyType type) {
        this.body.setType(type);
    }

    /**
     * Sets the position and rotation of the body.
     *
     * @param position The new position of the body.
     * @param angle The new rotation angle in radians.
     */
    public void setTransform(Vector2 position, float angle) {
        this.body.setTransform(position, angle);
    }

    /**
     * Sets the position of the body.
     *
     * @param position The new position of the body.
     */
    public void setPosition(Vector2 position) {
        this.body.setTransform(position, this.body.getAngle());
    }

    /**
     * Wether or not the all the fixtures in the body are sensors.
     *
     * @return true if the entire body is a sensor, false otherwise.
     */
    public boolean isSensor() {
        boolean temp = true;
        for (Fixture fix : this.body.getFixtureList()) {
            if (!fix.isSensor()) {
                temp = false;
                break;
            }
        }
        return temp;
    }

    /**
     * Gets the angular velocity of the body.
     *
     * @return The angular velocity in radians per second.
     */
    public float getAngularVelocity() {
        return this.body.getAngularVelocity();
    }

    /**
     * Gets the gravity scale of the body. The gravity scale is a multiplier
     * that determines how much the gravity will affect the body.
     * @return the gravity scale.
     */
    public float getGravityScale() {
        return this.body.getGravityScale();
    };

    /**
     * Gets the linear velocity of the body.
     *
     * @return A {@link Vector2} representing the body's velocity.
     */
    public Vector2 getVelocity() {
        return this.body.getLinearVelocity();
    }

    /**
     * Gets the type of the body.
     *
     * @return The {@link BodyDef.BodyType} of the body.
     */
    public BodyDef.BodyType getType() {
        return this.body.getType();
    }

    /**
     * Retrieves the current transform of the body, which includes its position and rotation in the world.
     * This can be used to change the body position and rotation.
     *
     * @return The current {@link Transform} of the body, including both its position and rotation.
     */
    public Transform getTransform() {
        return this.body.getTransform();
    }

    private static void applyFixture(Body bdy, FixtureDef fixtureDef, boolean doDispose) {
        bdy.createFixture(fixtureDef);
        if (doDispose) fixtureDef.shape.dispose();
    }
}
