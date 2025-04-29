package inf112.skeleton.utility.listeners;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.Fixture;

import inf112.skeleton.model.character.Character;
import inf112.skeleton.model.items.powerup.PowerUpObject;
import inf112.skeleton.utility.listeners.PowerUpCollisionHandler;

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
        contact = mock(Contact.class);
        characterbody = mock(Body.class);
        powerUpBody = mock(Body.class);
        characterFixture = mock(Fixture.class);
        powerUpFixture = mock(Fixture.class);
        characterObject = mock(Character.class);
        powerUpObject = mock(PowerUpObject.class);

        when(characterFixture.getBody()).thenReturn(characterbody);
        when(powerUpFixture.getBody()).thenReturn(powerUpBody);

    }

    @Test
    void testHandleCollision_ValidPowerUp() {
        when(characterbody.getUserData()).thenReturn(characterObject);
        when(powerUpBody.getUserData()).thenReturn(powerUpObject);
        handler.onContactBegin(contact, characterFixture, powerUpFixture);
        verify(powerUpObject).onPlayerCollide();
    }

    @Test
    void testHandleCollision_InvalidPowerUp() {
        when(characterbody.getUserData()).thenReturn(characterObject);
        when(powerUpBody.getUserData()).thenReturn(new Object()); 
        handler.onContactBegin(contact, characterFixture, powerUpFixture);
        verify(powerUpObject, never()).onPlayerCollide();
    }

    @Test
    void testHandleCollision_CharacterBAndPowerUpA() {
    when(powerUpBody.getUserData()).thenReturn(powerUpObject);
    when(characterbody.getUserData()).thenReturn(characterObject);

    handler.onContactBegin(contact, powerUpFixture, characterFixture);

    verify(powerUpObject).onPlayerCollide();
}
}
