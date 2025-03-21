package inf112.skeleton.model.items.powerup;

import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * Abstract base class for game power-ups.
 */
public interface iPowerUp {
    /**
     * Apply the specific effect of the power-up to the player.
     * This method must be implemented by subclasses to define 
     * the specific behavior of the power-up.
     */
    public  void applyPowerUpEffect();

    /**
     * Gets the graphical representation of the power-up.
     *
     * @return The {@link Sprite} representing the power-up, which is used for rendering.
     */
    public Sprite getSprite();
}

