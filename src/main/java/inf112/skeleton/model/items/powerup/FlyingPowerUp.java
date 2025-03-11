package inf112.skeleton.model.items.powerup;

import inf112.skeleton.model.game_objects.Player;

public class FlyingPowerUp extends AbstractPowerUp {

    public FlyingPowerUp(Player player) {
        super(player, PowerUpEnum.FLYING);
    }

    @Override
    public void applyPowerUpEffect() {
        // do power up code here. not inside player
    }

    @Override
    public void removePowerUpEffect() {
        // do power up code here not inside player.
    }
}

