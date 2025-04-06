//package inf112.skeleton.model.utility.listeners;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.verifyNoInteractions;
//import static org.mockito.Mockito.when;
//
//import com.badlogic.gdx.physics.box2d.Contact;
//import com.badlogic.gdx.physics.box2d.Fixture;
//
//import inf112.skeleton.model.items.powerup.PowerUpObject;
//import inf112.skeleton.utility.listeners.PowerUpCollisionHandler;
//
//class PowerUpCollisionHandlerTest {
//
//    private PowerUpCollisionHandler handler;
//    private Contact contact;
//    private Fixture playerFixture;
//    private Fixture powerUpFixture;
//    private PowerUpObject powerUpObject;
//
//    @BeforeEach
//    void setUp() {
//        handler = new PowerUpCollisionHandler();
//        contact = mock(Contact.class);
//        playerFixture = mock(Fixture.class);
//        powerUpFixture = mock(Fixture.class);
//        powerUpObject = mock(PowerUpObject.class);
//    }
//
//    @Test
//    void testHandleCollision_ValidPowerUp() {
//        when(powerUpFixture.getUserData()).thenReturn(powerUpObject);
//        handler.onContactBegin(contact, playerFixture, powerUpFixture);
//        verify(powerUpObject).onPlayerCollide();
//    }
//
//    @Test
//    void testHandleCollision_InvalidPowerUp() {
//        when(powerUpFixture.getUserData()).thenReturn(new Object()); // not a PowerUpObject
//        handler.onContactBegin(contact, playerFixture, powerUpFixture);
//        verifyNoInteractions(powerUpObject);
//    }
//}
