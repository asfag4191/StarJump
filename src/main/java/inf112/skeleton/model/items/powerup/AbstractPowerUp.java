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

    public AbstractPowerUp(Player player, Vector2 position, Sprite sprite) {
        this.player = player;
        this.position = position;
        this.sprite=sprite;
        sprite.setPosition(position.x, position.y);
    }
    

    /**
     * Apply the specific effect of the power-up to the player.
     */
    public abstract void applyPowerUpEffect();

    /**
     * @return The graphical representation of the power-up.
     */
    public Sprite getSprite() {
        return sprite;
    }
}

