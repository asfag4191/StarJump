package inf112.skeleton.model.character.enemy.projectile;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Disposable;
import inf112.skeleton.model.colliders.RigidBody;
import inf112.skeleton.view.Animator;

class Projectile extends RigidBody implements Disposable {
    private static final PolygonShape SHAPE = new PolygonShape();

    public final ProjectileAttributes attributes;
    public final Animator animator = new Animator();

    public Projectile(World world, ProjectileAttributes attributes, Vector2 size) {
        super(world,getBodyDef(attributes.gravity()), getFixtureDef(size), false);
        this.attributes = attributes;
        this.setVelocity(attributes.velocity());
    }

    private static BodyDef getBodyDef(boolean gravity) {
        BodyDef def = new BodyDef();
        def.type = BodyDef.BodyType.DynamicBody;
        def.fixedRotation = true;
        def.linearDamping = 0;
        def.gravityScale = gravity ? 1 : 0;
        def.bullet = true;
        return def;
    }

    private static FixtureDef getFixtureDef(Vector2 size) {
        SHAPE.setAsBox(size.x / 2, size.y / 2);
        FixtureDef fixDef = new FixtureDef();
        fixDef.shape = SHAPE;
        fixDef.density = 1;
        fixDef.friction = 1;
        fixDef.isSensor = true;
        return fixDef;
    }

    @Override
    public void dispose() {
        if (this.body != null) {
            this.body.getWorld().destroyBody(this.body);
        }
    }
}
