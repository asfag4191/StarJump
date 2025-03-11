package inf112.skeleton.model.items.powerup;

import inf112.skeleton.model.Player;

public class FlyingPowerUp extends AbstractPowerUp {

    public FlyingPowerUp(Player player) {
        super(player, PowerUpEnum.FLYING);
    }

    @Override
    public void applyPowerUpEffect() {
        player.enableFlying();
    }

    @Override
    public void removePowerUpEffect() {
        player.disableFlying();
    }
}

