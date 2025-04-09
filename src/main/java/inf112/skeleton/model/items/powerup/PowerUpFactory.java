package inf112.skeleton.model.items.powerup;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

import inf112.skeleton.model.character.Character;

/**
 * Abstract base class representing a generic factory responsible for creating power-up objects.
 *
 * Concrete subclasses specify how to create specific power-ups by implementing the 
 * abstract {@link #create} method.
 */
public abstract class PowerUpFactory {
    private final String texturePath;

    /**
     * Constructs a new PowerUpFactory with the given texture path.
     *
     * @param texturePath Path to the graphical texture asset used for creating power-up sprites.
     */
    protected PowerUpFactory(String texturePath) {
        this.texturePath = texturePath;
    }

    /**
     * Creates and positions a sprite for the power-up at the given world coordinates.
     *
     * @param position The exact position to place the sprite in the game world.
     * @return A new {@link Sprite} instance for the power-up.
     */
    protected Sprite createSprite(Vector2 position) {
        Texture texture = new Texture(texturePath);
        Sprite sprite = new Sprite(texture);
        sprite.setSize(1,1);
        sprite.setPosition(position.x, position.y);
        return sprite;
    }

     /**
     * Abstract method defining the creation of a power-up instance.
     *
     * @param character The character who can interact with the power-up.
     * @param position  The position in the game world to spawn the power-up.
     * @return A concrete implementation of {@link iPowerUp}.
     */
    public abstract iPowerUp create(Character character, Vector2 position);

    
}