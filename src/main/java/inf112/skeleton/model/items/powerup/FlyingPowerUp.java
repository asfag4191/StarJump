package inf112.skeleton.model.items.powerup;


import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

import inf112.skeleton.model.character.controllable_characters.Player;

/**
 * Power-up that grants flying abilities to the player.
 */
public class FlyingPowerUp extends AbstractPowerUp {

    /**
     * Creates a new flying power-up.
     * @param player Player to apply the power-up effect.
     * @param position Position in the game world.
     * @param sprite Sprite representing the power-up.
     */
    public FlyingPowerUp(Player player, Vector2 position, Sprite sprite) {
        super(player, position, sprite);
    }

    @Override
    public void applyPowerUpEffect() {
        player.enableFlying();
    }
}

