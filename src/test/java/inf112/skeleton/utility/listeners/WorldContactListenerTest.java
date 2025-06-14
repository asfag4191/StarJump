package inf112.skeleton.utility.listeners;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.Fixture;


class WorldContactListenerTest {

    private WorldContactListener listener;
    private CollisionHandler collisionHandler;

    private Contact contact;
    private Body bodyA;
    private Body bodyB;
    private Fixture fixtureA;
    private Fixture fixtureB;

    @BeforeEach
    void setUp() {
        collisionHandler = mock(CollisionHandler.class);
        listener = new WorldContactListener(collisionHandler);

        contact = mock(Contact.class);
        bodyA = mock(Body.class);
        bodyB = mock(Body.class);
        fixtureA = mock(Fixture.class);
        fixtureB = mock(Fixture.class);

        when(contact.getFixtureA()).thenReturn(fixtureA);
        when(contact.getFixtureB()).thenReturn(fixtureB);
        when(fixtureA.getBody()).thenReturn(bodyA);
        when(fixtureB.getBody()).thenReturn(bodyB);
    }

    @Test
    void beginContact_calls_onContactBegin_UserDataNotNull() {
        when(bodyA.getUserData()).thenReturn(new Object());
        when(bodyB.getUserData()).thenReturn(new Object());
        listener.beginContact(contact);
        verify(collisionHandler).onContactBegin(contact, fixtureA, fixtureB);
    }

    @Test
    void beginContact_calls_onContactBegin_UserDataIsNull() {
        when(bodyA.getUserData()).thenReturn(null); 
        when(bodyB.getUserData()).thenReturn(new Object());
        listener.beginContact(contact);
        verify(collisionHandler, never()).onContactBegin(contact, fixtureA, fixtureB);
    }

    @Test
    void endContact_calls_onContactEnded_UserDataNotNull() {
        when(bodyA.getUserData()).thenReturn(new Object());
        when(bodyB.getUserData()).thenReturn(new Object());
        listener.endContact(contact);
        verify(collisionHandler).onContactEnded(contact, fixtureA, fixtureB);
    }

    @Test
    void endContact_calls_onContactEnded_UserDataIsNull() {
        when(bodyA.getUserData()).thenReturn(null);
        when(bodyB.getUserData()).thenReturn(new Object());
        listener.endContact(contact);
        verify(collisionHandler, never()).onContactEnded(contact, fixtureA, fixtureB);
    }

    @Test
    void beginContact_callsAllHandlers() {
    CollisionHandler handler1 = mock(CollisionHandler.class);
    CollisionHandler handler2 = mock(CollisionHandler.class);
    
    WorldContactListener multiListener = new WorldContactListener(List.of(handler1, handler2));

    when(bodyA.getUserData()).thenReturn(new Object());
    when(bodyB.getUserData()).thenReturn(new Object());

    multiListener.beginContact(contact);

    verify(handler1).onContactBegin(contact, fixtureA, fixtureB);
    verify(handler2).onContactBegin(contact, fixtureA, fixtureB);
    }
}
