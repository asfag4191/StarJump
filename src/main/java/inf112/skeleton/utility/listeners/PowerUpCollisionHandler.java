package inf112.skeleton.utility.listeners;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.Fixture;

import inf112.skeleton.model.character.controllable_characters.Player;
import inf112.skeleton.model.items.powerup.FlyingPowerUp;
import inf112.skeleton.model.items.powerup.PowerUpObject;

public class PowerUpCollisionHandler implements CollisionHandler {

    @Override
    public void handleCollision(Contact contact, Fixture playerFixture, Fixture powerUpFixture) {
        PowerUpObject powerUp = (PowerUpObject) powerUpFixture.getUserData();
        powerUp.onPlayerCollide();

        Player player = (Player) playerFixture.getUserData();

    player.applyPowerUp(new FlyingPowerUp(player, null, null), 1f); // 3 seconds flying
}
}
