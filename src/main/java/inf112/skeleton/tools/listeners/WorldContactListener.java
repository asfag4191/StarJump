package inf112.skeleton.tools.listeners;

import com.badlogic.gdx.physics.box2d.*;
import inf112.skeleton.model.game_objects.Player;
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

        // Clearly separate handling with instanceof checks
        if (userDataA instanceof Player && userDataB instanceof PowerUpObject) {
            powerUpCollisionHandler.handleCollision(contact, fixA, fixB);
        } else if (userDataB instanceof Player && userDataA instanceof PowerUpObject) {
            powerUpCollisionHandler.handleCollision(contact, fixB, fixA);
        } 
        // Add additional else-if clearly here:
        // else if (userDataA instanceof Player && userDataB instanceof Enemy) { ... }

    }

    @Override public void endContact(Contact contact) {}
    @Override public void preSolve(Contact contact, Manifold oldManifold) {}
    @Override public void postSolve(Contact contact, ContactImpulse impulse) {}
}
