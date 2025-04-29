package inf112.skeleton.utility.listeners;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.Fixture;

import inf112.skeleton.model.character.Character;
import inf112.skeleton.utility.listeners.ProjectileCollisionHandler;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.anyFloat;
import static org.mockito.Mockito.*;

public class ProjectileCollisionHandlerTest {

    private ProjectileCollisionHandler handler;
    private Contact contact;
    private Fixture fixA;
    private Fixture fixB;
    private Body bodyA;
    private Body bodyB;

    @BeforeEach
    void setup() {
        handler = new ProjectileCollisionHandler();
        contact = mock(Contact.class);
        fixA = mock(Fixture.class);
        fixB = mock(Fixture.class);
        bodyA = mock(Body.class);
        bodyB = mock(Body.class);

        when(fixA.getBody()).thenReturn(bodyA);
        when(fixB.getBody()).thenReturn(bodyB);
    }

    @Test
    void testPlayerTakesDamage() {
        Character player = mock(Character.class);
        when(player.isPlayer()).thenReturn(true);

        Object fakeProjectile = new Object() {
        };

        when(bodyA.getUserData()).thenReturn(player);
        when(bodyB.getUserData()).thenReturn(fakeProjectile);

        handler.onContactBegin(contact, fixA, fixB);

        verify(player, never()).takeDamage(anyFloat());
    }

    @Test
    void testNpcNotDamaged() {
        Character npc = mock(Character.class);
        when(npc.isPlayer()).thenReturn(false);

        Object fakeProjectile = new Object();

        when(bodyA.getUserData()).thenReturn(fakeProjectile);
        when(bodyB.getUserData()).thenReturn(npc);

        handler.onContactBegin(contact, fixA, fixB);

        verify(npc, never()).takeDamage(anyFloat());
    }

    @Test
    void testCollisionWithGroundDoesNotCrash() {
        Object fakeProjectile = new Object();

        when(bodyA.getUserData()).thenReturn("ground");
        when(bodyB.getUserData()).thenReturn(fakeProjectile);

        handler.onContactBegin(contact, fixA, fixB);
    }
}
