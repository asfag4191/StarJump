package inf112.skeleton.model.items.powerup;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;

import inf112.skeleton.model.character.controllable_characters.Player;

/**
 * A power-up that grants the player temporary flying ability.
 */
public class FlyingPowerUp extends AbstractPowerUp {

    private static final float FLYING_DURATION = 1.0f; 

    /**
     * Creates a FlyingPowerUp at the specified position for the given player.
     *
     * @param player   the player who will receive the power-up
     * @param position the position where the power-up appears
     * @param sprite   the sprite representing the power-up
     */
    public FlyingPowerUp(Player player, Vector2 position, Sprite sprite) {
        super(player, position, sprite);
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
}


