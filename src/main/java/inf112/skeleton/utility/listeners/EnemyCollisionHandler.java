package inf112.skeleton.utility.listeners;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.Fixture;

import inf112.skeleton.model.character.Character;
import inf112.skeleton.model.character.enemy.BlackHole;

public class EnemyCollisionHandler implements CollisionHandler{

    @Override
    public void onContactBegin(Contact contact, Fixture fixA, Fixture fixB) {
        targetCollision(fixA, fixB);
    }

    @Override
    public void onContactEnded(Contact contact, Fixture fixA, Fixture fixB) {}

    /**
     * Handles collisions between an Enemy-<code>Character</code> and 
     * Another <code>Character</code>
     * @param fixtureA ...
     * @param fixtureB ...
     */
    private void targetCollision(Fixture fixtureA, Fixture fixtureB) {
        Object userDataA = fixtureA.getBody().getUserData();
        Object userDataB = fixtureB.getBody().getUserData();

        boolean isBlackHoleA = userDataA instanceof BlackHole;
        boolean isBlackHoleB = userDataB instanceof BlackHole;
        boolean isTargetA = userDataA instanceof Character;
        boolean isTargetB = userDataB instanceof Character;

        if (isBlackHoleA && isTargetB) {
            BlackHole blackHole = (BlackHole) userDataA;
            Character target = (Character) userDataB;
            blackHole.attack(target);
        }
        else if (isBlackHoleB && isTargetA) {
            BlackHole blackHole = (BlackHole) userDataB;
            Character target = (Character) userDataA;
            blackHole.attack(target);
        }
    }
}
