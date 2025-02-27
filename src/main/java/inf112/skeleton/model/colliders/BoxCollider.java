package inf112.skeleton.model.colliders;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;

public class BoxCollider extends ViewableBody {
    private static final PolygonShape shape = new PolygonShape();

    public BoxCollider(World world, BodyDef bodyDef, Texture texture, Vector2 size) {
        super(world, bodyDef, getShape(size), texture, false);
        super.setSpriteSize(size);
    }

    private static Shape getShape(Vector2 size) {
        shape.setAsBox(size.x / 2, size.y / 2);
        return shape;
    }
}
