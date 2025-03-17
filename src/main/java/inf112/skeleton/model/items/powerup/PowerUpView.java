package inf112.skeleton.model.items.powerup;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class PowerUpView {

    private final PowerUpManager powerUpManager;

    public PowerUpView(PowerUpManager powerUpManager) {
        this.powerUpManager = powerUpManager;
    }

    public void render(SpriteBatch batch) {
        for (PowerUpObject powerUp : powerUpManager.getPowerUps()) {
            if (!powerUp.isCollected()) {
                powerUp.getSprite().draw(batch);
            }
        }
    }
}
