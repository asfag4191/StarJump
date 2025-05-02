package inf112.skeleton.model.character.enemy.projectile;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.utils.Disposable;

import inf112.skeleton.model.WorldModel;
import inf112.skeleton.model.iUpdateable;
import inf112.skeleton.model.colliders.RigidBody;
import inf112.skeleton.view.Animator;
import inf112.skeleton.view.Renderable;

public class Projectile extends RigidBody implements Disposable, iUpdateable, Renderable {
    private static final PolygonShape SHAPE = new PolygonShape();

    public final ProjectileAttributes attributes;
    public final Animator animator;
    private Texture bulletTex = new Texture(Gdx.files.internal("sprites/star.png"));

    private Vector2 size;

    private WorldModel worldModel;

    public Projectile(WorldModel worldModel, Vector2 startPos, ProjectileAttributes attributes, Vector2 size,
            Animator animator) {
        super(worldModel.world, getBodyDef(attributes.gravity()), getFixtureDef(size), false);
        this.attributes = attributes;
        this.setVelocity(attributes.velocity());
        this.size = size;
        this.setPosition(startPos);
        this.body.setUserData(this);
        this.worldModel = worldModel;
        this.animator = animator;
        this.animator.play("projectile");

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

    /**
     * Returns projectile attributes
     */
    public ProjectileAttributes getAttributes() {
        return this.attributes;
    }

    @Override
    public void dispose() {
        if (this.body != null) {
            // this.body.getWorld().destroyBody(this.body);
            this.worldModel.setBodyForRemove(this.body);
        }
        this.animator.dispose();
        this.bulletTex.dispose();

        System.out.println("Projectile disposed");
    }

    @Override
    public void update(float dt) {
        // move

        move(dt);
        // detect if hit anything
        // self destruct
        // maybe do damage to player
    }

    private void move(float dt) {
        this.setPosition(this.getBody().getPosition().add(this.attributes.velocity().scl(dt)));
    }

    @Override
    public void render(Batch batch, float dt) {
        Vector2 bodyPos = this.getTransform().getPosition();
        float bodyRad = this.getTransform().getRotation();
        TextureRegion nextFrame = this.animator.update(dt);

        if (nextFrame != null) {
            batch.draw(nextFrame,
                    bodyPos.x - size.x / 2,
                    bodyPos.y - size.y / 2,
                    size.x / 2, size.y / 2, // Origin
                    size.x, size.y, // Width and height
                    1f, 1f, // Scale (no scaling)
                    (float) Math.toDegrees(bodyRad));
        }
    }
}
