package inf112.skeleton.model.character.enemy;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.RayCastCallback;
import com.badlogic.gdx.physics.box2d.World;

import inf112.skeleton.model.character.Character;
import inf112.skeleton.model.character.controllable_characters.Player;

public class SentryEnemy extends SimpleEnemy implements iStationaryEnemy {
    private Player player;
    public Vector2 playerDirection;
    private float RANGE = 10f;
    ShapeRenderer shapeRenderer = new ShapeRenderer();
    final boolean hitPlayer = false;
    // FOR DEBUGGING
    public Vector2 rStart;
    public Vector2 rEnd;
    public Vector2 hitPoint;

    public SentryEnemy(Character character, Player player, World world) {
        super(character, world);
        this.player = player;
        setupAnimation();
    }

    @Override
    public void shoot(Vector2 direction, float bulletSpeed) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'shoot'");
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
        return seesPlayer[0];
    }

    public void update(float dt) {
        super.update(dt);
        if (getDistanceToPlayer() > RANGE) {
            // System.out.println("Player out of range");
            return;
        }
        if (seesTarget(getPlayerDirection(), RANGE)) {
            // System.out.println("Player in sight");
        } else {
            // System.out.println("Player not in sight");
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
        Texture tex = new Texture(Gdx.files.internal("sprites/star.png"));
        enemyCharacter.animator.addAnimation("idle", tex, 1, 1, 0);
        enemyCharacter.animator.play("idle");
    }

}
