package inf112.skeleton.model.items.powerup;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;

import inf112.skeleton.model.character.Character;
import inf112.skeleton.utility.SoundManager;

/**
 * A power-up that grants the player temporary flying ability.
 */
public class FlyingPowerUp implements iPowerUp {

    static final float FLYING_DURATION = 1.0f;
    private final Character character;
    private final Sprite sprite;
    private final Vector2 position;
    private final SoundManager soundManager;


    /**
     * Constructor for FlyingPowerUp.
     * Enables the player to fly for a short duration.
     * Sets the character's gravity scale to 0, and linear velocity to 5.
     * Disables collision for the player.
     * 
     * @param character The character who receives the power-up effect.
     * @param position  The position where the power-up appears.
     * @param sprite    The graphical representation of the power-up.
     */
    public FlyingPowerUp(Character character, Vector2 position, Sprite sprite) {
        this.character = character;
        this.position = position;
        this.sprite = sprite;
        this.sprite.setPosition(position.x, position.y);
        this.soundManager = new SoundManager();


    }

    @Override
    public void applyPowerUpEffect() {
        enableFlyingEffect();
        soundManager.powerup_fly.play();
    }

    private void enableFlyingEffect() {
        character.setGravityScale(0f);
        float flyBoost = character.getAttributes().getJumpPower() * 2f; // Boost factor
        character.setVelocity(new Vector2(character.getVelocity().x, flyBoost));
        character.setAsSensor(true);

        Timer.schedule(new Task() {
            @Override
            public void run() {
                character.setGravityScale(1f);
                character.setAsSensor(false);

                character.applyForce(new Vector2(0f, -5f));
            }
        }, FLYING_DURATION);
    }

    @Override
    public Sprite getSprite() {
        return sprite;
    }
}



