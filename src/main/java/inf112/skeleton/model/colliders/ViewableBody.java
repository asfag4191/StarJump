package inf112.skeleton.model.colliders;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;
import inf112.skeleton.view.Renderable;

/**
 * A {@link RigidBody} extension that adds rendering capabilities using a {@link Sprite}.
 * This class allows a rigid body to be visually represented in a simulation by associating a texture to the body and rendering it at its current position and rotation.
 */
public class ViewableBody extends RigidBody implements Renderable {
    private Sprite sprite;

    /**
     * Constructs a new ViewableBody with the given parameters and a texture for rendering.
     * This constructor also applies the shape to the body, and optionally disposes of the shape after use.
     * @param world The world in which the body will exist.
     * @param bodyDef The definition of the body, which includes properties such as position, type, and other attributes.
     * @param shape The shape of the body, which defines its physical properties (e.g., collision shape, density).
     * @param texture The texture used to render the body.
     * @param doDispose A flag indicating whether to dispose of the shape after applying it to the body.
     *                  If true, the shape will be disposed to free up resources.
     */
    public ViewableBody(World world, BodyDef bodyDef, Shape shape, Texture texture, boolean doDispose) {
        super(world, bodyDef, shape, doDispose);
        sprite = new Sprite(texture);
        sprite.setOriginCenter();
    }

    /**
     * An overloaded constructor with {@code doDispose} set to true by default.
     */
    public ViewableBody(World world, BodyDef bodyDef, Shape shape, Texture texture) {
        this(world, bodyDef, shape, texture, true);
    }

    /**
     * Sets the size of the sprite based on the given dimensions.
     * @param size A vector representing the size (width, height) of the sprite.
     */
    protected void setSpriteSize(Vector2 size) {
        sprite.setSize(size.x, size.y);
    }

    /**
     * Sets the radius of the sprite based on the given value. The sprite will be scaled to fit a circle of the specified radius.
     * @param radius The radius to set for the sprite.
     */
    protected void setSpriteRadius(float radius) {
        sprite.setSize(radius * 2, radius * 2);
    }

    /**
     * Renders the body using its associated sprite, updating its position and rotation based on the body's current transform.
     * @param batch The {@link Batch} used to draw the sprite.
     */
    @Override
    public void render(Batch batch) {
        Vector2 bodyPos = this.getTransform().getPosition();
        float bodyDeg = this.getTransform().getRotation();
        sprite.setPosition(bodyPos.x - sprite.getWidth() / 2, bodyPos.y - sprite.getHeight() / 2);
        sprite.setRotation(bodyDeg);
        sprite.draw(batch);
    }
}
