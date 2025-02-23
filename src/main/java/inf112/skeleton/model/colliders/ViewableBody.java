package inf112.skeleton.model.colliders;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;
import inf112.skeleton.view.Renderable;

public class ViewableBody extends RigidBody implements Renderable {
    Sprite sprite;

    public ViewableBody(World world, BodyDef bodyDef, Shape shape, Texture texture, boolean doDispose) {
        super(world, bodyDef, shape, doDispose);
        sprite = new Sprite(texture);
        sprite.setOriginCenter();
    }

    public ViewableBody(World world, BodyDef bodyDef, Shape shape, Texture texture) {
        this(world, bodyDef, shape, texture, true);
    }

    protected void setSpriteSize(Vector2 size) {
        sprite.setSize(size.x, size.y);
    }

    protected void setSpriteRadius(float radius) {
        sprite.setSize(radius * 2, radius * 2);
    }

    @Override
    public void render(Batch batch) {
        Vector2 bodyPos = this.getTransform().getPosition();
        float bodyDeg = this.getTransform().getRotation();
        sprite.setPosition(bodyPos.x - sprite.getWidth() / 2, bodyPos.y - sprite.getHeight() / 2);
        sprite.setRotation(bodyDeg);
        sprite.draw(batch);
    }
}
