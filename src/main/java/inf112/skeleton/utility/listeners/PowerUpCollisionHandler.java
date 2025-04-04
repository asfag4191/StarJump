package inf112.skeleton.utility.listeners;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.Fixture;
import inf112.skeleton.model.character.Character;
import inf112.skeleton.model.items.powerup.PowerUpObject;

public class PowerUpCollisionHandler implements CollisionHandler {

    @Override
    public void onContactBegin(Contact contact, Fixture fixA, Fixture fixB) {
        Object userDataA = fixA.getBody().getUserData();
        Object userDataB = fixB.getBody().getUserData();

        boolean isCharacterA = userDataA instanceof Character;
        boolean isCharacterB = userDataB instanceof Character;
        boolean isPowerUpA = userDataA instanceof PowerUpObject;
        boolean isPowerUpB = userDataB instanceof PowerUpObject;

        if (isCharacterA && isPowerUpB) {
            PowerUpObject powerUp = (PowerUpObject) userDataB;
            powerUp.onPlayerCollide();
        }
        else if (isCharacterB && isPowerUpA) {
            PowerUpObject powerUp = (PowerUpObject) userDataA;
            powerUp.onPlayerCollide();
        }
    }

    @Override
    public void onContactEnded(Contact contact, Fixture fixA, Fixture fixB) {

    }
}
