package inf112.skeleton.model.utility.listeners;

import static org.mockito.Mockito.*;

import com.badlogic.gdx.physics.box2d.*;
import inf112.skeleton.utility.listeners.CollisionHandler;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.badlogic.gdx.backends.headless.HeadlessApplicationConfiguration;
import com.badlogic.gdx.graphics.GL20;
import inf112.skeleton.utility.listeners.WorldContactListener;


class WorldContactListenerTest {

    private WorldContactListener listener;
    private CollisionHandler collisionHandler;

    private Contact contact;
    private Body bodyA;
    private Body bodyB;
    private Fixture fixtureA;
    private Fixture fixtureB;

    @BeforeAll
    static void initLibGdx() {
        new HeadlessApplication(new ApplicationAdapter() {}, new HeadlessApplicationConfiguration());
    }

    @BeforeEach
    void setUp() {
        collisionHandler = mock(CollisionHandler.class);
        listener = new WorldContactListener(collisionHandler);

        Gdx.gl = Gdx.gl20 = Mockito.mock(GL20.class);

        // Mock all the classes needed for the test.
        contact = mock(Contact.class);
        bodyA = mock(Body.class);
        bodyB = mock(Body.class);
        fixtureA = mock(Fixture.class);
        fixtureB = mock(Fixture.class);

        // Setup the the behaviour of the mocked classes.
        when(contact.getFixtureA()).thenReturn(fixtureA);
        when(contact.getFixtureB()).thenReturn(fixtureB);
        when(fixtureA.getBody()).thenReturn(bodyA);
        when(fixtureB.getBody()).thenReturn(bodyB);
    }

    @Test
    void beginContact_calls_onContactBegin_UserDataNotNull() {
        when(bodyA.getUserData()).thenReturn(new Object());
        when(bodyB.getUserData()).thenReturn(new Object());
        // Make a call
        listener.beginContact(contact);
        // Should make a call on onContactBegin
        verify(collisionHandler).onContactBegin(contact, fixtureA, fixtureB);
    }

    @Test
    void beginContact_calls_onContactBegin_UserDataIsNull() {
        when(bodyA.getUserData()).thenReturn(null); // Simulating null UserData
        when(bodyB.getUserData()).thenReturn(new Object());
        // Make a call
        listener.beginContact(contact);
        // Should not make a call on onContactBegin
        verify(collisionHandler, never()).onContactBegin(contact, fixtureA, fixtureB);
    }

    @Test
    void endContact_calls_onContactEnded_UserDataNotNull() {
        when(bodyA.getUserData()).thenReturn(new Object());
        when(bodyB.getUserData()).thenReturn(new Object());
        // Make a call
        listener.endContact(contact);
        // Should make a call on onContactEnded
        verify(collisionHandler).onContactEnded(contact, fixtureA, fixtureB);
    }

    @Test
    void endContact_calls_onContactEnded_UserDataIsNull() {
        when(bodyA.getUserData()).thenReturn(null); // Simulating null UserData
        when(bodyB.getUserData()).thenReturn(new Object());
        // Make a call
        listener.endContact(contact);
        // Should not make a call on onContactEnded
        verify(collisionHandler, never()).onContactEnded(contact, fixtureA, fixtureB);
    }
}
