package inf112.skeleton.model.items.powerup;

import inf112.skeleton.model.Player;

public abstract class AbstractPowerUp {
    
    protected final Player player;
    protected final PowerUpEnum type;

    public AbstractPowerUp(Player player, PowerUpEnum type) {
        this.player = player;
        this.type = type;
    }

    public abstract void applyPowerUpEffect();
    public abstract void removePowerUpEffect();
}

