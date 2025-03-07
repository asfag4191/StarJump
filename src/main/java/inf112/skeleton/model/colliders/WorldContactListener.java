//package inf112.skeleton.model.colliders;
//
//import com.badlogic.gdx.physics.box2d.Contact;
//import com.badlogic.gdx.physics.box2d.ContactImpulse;
//import com.badlogic.gdx.physics.box2d.ContactListener;
//import com.badlogic.gdx.physics.box2d.Fixture;
//import com.badlogic.gdx.physics.box2d.Manifold;
//import inf112.skeleton.model.Player;
//
//public class WorldContactListener implements ContactListener {
//    private final Player player;
//
//    public WorldContactListener(Player player) {
//        this.player = player;
//    }
//
//    @Override
//    public void beginContact(Contact contact) {
//        Fixture a = contact.getFixtureA();
//        Fixture b = contact.getFixtureB();
//
//        // foot <-> platform
//        if (isFootOnPlatform(a, b)) {
//            player.onCollisionStart(b);
//        } else if (isFootOnPlatform(b, a)) {
//            player.onCollisionStart(a);
//        }
//    }
//
//    @Override
//    public void endContact(Contact contact) {
//        Fixture a = contact.getFixtureA();
//        Fixture b = contact.getFixtureB();
//
//        // foot <-> platform
//        if (isFootOnPlatform(a, b)) {
//            player.onCollisionEnd(b);
//        } else if (isFootOnPlatform(b, a)) {
//            player.onCollisionEnd(a);
//        }
//    }
//
//    @Override
//    public void preSolve(Contact contact, Manifold oldManifold) {}
//
//    @Override
//    public void postSolve(Contact contact, ContactImpulse impulse) {}
//
//    private boolean isFootOnPlatform(Fixture footFixture, Fixture platformFixture) {
//        return footFixture.getUserData() != null && footFixture.getUserData().equals("foot") &&
//                platformFixture.getUserData() != null && platformFixture.getUserData().equals("platform");
//    }
//}
////world.setContactListener(new WorldContactListener(player)); i WorldModel hvis du skal bruke dette