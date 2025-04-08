package inf112.skeleton.model.utility.listeners;

import com.badlogic.gdx.physics.box2d.Body;
import inf112.skeleton.model.character.Character;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.Fixture;

import inf112.skeleton.model.items.powerup.PowerUpObject;
import inf112.skeleton.utility.listeners.PowerUpCollisionHandler;

import static org.mockito.Mockito.*;

class PowerUpCollisionHandlerTest {

    private PowerUpCollisionHandler handler;
    private Contact contact;
    private Body characterbody;
    private Body powerUpBody;
    private Fixture characterFixture;
    private Fixture powerUpFixture;
    private Character characterObject;
    private PowerUpObject powerUpObject;

    @BeforeEach
    void setUp() {
        handler = new PowerUpCollisionHandler();

        // Mock all classes needed for the test
        contact = mock(Contact.class);
        characterbody = mock(Body.class);
        powerUpBody = mock(Body.class);
        characterFixture = mock(Fixture.class);
        powerUpFixture = mock(Fixture.class);
        characterObject = mock(Character.class);
        powerUpObject = mock(PowerUpObject.class);

        // Setup the the behaviour of the mocked classes.
        // No need to setup contact as its not used in this specific handler.
        when(characterFixture.getBody()).thenReturn(characterbody);
        when(powerUpFixture.getBody()).thenReturn(powerUpBody);

    }

    @Test
    void testHandleCollision_ValidPowerUp() {
        when(characterbody.getUserData()).thenReturn(characterObject);
        when(powerUpBody.getUserData()).thenReturn(powerUpObject);
        // call onContactBegin
        handler.onContactBegin(contact, characterFixture, powerUpFixture);
        // check for call on onPlayerCollide
        verify(powerUpObject).onPlayerCollide();
    }

    @Test
    void testHandleCollision_InvalidPowerUp() {
        when(characterbody.getUserData()).thenReturn(characterObject);
        when(powerUpBody.getUserData()).thenReturn(new Object()); // not a PowerUp object
        // call onContactBegin
        handler.onContactBegin(contact, characterFixture, powerUpFixture);
        // check for call on onPlayerCollide
        verify(powerUpObject, never()).onPlayerCollide();
    }
}
