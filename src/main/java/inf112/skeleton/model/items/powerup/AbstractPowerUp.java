package inf112.skeleton.model.items.powerup;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

import inf112.skeleton.model.character.controllable_characters.Player;

public abstract class AbstractPowerUp {
    
    protected final Player player;
    protected final Sprite sprite;
    protected final Vector2 position; //position of powerup in the game world

    public AbstractPowerUp(Player player, Vector2 position, Sprite sprite) {
        this.player = player;
        this.position = position;
        this.sprite=sprite;

    }
    

    public abstract void applyPowerUpEffect();
    //public abstract void removePowerUpEffect();

    //2d image of powerup, the graphical apperence of the powerup
    public Sprite getSprite(){
        return sprite;
    }
}

