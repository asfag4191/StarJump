package inf112.skeleton.utility.listeners;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;

public class WorldContactListener implements ContactListener {
    private ArrayList<CollisionHandler> collisionHandlers;

    /**
     * Constructs a {@link WorldContactListener} with a list of {@link CollisionHandler}s.
     * @param collisionHandlers A list of {@link CollisionHandler}s to be utilized on contact.
     */
    public WorldContactListener(List<CollisionHandler> collisionHandlers) {
        this.collisionHandlers = new ArrayList<>(collisionHandlers);
    }

    /**
     * Constructs a {@link WorldContactListener} with a single {@link CollisionHandler}.
     * @param handler A single {@link CollisionHandler} to be utilized on contact.
     */
    public WorldContactListener(CollisionHandler handler) {
        this.collisionHandlers = new ArrayList<>();
        this.collisionHandlers.add(handler);
    }

    @Override
    public void beginContact(Contact contact) {
        onContact(contact, true);
    }

    @Override
    public void endContact(Contact contact) {
        onContact(contact, false);
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {}

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {}

    /**
     * Handles the contact events based on the contact type.
     * 
     * If the contact has just begun, it calls {@link CollisionHandler#onContactBegin(Contact, Fixture, Fixture)}
     * on all the handlers.
     * If the contact has ended, it calls {@link CollisionHandler#onContactEnded(Contact, Fixture, Fixture)}
     * on all the handlers.
     * 
     *
     * @param contact The contact information for the collision.
     * @param hasBegun {@code true} if the contact has begun, {@code false} if the contact has ended.
     */
    private void onContact(Contact contact, boolean hasBegun) {
        Fixture fixA = contact.getFixtureA();
        Fixture fixB = contact.getFixtureB();

        if (fixA.getBody().getUserData() == null || fixB.getBody().getUserData() == null) return;

        for (CollisionHandler handler : collisionHandlers) {
            if (hasBegun) {
                handler.onContactBegin(contact, fixA, fixB);
            } else {
                handler.onContactEnded(contact, fixA, fixB);
            }
        }
    }
}
