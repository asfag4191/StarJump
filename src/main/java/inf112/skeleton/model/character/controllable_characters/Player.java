package inf112.skeleton.model.character.controllable_characters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.World;

import inf112.skeleton.app.StarJump;
import inf112.skeleton.model.character.Character;
import inf112.skeleton.model.character.Stats;
import inf112.skeleton.model.items.powerup.AbstractPowerUp;

/**
 * Represents the player character in the game.
 * This class extends the Character class and provides
 * player-specific functionality such as jumping and rendering.
 */
public class Player extends Character {
    private static final float JUMP_FORCE = 7f;


    /**
     * Creates a new player character with the given size and world.
     * @param size The size of the player character.
     * @param world The world in which the player character exists.
     */
    // Power-up related fields
    private boolean isFlying = false;
    private float powerUpTimer = 0;
    public boolean isGrounded = false;
    private int jumpMade = 0;
    private final int MAX_JUMP = 2;
    //private float flyingTime = 0f; // flying duration timer
    private final float MAX_FLYING_TIME = 1f; // flying duration (2 seconds)

    public Player(Vector2 size, World world) {
        super("Star", new Stats(100, 2, 16, 5, 1), size, world, true);
        this.getBody().setUserData(this);

        Filter filter = new Filter();
        // this.texture = new TextureRegion(new
        // Texture(Gdx.files.internal("sprites/star.png")));

        filter.categoryBits = StarJump.PLAYER_BIT;
        filter.maskBits = StarJump.GROUND_BIT | StarJump.POWERUP;
        this.getBody().setUserData(this);

        FileHandle fileHandle = Gdx.files.internal("sprites/star.png");
        if (!fileHandle.exists()) {
            System.out.println("Asset does NOT exist: " + fileHandle.path());
        } else {
            System.out.println("Asset found: " + fileHandle.path());
        }
        texture = new TextureRegion(new Texture(fileHandle));
        for (Fixture fixture : this.getBody().getFixtureList()) {
            fixture.setFilterData(filter);
            fixture.setUserData(this);
        }
    }

    public void setPosition(Vector2 position) {
        getBody().setTransform(position, getBody().getAngle());
    }

    /**
     * Enables or disables collision for the object's body.
     * When collision is enabled, the fixture behaves as a solid object.
     * When disabled, the fixture becomes a sensor, allowing other objects to pass through.
     *
     * @param enabled {@code true} to enable collision, {@code false} to disable it
     */
    public void setCollisionEnabled(boolean enabled) {
        this.getBody().getFixtureList().first().setSensor(!enabled);
    }

    public void applyPowerUp(AbstractPowerUp powerUp, float duration) {
        powerUp.applyPowerUpEffect();
        powerUpTimer = duration;
    }


    /**
    * Updates player's state each frame.
    *
    * @param dt Delta time since last frame.
    */
    public void update(float dt) {
        if (isFlying) {
            powerUpTimer += dt;
            if (powerUpTimer < MAX_FLYING_TIME) {
                body.setGravityScale(0f);
                body.setLinearVelocity(body.getLinearVelocity().x, 5f); // Increase from 5f to 8f if too slow
            } else {
                disableFlying();
            }
        }
    } 

    /**
     * Enables flying mode, ignoring gravity and collision for a limited time.
    */
    public void enableFlying() {
        isFlying = true;
        powerUpTimer = 0f; // Clear timer!
        body.setGravityScale(0f);
        setCollisionEnabled(false); // Flying through objects
    }

    /**
     * Disables the flying state, re-enabling gravity and collisions.
     */
    public void disableFlying() {
        isFlying = false;
        powerUpTimer = 0f; 
        body.setGravityScale(1f);
        setCollisionEnabled(true); 
    }

    public void jump(){
        if (jumpMade < MAX_JUMP) {
            body.setLinearVelocity(body.getLinearVelocity().x, 0);
            body.applyLinearImpulse(new Vector2(0, JUMP_FORCE), body.getWorldCenter(), true);
            jumpMade++;
            isGrounded = false;
        }
    }

    public void landed(){
        jumpMade = 0;
        isGrounded = true;
    }
}