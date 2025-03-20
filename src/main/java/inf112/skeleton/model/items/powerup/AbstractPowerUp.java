package inf112.skeleton.model.items.powerup;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

import inf112.skeleton.model.character.controllable_characters.Player;

/**
 * Abstract base class for game power-ups.
 */
public abstract class AbstractPowerUp {
    protected final Player player;
    protected final Sprite sprite;
    protected final Vector2 position; 
    protected PowerUpEnum type; 

    /**
     * Constructor for AbstractPowerUp.
     * Initializes the power-up's properties including the player, position, 
     * sprite, and typ
     * 
     * @param player   Player who receives the power-up effect.
     * @param position The position where the power-up appears.
     * @param sprite   The graphical representation of the power-up.
     */
    public AbstractPowerUp(Player player, Vector2 position, Sprite sprite) {
        this.player = player;
        this.position = position;
        this.sprite=sprite;
        sprite.setPosition(position.x, position.y);
    }
    

    /**
     * Apply the specific effect of the power-up to the player.
     * This method must be implemented by subclasses to define 
     * the specific behavior of the power-up.
     */
    public abstract void applyPowerUpEffect();

    /**
     * Gets the graphical representation of the power-up.
     *
     * @return The {@link Sprite} representing the power-up, which is used for rendering.
     */
    public Sprite getSprite() {
        return sprite;
    }
}

