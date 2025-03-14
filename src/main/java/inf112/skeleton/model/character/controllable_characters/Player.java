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
    private static final float JUMP_FORCE = 5f;
    private boolean isFlying = false;
    private float powerUpTimer = 0;
    private boolean isGrounded;

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

    public Body getBody() {
        return this.body;
    }

    public void setCollisionEnabled(boolean enabled) {
        if (enabled) {
            this.getBody().getFixtureList().first().setSensor(false);
        } else {
            this.getBody().getFixtureList().first().setSensor(true);
        }
    }

    public void applyPowerUp(AbstractPowerUp powerUp, float duration) {
        powerUp.applyPowerUpEffect();
        powerUpTimer = duration;
    }

    // @Override
    public void update(float dt) {
        if (isFlying) {
            if (powerUpTimer > 0) {
                powerUpTimer -= dt;
            }
            if (powerUpTimer <= 0) {
                disableFlying(); // Disable or remove power-up effect when time expires
            }
        }
    }

    public void enableFlying() {
        isFlying = true;
        body.setGravityScale(0f);
        setCollisionEnabled(false); // Flying through objects
    }

    public void disableFlying() {
        isFlying = false;
        body.setGravityScale(1f);
        setCollisionEnabled(true); // Restore normal collisions
    }
}
