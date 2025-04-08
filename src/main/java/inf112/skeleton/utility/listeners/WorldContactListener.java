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
        Fixture fixA = contact.getFixtureA();
        Fixture fixB = contact.getFixtureB();

        if (fixA.getBody().getUserData() == null || fixB.getBody().getUserData() == null) return;

        for (CollisionHandler handler : collisionHandlers) {
            handler.onContactBegin(contact, fixA, fixB);
        }
    }

    @Override public void endContact(Contact contact) {
        Fixture fixA = contact.getFixtureA();
        Fixture fixB = contact.getFixtureB();

        if (fixA.getBody().getUserData() == null || fixB.getBody().getUserData() == null) return;

        for (CollisionHandler handler : collisionHandlers) {
            handler.onContactEnded(contact, fixA, fixB);
        }
    }
    @Override public void preSolve(Contact contact, Manifold oldManifold) {}
    @Override public void postSolve(Contact contact, ContactImpulse impulse) {}
}
