package inf112.skeleton.model.character.enemy;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.RayCastCallback;

import inf112.skeleton.model.WorldModel;
import inf112.skeleton.model.character.Character;
import inf112.skeleton.model.character.controllable_characters.Player;
import inf112.skeleton.model.character.enemy.projectile.Projectile;
import inf112.skeleton.model.character.enemy.projectile.ProjectileAttributes;
import inf112.skeleton.view.Animator;

public class SentryEnemy extends SimpleEnemy implements iStationaryEnemy {
    private Player player;
    private float RANGE = 10f;
    final boolean hitPlayer = false;
    private static final float SHOOTING_DELAY = 5;
    private static final float BULLET_SPEED = 2f;
    private float shootingState = 0;
    private float shootingDelay = 0;
    private Texture bulletTex;
    private WorldModel worldModel;
    private Animator bulletAnim;
    private Texture cannonStandTex;

    public SentryEnemy(Character character, Player player, WorldModel worldModel) {
        super(character);
        this.player = player;
        setupAnimation();
        this.bulletTex = new Texture(Gdx.files.internal("sprites/simple_blackhole.png"));
        this.worldModel = worldModel;
        this.cannonStandTex = new Texture(Gdx.files.internal("sprites/cannon/cannon_stand.png"));
        this.bulletAnim = new Animator();
        this.bulletAnim.addAnimation("projectile", this.bulletTex, 1, 1, 0);
    }

    @Override
    public void shoot(Vector2 target, float bulletSpeed) {
        this.shootingState = -1f;
        this.shootingDelay = 1f;

        Projectile proj = new Projectile(worldModel, getCharacter().getPosition(),
                new ProjectileAttributes(target.nor().scl(bulletSpeed), 1f, 1f, false),
                new Vector2(0.5f, 0.5f), bulletAnim);

        proj.animator.addAnimation("projectile", bulletTex, 1, 1, 0);
        proj.animator.play("projectile");
        this.worldModel.addViewableObject(proj);

    }

    @Override
    public boolean seesTarget(Vector2 playerPos) {
        Vector2 thisPos = getCharacter().getPosition();
        Vector2 rayStart = thisPos.cpy(); // start of the ray
        Vector2 rayEnd = playerPos.cpy(); // end of the ray

        final Vector2[] hitPoint = new Vector2[1];
        final Boolean[] seesPlayer = new Boolean[1];
        seesPlayer[0] = true;
        RayCastCallback callback = new RayCastCallback() {
            @Override
            public float reportRayFixture(Fixture fixture, Vector2 point, Vector2 normal, float fraction) {
                // System.out.println("Ray hit: " + fixture.getBody().getUserData());
                if ("ground".equals(fixture.getBody().getUserData())) {
                    hitPoint[0] = point.cpy();
                    seesPlayer[0] = false;
                    return -1;
                }
                return 1;
            };
        };
        worldModel.world.rayCast(callback, rayStart, rayEnd);

        return seesPlayer[0];
    }

    public void update(float dt) {
        super.update(dt);

        // No need to do the calculations if player is not in range
        if (getDistanceToPlayer() < RANGE) {
            Vector2 playerDirection = getPlayerDirection();
            this.getCharacter().setTransform(this.getCharacter().getPosition(),
                    playerDirection.angleRad() - 3.14f / 2f);
        }

        if (this.shootingState >= SHOOTING_DELAY) {
            this.shoot(getPlayerDirection(), BULLET_SPEED);
        }

        // If player is in range we want to start the timer for shooting
        if (this.shootingState >= 0 && seesTarget(player.character.getPosition())) {
            this.shootingState += dt;
        } else if (shootingState > 0) {
            // If player is not in range we want to reset the timer
            this.shootingState = 0;
        }

        // If we have shot, we want to wait a bit before we can shoot again
        if (this.shootingState == -1f) {
            shootingDelay -= dt;
            if (shootingDelay <= 0) {
                shootingState = 0;
            }
        }
    }

    // Will be private when rest is implemented
    public Vector2 getPlayerDirection() {
        Vector2 playerPosition = this.player.character.getPosition();
        Vector2 enemyPosition = this.getCharacter().getPosition();

        return playerPosition.sub(enemyPosition).nor();
    }

    /**
     * Returns the distance to the player.
     */
    public float getDistanceToPlayer() {
        Vector2 playerPosition = this.player.character.getPosition();
        Vector2 enemyPosition = getCharacter().getPosition();

        return playerPosition.dst(enemyPosition);
    }

    @Override
    protected void setupAnimation() {
        getCharacter().animator.clearAnimations();
        Texture tex = new Texture(Gdx.files.internal("sprites/cannon/cannon_barrel.png"));
        getCharacter().animator.addAnimation("idle", tex, 1, 1, 0);
        getCharacter().animator.play("idle");
    }

    private void renderCannonStand(Batch batch) {
        batch.draw(cannonStandTex, getCharacter().getPosition().x - 0.5f, getCharacter().getPosition().y - 0.5f,
                1.5f, 1.5f);
    }

    @Override
    public void render(Batch batch, float dt) {
        super.render(batch, dt);
        renderCannonStand(batch);
    }

    /**
     * Returns the shooting state of SentryEnemy.
     * -1 -> shooting
     * 0 -> can't see enemy
     * < 0 -> aiming
     */
    public float getShootingState() {
        return shootingState;
    }

}
