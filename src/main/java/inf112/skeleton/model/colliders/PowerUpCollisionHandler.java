package inf112.skeleton.model.colliders;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.Fixture;

import inf112.skeleton.model.Player;
import inf112.skeleton.model.items.powerup.AbstractPowerUp;

public class PowerUpCollisionHandler {
    private final PowerUpManager powerUpManager;

    public PowerUpCollisionHandler(PowerUpManager powerUpManager) {
        this.powerUpManager = powerUpManager;
    }

    public void handleCollision(Contact contact) {
        Fixture fixA = contact.getFixtureA();
        Fixture fixB = contact.getFixtureB();
    
        Object objA = fixA.getBody().getUserData();
        Object objB = fixB.getBody().getUserData();
    
        if (objA instanceof Player && objB instanceof AbstractPowerUp) {
            System.out.println(" Player collected power-up!");
            ((AbstractPowerUp) objB).collectPowerUp();
            powerUpManager.addPowerUpToRemove(fixB.getBody());  //  Add to removal list
        } else if (objB instanceof Player && objA instanceof AbstractPowerUp) {
            System.out.println(" Player collected power-up!");
            ((AbstractPowerUp) objA).collectPowerUp();
            powerUpManager.addPowerUpToRemove(fixA.getBody());  //  Add to removal list
        }
    }
}    
 
