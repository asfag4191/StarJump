package inf112.skeleton.utility.listeners;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.Fixture;

import inf112.skeleton.model.character.Character;
import inf112.skeleton.model.character.enemy.BlackHole;

public class EnemyCollisionHandler implements CollisionHandler {

    @Override
    public void onContactBegin(Contact contact, Fixture fixA, Fixture fixB) {
        targetCollision(fixA, fixB);
    }

    @Override
    public void onContactEnded(Contact contact, Fixture fixA, Fixture fixB) {
        // Not needed for now
    }

    /**
     * Handles collisions between an Enemy Character and another Character,
     * including black hole top sensor contact.
     */
    private void targetCollision(Fixture fixA, Fixture fixB) {
        Object fixUserDataA = fixA.getUserData();
        Object fixUserDataB = fixB.getUserData();

        // Use the fixture’s userData to detect it’s a sensor.

        // Use the body’s userData to identify which BlackHole owns that sensor.

        // Check if the sensor fixture was hit
        if ("blackhole_sensor".equals(fixUserDataB)) {
            Object playerObj = fixA.getBody().getUserData();
            Object blackHoleObj = fixB.getBody().getUserData();
        
            if (playerObj instanceof Character && blackHoleObj instanceof BlackHole) {
                ((BlackHole) blackHoleObj).markForRemoval();
            }
        }

        // Check if the sensor fixture was hit
        if ("blackhole_sensor".equals(fixUserDataA)) {
            Object other = fixB.getBody().getUserData();
            BlackHole blackHole = (BlackHole) fixA.getBody().getUserData(); // black hole is body of sensor
            if (other instanceof Character && blackHole != null) {
                blackHole.markForRemoval(); // kill black hole
                return;
            }
        }
        
        if ("blackhole_sensor".equals(fixUserDataB)) {
            Object other = fixA.getBody().getUserData();
            BlackHole blackHole = (BlackHole) fixB.getBody().getUserData();
            if (other instanceof Character && blackHole != null) {
                blackHole.markForRemoval();
                return;
            }
        }

        //  Regular black hole attack logic (side collision etc)
        Object userDataA = fixA.getBody().getUserData();
        Object userDataB = fixB.getBody().getUserData();

        boolean isBlackHoleA = userDataA instanceof BlackHole;
        boolean isBlackHoleB = userDataB instanceof BlackHole;
        boolean isTargetA = userDataA instanceof Character;
        boolean isTargetB = userDataB instanceof Character;

        if (isBlackHoleA && isTargetB) {
            BlackHole blackHole = (BlackHole) userDataA;
            Character target = (Character) userDataB;
            blackHole.attack(target);
        } else if (isBlackHoleB && isTargetA) {
            BlackHole blackHole = (BlackHole) userDataB;
            Character target = (Character) userDataA;
            blackHole.attack(target);
        }
    }
}
