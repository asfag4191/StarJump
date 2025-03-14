package inf112.skeleton.model.items.powerup;


import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

import inf112.skeleton.model.character.controllable_characters.Player;

public class FlyingPowerUp extends AbstractPowerUp {

    public FlyingPowerUp(Player player, Vector2 position, Sprite sprite) {
        //super(player, PowerUpEnum.FLYING);
        super(player, position, sprite);
    }

    @Override
    public void applyPowerUpEffect() {
        player.enableFlying();
    }

    //@Override
    //public void removePowerUpEffect() {
     //   player.disableFlying();
    //}
}

