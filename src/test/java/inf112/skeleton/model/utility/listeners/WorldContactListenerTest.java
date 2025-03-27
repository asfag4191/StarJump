package inf112.skeleton.model.utility.listeners;

import static org.junit.jupiter.api.Assertions.assertFalse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.World;

import inf112.skeleton.app.StarJump;
import inf112.skeleton.model.character.controllable_characters.Player;
import inf112.skeleton.model.items.powerup.PowerUpObject;
import inf112.skeleton.utility.listeners.PowerUpCollisionHandler;
import inf112.skeleton.utility.listeners.WorldContactListener;

class WorldContactListenerTest {

    private WorldContactListener listener;
    private PowerUpCollisionHandler powerUpCollisionHandler;

    private Contact contact;
    private Fixture fixtureA;
    private Fixture fixtureB;
    private Filter groundFilter;

    @BeforeEach
    void setUp() {
        powerUpCollisionHandler = mock(PowerUpCollisionHandler.class);
        listener = new WorldContactListener(powerUpCollisionHandler);

        contact = mock(Contact.class);
        fixtureA = mock(Fixture.class);
        fixtureB = mock(Fixture.class);

        when(contact.getFixtureA()).thenReturn(fixtureA);
        when(contact.getFixtureB()).thenReturn(fixtureB);

        groundFilter = new Filter();
        groundFilter.categoryBits = StarJump.GROUND_BIT;
    }

    @Test
    void testPowerUpCollision_PlayerA_PowerUpB() {
        Player player = mock(Player.class);
        PowerUpObject powerUp = mock(PowerUpObject.class);

        when(fixtureA.getUserData()).thenReturn(player);
        when(fixtureB.getUserData()).thenReturn(powerUp);

        listener.beginContact(contact);

        verify(powerUpCollisionHandler).handleCollision(contact, fixtureA, fixtureB);
    }

    @Test
    void testPowerUpCollision_PlayerB_PowerUpA() {
        Player player = mock(Player.class);
        PowerUpObject powerUp = mock(PowerUpObject.class);

        when(fixtureA.getUserData()).thenReturn(powerUp);
        when(fixtureB.getUserData()).thenReturn(player);

        listener.beginContact(contact);

        verify(powerUpCollisionHandler).handleCollision(contact, fixtureB, fixtureA);
    }

    @Test
    void testPlayerLandsOnGround_A() {
        Player player = mock(Player.class);
        when(fixtureA.getUserData()).thenReturn(player);
        when(fixtureB.getUserData()).thenReturn(new Object());
        when(fixtureB.getFilterData()).thenReturn(groundFilter);

        listener.beginContact(contact);

        verify(player).landed();
    }

    @Test
    void testPlayerLandsOnGround_B() {
        Player player = mock(Player.class);
        when(fixtureA.getUserData()).thenReturn(new Object());
        when(fixtureB.getUserData()).thenReturn(player);
        when(fixtureA.getFilterData()).thenReturn(groundFilter);

        listener.beginContact(contact);

        verify(player).landed();
    }

    @Test
    void testPlayerLeavesGround_A() {
        DummyPlayer player = new DummyPlayer();
        when(fixtureA.getUserData()).thenReturn(player);
        when(fixtureB.getUserData()).thenReturn(new Object());
        when(fixtureB.getFilterData()).thenReturn(groundFilter);

        when(contact.getFixtureA()).thenReturn(fixtureA);
        when(contact.getFixtureB()).thenReturn(fixtureB);

        listener.endContact(contact);

        assertFalse(player.isGrounded);
    }

    @Test
    void testPlayerLeavesGround_B() {
        DummyPlayer player = new DummyPlayer();
        when(fixtureA.getUserData()).thenReturn(new Object());
        when(fixtureB.getUserData()).thenReturn(player);
        when(fixtureA.getFilterData()).thenReturn(groundFilter);

        when(contact.getFixtureA()).thenReturn(fixtureA);
        when(contact.getFixtureB()).thenReturn(fixtureB);

        listener.endContact(contact);

        assertFalse(player.isGrounded);
    }

    /**
     * DummyPlayer used for testing isGrounded changes.
     */
    private static class DummyPlayer extends Player {
        public boolean isGrounded = true;

        public DummyPlayer() {
            super(mock(Vector2.class), mock(World.class));
        }

        @Override
        public void landed() {
            isGrounded = true;
        }
    }
}
