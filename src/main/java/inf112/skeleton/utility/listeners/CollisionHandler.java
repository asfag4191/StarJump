package inf112.skeleton.utility.listeners;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.Fixture;

/**
 * Handles collisions between two objects in the game.
 */
public interface CollisionHandler {

    /**
     * Called when two objects start touching.
     *
     * @param contact the contact info
     * @param fixA    the first object
     * @param fixB    the second object
     */
    void onContactBegin(Contact contact, Fixture fixA, Fixture fixB);

    /**
     * Called when two objects stop touching.
     *
     * @param contact the contact info
     * @param fixA    the first object
     * @param fixB    the second object
     */
    void onContactEnded(Contact contact, Fixture fixA, Fixture fixB);
}
