package inf112.skeleton.model.items.powerup;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;

import inf112.skeleton.model.character.controllable_characters.Player;

/**
 * A power-up that grants the player temporary flying ability.
 */
public class FlyingPowerUp implements iPowerUp {

    private static final float FLYING_DURATION = 1.0f; 
    private final Player player;
    private final Sprite sprite;
    private final Vector2 position; 
    

    /**
     * Constructor for FlyingPowerUp.
     * Enables the player to fly for a short duration.
     * Sets the player's gravity scale to 0, and linear velocity to 5.
     * Disables collision for the player.
     * 
     * @param player   Player who receives the power-up effect.
     * @param position The position where the power-up appears.
     * @param sprite   The graphical representation of the power-up.
     */
    public FlyingPowerUp(Player player, Vector2 position, Sprite sprite) {
        this.player = player;
        this.position = position;
        this.sprite = sprite;
        this.sprite.setPosition(position.x, position.y);

    }

    @Override
    public void applyPowerUpEffect() {
        enableFlyingEffect();
    }

    private void enableFlyingEffect() {
        player.getBody().setGravityScale(0f);
        player.getBody().setLinearVelocity(player.getBody().getLinearVelocity().x, 5f);
        player.setCollisionEnabled(false); 

        
        Timer.schedule(new Task() {
            @Override
            public void run() {
                player.getBody().setGravityScale(1f); 
                player.setCollisionEnabled(true);    
            }
        }, FLYING_DURATION);
    }

    @Override
    public Sprite getSprite() {
        return sprite;
    }
}


