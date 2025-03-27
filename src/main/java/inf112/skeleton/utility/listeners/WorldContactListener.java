package inf112.skeleton.utility.listeners;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;

import inf112.skeleton.app.StarJump;
import inf112.skeleton.model.character.controllable_characters.Player;
import inf112.skeleton.model.items.powerup.PowerUpObject;

public class WorldContactListener implements ContactListener {

    private final PowerUpCollisionHandler powerUpCollisionHandler;
    // private final EnemyCollisionHandler enemyCollisionHandler;
    // private final DangerCollisionHandler dangerCollisionHandler;

    public WorldContactListener(
            PowerUpCollisionHandler powerUpCollisionHandler
            //, EnemyCollisionHandler enemyCollisionHandler
            //, DangerCollisionHandler dangerCollisionHandler
    ) {
        this.powerUpCollisionHandler = powerUpCollisionHandler;
        // this.enemyCollisionHandler = enemyCollisionHandler;
        // this.dangerCollisionHandler = dangerCollisionHandler;
    }

    @Override
    public void beginContact(Contact contact) {
        Fixture fixA = contact.getFixtureA();
        Fixture fixB = contact.getFixtureB();

        if (fixA.getUserData() == null || fixB.getUserData() == null) return;

        Object userDataA = fixA.getUserData();
        Object userDataB = fixB.getUserData();

        if (userDataA instanceof Player && userDataB instanceof PowerUpObject) {
            powerUpCollisionHandler.handleCollision(contact, fixA, fixB);
        } else if (userDataB instanceof Player && userDataA instanceof PowerUpObject) {
            powerUpCollisionHandler.handleCollision(contact, fixB, fixA);
        } 

        if (userDataA instanceof Player && fixB.getFilterData().categoryBits == StarJump.GROUND_BIT) {
            ((Player) userDataA).landed();
        } else if (userDataB instanceof Player && fixA.getFilterData().categoryBits == StarJump.GROUND_BIT) {
            ((Player) userDataB).landed();
        }

    }

    @Override public void endContact(Contact contact) {
        Fixture fixA = contact.getFixtureA();
        Fixture fixB = contact.getFixtureB();

        if (fixA.getUserData() == null || fixB.getUserData() == null) return;

        Object userDataA = fixA.getUserData();
        Object userDataB = fixB.getUserData();

        if (userDataA instanceof Player && fixB.getFilterData().categoryBits == StarJump.GROUND_BIT) {
            ((Player) userDataA).isGrounded = false;
        } else if (userDataB instanceof Player && fixA.getFilterData().categoryBits == StarJump.GROUND_BIT) {
            ((Player) userDataB).isGrounded = false;
        }
    }
    @Override public void preSolve(Contact contact, Manifold oldManifold) {}
    @Override public void postSolve(Contact contact, ContactImpulse impulse) {}
}
