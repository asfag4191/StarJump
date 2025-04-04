package inf112.skeleton.model.utility.listeners;

import static org.junit.jupiter.api.Assertions.assertFalse;

import inf112.skeleton.model.character.Character;
import inf112.skeleton.model.character.CharacterAttributes;
import inf112.skeleton.model.character.CharacterState;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.badlogic.gdx.backends.headless.HeadlessApplicationConfiguration;
import com.badlogic.gdx.graphics.GL20;
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
    private World world;
    private Character charac;

    @BeforeAll
    static void initLibGdx() {
        new HeadlessApplication(new ApplicationAdapter() {}, new HeadlessApplicationConfiguration());
    }
    
    @BeforeEach
    void setUp() {
        powerUpCollisionHandler = mock(PowerUpCollisionHandler.class);
        listener = new WorldContactListener(powerUpCollisionHandler);

        Gdx.gl = Gdx.gl20 = Mockito.mock(GL20.class);
        world = new World(new Vector2(0, -9.81f), true);
        CharacterAttributes attributes = new CharacterAttributes(3, 1, 1, 5, 1);
        charac = new Character("", attributes, new Vector2(1, 1), world);
        contact = mock(Contact.class);
        fixtureA = mock(Fixture.class);
        fixtureB = mock(Fixture.class);
        when(contact.getFixtureA()).thenReturn(fixtureA);
        when(contact.getFixtureB()).thenReturn(fixtureB);

        groundFilter = new Filter();
        groundFilter.categoryBits = StarJump.GROUND_BIT;

        Filter powerUpFilter = new Filter();
        powerUpFilter.categoryBits = StarJump.POWERUP;
    }

    @Test
    void testPowerUpCollision_PlayerA_PowerUpB() {
        PowerUpObject powerUp = mock(PowerUpObject.class);
        when(fixtureA.getUserData()).thenReturn(charac);
        when(fixtureB.getUserData()).thenReturn(powerUp);
    
        // Mock filter data to avoid NullPointerException
        when(fixtureA.getFilterData()).thenReturn(new Filter());
        when(fixtureB.getFilterData()).thenReturn(new Filter());
    
        listener.beginContact(contact);
    
        verify(powerUpCollisionHandler).onContactBegin(contact, fixtureA, fixtureB);
    }

    @Test
    void testPowerUpCollision_PlayerB_PowerUpA() {
        PowerUpObject powerUp = mock(PowerUpObject.class);
        when(fixtureA.getUserData()).thenReturn(powerUp);
        when(fixtureB.getUserData()).thenReturn(charac);
    
        // Mock filter data to avoid NullPointerException
        when(fixtureA.getFilterData()).thenReturn(new Filter());
        when(fixtureB.getFilterData()).thenReturn(new Filter());
    
        listener.beginContact(contact);
    
        verify(powerUpCollisionHandler).onContactBegin(contact, fixtureB, fixtureA);
    }

    @Test
    void testPlayerLandsOnGround_A() {
        when(fixtureA.getUserData()).thenReturn(charac);
        when(fixtureB.getUserData()).thenReturn(new Object());
        when(fixtureB.getFilterData()).thenReturn(groundFilter);
    
        // To avoid null exception when checking Player
        when(fixtureA.getFilterData()).thenReturn(new Filter());
    
        listener.beginContact(contact);
    
        assertFalse(charac.getState() == CharacterState.FREEFALL, "Player should be grounded after landing.");
    }

    @Test
    void testPlayerLandsOnGround_B() {
        when(fixtureA.getUserData()).thenReturn(new Object());
        when(fixtureB.getUserData()).thenReturn(charac);
        when(fixtureA.getFilterData()).thenReturn(groundFilter);
        when(fixtureB.getFilterData()).thenReturn(new Filter()); // Avoid null
    
        listener.beginContact(contact);
    
        assertFalse(charac.getState() == CharacterState.FREEFALL, "Player should be grounded after landing.");
    }

    @Test
    void testPlayerLeavesGround_A() {
        // First, simulate that the player has landed
        charac.setState(CharacterState.IDLE);
    
        when(fixtureA.getUserData()).thenReturn(charac);
        when(fixtureB.getUserData()).thenReturn(new Object());
        when(fixtureB.getFilterData()).thenReturn(groundFilter);
        when(fixtureA.getFilterData()).thenReturn(new Filter());
    
        listener.endContact(contact);
    
        assertFalse(charac.getState() != CharacterState.FREEFALL, "Player should not be grounded after leaving ground.");
    }

    @Test
    void testPlayerLeavesGround_B() {
        charac.setState(CharacterState.IDLE);
    
        when(fixtureA.getUserData()).thenReturn(new Object());
        when(fixtureB.getUserData()).thenReturn(charac);
        when(fixtureA.getFilterData()).thenReturn(groundFilter);
        when(fixtureB.getFilterData()).thenReturn(new Filter());
    
        listener.endContact(contact);
    
        assertFalse(charac.getState() != CharacterState.FREEFALL, "Player should not be grounded after leaving ground.");
    }
}
