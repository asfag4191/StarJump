package inf112.skeleton.model.colliders;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.Fixture;

public interface CollisionHandler {
    void handleCollision(Contact contact, Fixture fixA, Fixture fixB);
}
