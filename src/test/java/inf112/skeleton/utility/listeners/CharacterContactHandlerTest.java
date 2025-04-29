package inf112.skeleton.utility.listeners;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.Fixture;
import inf112.skeleton.app.StarJump;
import inf112.skeleton.model.character.Character;
import inf112.skeleton.model.character.enemy.BlackHole;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class CharacterContactHandlerTest {

    private CharacterContactHandler handler;
    private Contact contact;
    private Fixture sensorFixture;
    private Fixture groundFixture;
    private Body sensorBody;
    private Character character;

    @BeforeEach
    void setUp() {
        handler = new CharacterContactHandler();
        contact = mock(Contact.class);
        sensorFixture = mock(Fixture.class);
        groundFixture = mock(Fixture.class);
        sensorBody = mock(Body.class);
        Body groundBody = mock(Body.class);
        character = mock(Character.class);

        // Setup filter data
        Filter sensorFilter = new Filter();
        sensorFilter.categoryBits = StarJump.GROUND_SENSOR_BIT;

        Filter groundFilter = new Filter();
        groundFilter.categoryBits = StarJump.GROUND_BIT;

        when(sensorFixture.getFilterData()).thenReturn(sensorFilter);
        when(groundFixture.getFilterData()).thenReturn(groundFilter);

        when(sensorFixture.getBody()).thenReturn(sensorBody);
        when(groundFixture.getBody()).thenReturn(groundBody);
    }

    @Test
    void testOnContactBegin_SetsCharacterGroundedTrue() {
        when(sensorBody.getUserData()).thenReturn(character);

        handler.onContactBegin(contact, sensorFixture, groundFixture);

        verify(character).setGrounded(true);
    }

    @Test
    void testOnContactEnded_SetsCharacterGroundedFalse() {
        when(sensorBody.getUserData()).thenReturn(character);

        handler.onContactEnded(contact, sensorFixture, groundFixture);

        verify(character).setGrounded(false);
    }

    @Test
    void testOnContactBegin_IgnoresInvalidCollision() {
        Filter nonSensorFilter = new Filter();
        nonSensorFilter.categoryBits = 0x0002; // not a sensor or ground

        when(sensorFixture.getFilterData()).thenReturn(nonSensorFilter);
        when(groundFixture.getFilterData()).thenReturn(nonSensorFilter);

        handler.onContactBegin(contact, sensorFixture, groundFixture);

        verify(character, never()).setGrounded(anyBoolean());
    }

    @Test
    void testOnContactBegin_WithBlackHoleUserData() {
        BlackHole blackHole = mock(BlackHole.class);
        when(blackHole.getCharacter()).thenReturn(character);
        when(sensorBody.getUserData()).thenReturn(blackHole);

        handler.onContactBegin(contact, sensorFixture, groundFixture);

        verify(character).setGrounded(true);
    }

    @Test
    void testOnContactBegin_WithInvalidUserData() {
        when(sensorBody.getUserData()).thenReturn("NotACharacter");

        handler.onContactBegin(contact, sensorFixture, groundFixture);

        verify(character, never()).setGrounded(anyBoolean());
    }
}
