package inf112.skeleton.model.items.powerup;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.math.Vector2;

import inf112.skeleton.model.Player;
import inf112.skeleton.view.screen.GameScreen;

/**
 * Speed boost power-up that increases player speed.
 */
public class SpeedPowerUp extends AbstractPowerUp {
    private final float speedBoost = 2.0f; // The amount of speed boost

    public SpeedPowerUp(GameScreen screen, Vector2 position, Player player, MapObject mapObject) {
        super(screen, position, player, mapObject);
    }
    @Override
    protected void applyEffect() {
        // Apply an upward force to the player
        player.getBody().applyLinearImpulse(new Vector2(0, 10f), player.getBody().getWorldCenter(), true);
        
        // Disable collisions so the player can fly through objects
        player.setCollisionEnabled(false);
    }
    
    @Override
    protected void removeEffect() {
        // Restore collisions when the effect wears off
        player.setCollisionEnabled(true);
    }
    

    @Override
    public void render(Batch batch, float dt) {
    // Do nothing, since the power-up is already in the Tiled map
}

}
