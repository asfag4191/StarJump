package inf112.skeleton.model.items.powerup;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * View class for rendering power-ups.
 */
public class PowerUpView {

    private final PowerUpManager powerUpManager;

    /**
     * Constructor for PowerUpView.
     * 
     * @param powerUpManager
     */
    public PowerUpView(PowerUpManager powerUpManager) {
        this.powerUpManager = powerUpManager;
    }

    /**
     * Renders all power-ups that are not collected.
     * 
     * @param batch
     */
    public void render(SpriteBatch batch) {
        for (PowerUpObject powerUp : powerUpManager.getPowerUps()) {
            if (!powerUp.isCollected()) {
                powerUp.getSprite().draw(batch);
            }
        }
    }
}
