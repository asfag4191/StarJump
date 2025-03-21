package inf112.skeleton.model.items.powerup;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;

import inf112.skeleton.app.StarJump;
import inf112.skeleton.model.character.controllable_characters.Player;
import inf112.skeleton.model.items.InteractiveTileObject;
import inf112.skeleton.view.screen.GameScreen;

/**
 * Represents a power-up object in the game world.
 * This class handles collision detection, applying power-up effects, and rendering.
 */
public class PowerUpObject extends InteractiveTileObject {

    private boolean isCollected = false;
    private final iPowerUp powerUp;
    private final Player player;

    /**
     * Constructs a PowerUpObject with the given parameters.
     *
     * @param screen  the game screen where the power-up exists
     * @param object  the map object representing the power-up
     * @param powerUp the specific power-up effect to apply
     * @param player  the player who can collect the power-up
     * @param sprite  the sprite representing the power-up
     */
    public PowerUpObject(GameScreen screen, MapObject object, iPowerUp powerUp, Player player, Sprite sprite) {
        super(screen, object, StarJump.POWERUP);
        this.powerUp = powerUp;
        this.player = player;
        setCollisionFilter();
    }

    /**
     * Sets the collision filter for the power-up.
     * Only allows collision with the player.
     */
    private void setCollisionFilter() {
        var filter = fixture.getFilterData();
        filter.categoryBits = StarJump.POWERUP;
        filter.maskBits = StarJump.PLAYER_BIT;
        fixture.setFilterData(filter);
        fixture.setUserData(this); 
    }

    /**
     * Handles collision with the player, applying the power-up effect and marking it for removal.
     */
    @Override
    public void onPlayerCollide() {
        if (!isCollected) {
            powerUp.applyPowerUpEffect();
            screen.getPowerUpManager().markForRemoval(this);
            isCollected = true;
        }
    }

    /**
     * Checks if the power-up has been collected.
     *
     * @return true if collected, false otherwise
     */
    public boolean isCollected() {
        return isCollected;
    }

    /**
     * Sets the collected status of the power-up.
     *
     * @param collected true if collected, false otherwise
     */
    public void setCollected(boolean collected) {
        isCollected = collected;
    }

    /**
     * Gets the world where the power-up exists.
     *
     * @return the Box2D world instance
     */
    public World getWorld() {
        return world;
    }

    /**
     * Gets the physics body of the power-up.
     *
     * @return the Box2D body instance
     */
    public Body getBody() {
        return body;
    }

    /**
     * Gets the associated power-up effect.
     *
     * @return the AbstractPowerUp instance
     */
    public iPowerUp getPowerUp() {
        return powerUp;

    }

    /**
     * Sets the physics body of the power-up.
     *
     * @param body the Box2D body instance
     */
    public void setBody(Body body) {
        this.body = body;

    }

    /**
     * Gets the player who can collect the power-up.
     *
     * @return the Player instance
     */
    public Player getPlayer() {
        return player;
    }

    @Override
    public void dispose() {
        if (body != null) {
            world.destroyBody(body);
            body = null; 
        }
    }

    @Override
    public void update(float dt) {
        if (isCollected) {
            dispose();

        }
    }

    /**
     * Gets the sprite used to render the power-up.
     *
     * @return the Sprite instance
     */
    public Sprite getSprite() {
        return powerUp.getSprite();
    }
    

}


