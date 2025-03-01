package inf112.skeleton.model.colliders;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;

/**
 * A subclass of {@link ViewableBody} representing a box-shaped collider in the physics world.
 * This class uses a {@link PolygonShape} to define a rectangular collision area.
 */
public class BoxCollider extends ViewableBody {
    private static final PolygonShape SHAPE = new PolygonShape();

    /**
     * Constructs a new BoxCollider with the given parameters.
     * @param world The world in which the body will exist.
     * @param bodyDef The definition of the body, which includes properties such as position, type, and other attributes.
     * @param texture The texture used to render the box.
     * @param size The size (width, height) of the box collider.
     */
    public BoxCollider(World world, BodyDef bodyDef, Texture texture, Vector2 size) {
        super(world, bodyDef, getShape(size), texture, false);
        super.setSpriteSize(size);
    }

    private static Shape getShape(Vector2 size) {
        SHAPE.setAsBox(size.x / 2, size.y / 2);
        return SHAPE;
    }
}
