package inf112.skeleton.model.character.enemy;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.RayCastCallback;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;

import inf112.skeleton.model.character.Character;
import inf112.skeleton.model.character.controllable_characters.Player;

public class SentryEnemy extends SimpleEnemy implements iStationaryEnemy {
    private Player player;
    private float RANGE = 10f;
    private ShapeRenderer shapeRenderer = new ShapeRenderer();
    final boolean hitPlayer = false;
    // FOR DEBUGGING
    public Vector2 rStart;
    public Vector2 rEnd;
    public Vector2 hitPoint;
    public Vector2 target;
    private static final float SHOOTING_DELAY = 5;
    private float shootingState = 0;
    private float shootTimeLeft = 0;
    private Vector2 laserTarget;

    public SentryEnemy(Character character, Player player, World world) {
        super(character, world);
        this.player = player;
        setupAnimation();
    }

    @Override
    public void shoot(Vector2 target, float bulletSpeed) {
        System.out.println("shooting");
        shootingState = 0f;
        this.laserTarget = target;

        Timer.schedule(new Task() {
            @Override
            public void run() {
                System.out.println("Shooting at target: " + target);
                // Implement shooting logic here
                shootingState = -1f;

                if (seesTarget(target.nor(), RANGE)) {
                    player.character.takeDamage(10);
                    System.out.println("Hit player");
                } else {
                    System.out.println("Missed player");
                }
                shootTimeLeft = 0.5f;
            }
        }, SHOOTING_DELAY);
    }

    protected void fire(Vector2 target2, float dt) {
        if (target2 == null) {
            System.out.println("Target is null");
            this.shootTimeLeft = 0;
        }

        this.shootTimeLeft -= dt;

        if (this.shootTimeLeft <= 0) {
            this.shootTimeLeft = 0;
            this.shootingState = 0;
        }
    }

    protected void createShootingLine(Batch batch, Vector2 hit) {
        shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(1, 0, 0, 1);
        shapeRenderer.line(this.enemyCharacter.getPosition(), hit);
        shapeRenderer.end();
    }

    private void aiming(Vector2 target) {
        this.enemyCharacter.setTransform(this.enemyCharacter.getPosition(),
                (float) (target.angleRad() - Math.PI / 2));
        if (shootingState > 1) {
            shoot(target, 0);
        }
    }

    private void createAimingLine(Batch batch, Vector2 from, Vector2 to) {
        shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(1, 1, 1, 1);
        shapeRenderer.line(from, to);
        shapeRenderer.end();
    }

    @Override
    public boolean seesTarget(Vector2 direction, float range) {
        Vector2 thisPos = enemyCharacter.getPosition();
        Vector2 rayStart = thisPos.cpy(); // start of the ray
        Vector2 rayEnd = thisPos.cpy().add(direction.scl(range)); // end of the ray

        final Vector2[] hitPoint = new Vector2[1];
        final Boolean[] seesPlayer = new Boolean[1];
        seesPlayer[0] = false;
        RayCastCallback callback = new RayCastCallback() {
            @Override
            public float reportRayFixture(Fixture fixture, Vector2 point, Vector2 normal, float fraction) {
                // System.out.println("Ray hit: " + fixture.getBody().getUserData());
                if ("ground".equals(fixture.getBody().getUserData())) {
                    hitPoint[0] = point.cpy();
                    seesPlayer[0] = false;
                    return -1;
                } else if (fixture.getBody().getUserData() instanceof Character) {
                    Character character = (Character) fixture.getBody().getUserData();
                    if (character.isPlayer()) {
                        hitPoint[0] = point.cpy();
                        seesPlayer[0] = true;
                        return -1;
                    }
                    return 1;
                }
                return 1;
            };
        };
        world.rayCast(callback, rayStart, rayEnd);

        this.rStart = rayStart;
        this.rEnd = rayEnd;
        this.hitPoint = hitPoint[0];
        // System.out.println("Sees player: " + seesPlayer[0]);
        return seesPlayer[0];
    }

    public void update(float dt) {
        super.update(dt);
        if (getDistanceToPlayer() < RANGE) {
            Vector2 playerDirection = getPlayerDirection();
            // System.out.println("Player out of range");
            if (playerDirection == null) {
                System.out.println("Player direction is null");
                return;
            }
            if (this.shootingState >= 0 && seesTarget(playerDirection, RANGE)) {
                aiming(this.hitPoint);
                this.shootingState += dt;
            } else if (shootingState > 0) {
                System.out.println("Resetting shooting timer");
                this.shootingState = 0;
            }

            if (this.shootingState == -1f) {
                fire(laserTarget, dt);
            }
        }
    }

    // Will be private when rest is implemented
    public Vector2 getPlayerDirection() {
        Vector2 playerPosition = this.player.character.getPosition();
        Vector2 enemyPosition = enemyCharacter.getPosition();

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
        if (this.shootingState > 0 && hitPoint != null) {
            // createAimingLine(batch, rStart, hitPoint);
        }
        if (this.shootingState == -1f && hitPoint != null) {
            createShootingLine(batch, hitPoint);
        }

    }

}
