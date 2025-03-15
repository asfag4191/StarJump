package inf112.skeleton.utility.listeners;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.Fixture;

import inf112.skeleton.model.items.powerup.PowerUpObject;

public class PowerUpCollisionHandler implements CollisionHandler {

    @Override
    public void handleCollision(Contact contact, Fixture playerFixture, Fixture powerUpFixture) {
        if (powerUpFixture.getUserData() instanceof PowerUpObject powerUpObject) {
            powerUpObject.onPlayerCollide();
        }
    }
}
