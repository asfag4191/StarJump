package inf112.skeleton.model.character.enemy;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.RayCastCallback;

import inf112.skeleton.model.WorldModel;
import inf112.skeleton.model.character.Character;
import inf112.skeleton.model.character.controllable_characters.Player;
import inf112.skeleton.model.character.enemy.projectile.Projectile;
import inf112.skeleton.model.character.enemy.projectile.ProjectileAttributes;

public class SentryEnemy extends SimpleEnemy implements iStationaryEnemy {
    private Player player;
    private float RANGE = 10f;
    final boolean hitPlayer = false;
    // FOR DEBUGGING
    public Vector2 rStart;
    public Vector2 rEnd;
    public Vector2 hitPoint;
    public Vector2 target;
    private static final float SHOOTING_DELAY = 5;
    private float shootingState = 0;
    private float shootingDelay = 0;
    private Texture bulletTex;
    private WorldModel worldModel;

    public SentryEnemy(Character character, Player player, WorldModel worldModel) {
        super(character, worldModel.world);
        this.player = player;
        setupAnimation();
        this.bulletTex = new Texture(Gdx.files.internal("sprites/star.png"));
        this.worldModel = worldModel;
    }

    @Override
    public void shoot(Vector2 target, float bulletSpeed) {
        System.out.println("shooting");
        this.shootingState = -1f;

        System.out.println("Shooting at target: " + target);
        // Implement shooting logic here
        this.shootingState = -1f;
        Projectile proj = new Projectile(world, enemyCharacter.getPosition(),
                new ProjectileAttributes(target.nor(), 1f, 1f, false),
                new Vector2(0.5f, 0.5f));
        proj.animator.addAnimation("projectile", bulletTex, 1, 1, 0);
        this.worldModel.addViewableObject(proj);

        this.shootingDelay = 1f;
    }

    @Override
    public boolean seesTarget(Vector2 playerPos) {
        Vector2 thisPos = enemyCharacter.getPosition();
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
        world.rayCast(callback, rayStart, rayEnd);

        this.rStart = rayStart;
        this.rEnd = rayEnd;
        this.hitPoint = hitPoint[0];
        System.out.println("Ray start: " + rayStart + " Ray end: " + rayEnd);
        System.out
                .println("Hit point: " + hitPoint[0] + " Sees player: " + seesPlayer[0] + " Player pos: " + playerPos);

        // System.out.println("Sees player: " + seesPlayer[0]);
        return seesPlayer[0];
    }

    public void update(float dt) {
        super.update(dt);
        if (getDistanceToPlayer() < RANGE) {
            Vector2 playerDirection = getPlayerDirection();
            this.enemyCharacter.setTransform(this.enemyCharacter.getPosition(),
                    playerDirection.angleRad() - 3.14f / 2f);
        }
        if (this.shootingState >= SHOOTING_DELAY) {
            this.shoot(getPlayerDirection(), 0);
        }
        if (this.shootingState >= 0 && seesTarget(player.character.getPosition())) {
            this.shootingState += dt;
        } else if (shootingState > 0) {
            System.out.println("Resetting shooting timer");
            this.shootingState = 0;
        }
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
        Vector2 enemyPosition = this.enemyCharacter.getPosition();

        return playerPosition.sub(enemyPosition).nor();
    }

    /**
     * Returns the distance to the player.
     */
    public float getDistanceToPlayer() {
        Vector2 playerPosition = this.player.character.getPosition();
        Vector2 enemyPosition = enemyCharacter.getPosition();

        return playerPosition.dst(enemyPosition);
    }

    @Override
    protected void setupAnimation() {
        enemyCharacter.animator.clearAnimations();
        Texture tex = new Texture(Gdx.files.internal("sprites/cannon/cannon_barrel.png"));
        enemyCharacter.animator.addAnimation("idle", tex, 1, 1, 0);
        enemyCharacter.animator.play("idle");
    }

    private void setupCannonStand() {
        // Create a cannon stand
        Texture cannonStandTexture = new Texture(Gdx.files.internal("sprites/cannon/cannon_stand.png"));

        Body body = enemyCharacter.getBody();

    }

    @Override
    public void render(Batch batch, float dt) {
        super.render(batch, dt);
        // System.out.println("SentryEnemy render");
        if (rStart == null || rEnd == null) {
            return;
        }
    }

}
