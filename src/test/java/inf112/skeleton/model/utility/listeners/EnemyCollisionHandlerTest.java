package inf112.skeleton.model.utility.listeners;

import static org.mockito.Mockito.*;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Filter;

import inf112.skeleton.app.StarJump;
import inf112.skeleton.model.character.Character;
import inf112.skeleton.model.character.enemy.BlackHole;
import inf112.skeleton.utility.listeners.EnemyCollisionHandler;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class EnemyCollisionHandlerTest {

    private EnemyCollisionHandler handler;

    @BeforeEach
    void setUp() {
        handler = new EnemyCollisionHandler();
    }

    /**
     * Helper method that creates a Fixture with an attached Body and optional
     * Filter.
     */
    private Fixture mockFixtureWithBodyAndFilter(Object userData, Short categoryBits) {
        Fixture fixture = mock(Fixture.class);
        Body body = mock(Body.class);
        when(fixture.getBody()).thenReturn(body);
        when(body.getUserData()).thenReturn(userData);

        if (categoryBits != null) {
            Filter filter = new Filter();
            filter.categoryBits = categoryBits;
            when(fixture.getFilterData()).thenReturn(filter);
        } else {
            when(fixture.getFilterData()).thenReturn(null);
        }
        return fixture;
    }

    @Test
    void changeDirectionOnLeftSensorGround() {
        BlackHole blackHole = mock(BlackHole.class);

        Fixture leftSensor = mock(Fixture.class);
        Body blackHoleBody = mock(Body.class);
        when(leftSensor.getUserData()).thenReturn("leftSensor");
        when(leftSensor.getBody()).thenReturn(blackHoleBody);
        when(blackHoleBody.getUserData()).thenReturn(blackHole);

        Fixture ground = mock(Fixture.class);
        Filter groundFilter = new Filter();
        groundFilter.categoryBits = StarJump.GROUND_BIT;
        when(ground.getFilterData()).thenReturn(groundFilter);

        Body groundBody = mock(Body.class);
        when(ground.getBody()).thenReturn(groundBody);

        handler.onContactBegin(mock(Contact.class), leftSensor, ground);

        verify(blackHole, times(1)).changeDirection();
    }

    @Test
    void changesDirectionOnRightSensorCharacter() {
        BlackHole blackHole = mock(BlackHole.class);
        Character character = mock(Character.class);

        Fixture rightSensor = mock(Fixture.class);
        Body blackHoleBody = mock(Body.class);
        when(rightSensor.getUserData()).thenReturn("rightSensor");
        when(rightSensor.getBody()).thenReturn(blackHoleBody);
        when(blackHoleBody.getUserData()).thenReturn(blackHole);

        Fixture characterFixture = mock(Fixture.class);
        Body characterBody = mock(Body.class);
        when(characterFixture.getBody()).thenReturn(characterBody);
        when(characterBody.getUserData()).thenReturn(character);

        Filter dummyFilter = new Filter();
        dummyFilter.categoryBits = 0;
        when(characterFixture.getFilterData()).thenReturn(dummyFilter);

        handler.onContactBegin(mock(Contact.class), rightSensor, characterFixture);

        verify(blackHole, times(1)).changeDirection();
    }

    @Test
    void blackHoleForRemovalOnSensorHit() {
        BlackHole blackHole = mock(BlackHole.class);
        Character character = mock(Character.class);

        Fixture blackHoleSensor = mock(Fixture.class);
        Body blackHoleBody = mock(Body.class);
        when(blackHoleSensor.getUserData()).thenReturn("blackhole_sensor");
        when(blackHoleSensor.getBody()).thenReturn(blackHoleBody);
        when(blackHoleBody.getUserData()).thenReturn(blackHole);

        Fixture characterFixture = mock(Fixture.class);
        Body characterBody = mock(Body.class);
        when(characterFixture.getBody()).thenReturn(characterBody);
        when(characterBody.getUserData()).thenReturn(character);

        handler.onContactBegin(mock(Contact.class), characterFixture, blackHoleSensor);

        verify(blackHole, times(2)).markForRemoval();
    }

    @Test
    void blackHoleAttacksCharacterOnSide() {
        BlackHole blackHole = mock(BlackHole.class);
        Character character = mock(Character.class);

        Fixture blackHoleFixture = mockFixtureWithBodyAndFilter(blackHole, (short) 0);
        Fixture characterFixture = mockFixtureWithBodyAndFilter(character, (short) 0);

        handler.onContactBegin(mock(Contact.class), blackHoleFixture, characterFixture);

        verify(blackHole, times(1)).attack(character);
    }

    @Test
    void doesNothing() {
        Fixture fixA = mockFixtureWithBodyAndFilter(null, (short) 0);
        Fixture fixB = mockFixtureWithBodyAndFilter(null, (short) 0);

        handler.onContactBegin(mock(Contact.class), fixA, fixB);
    }
}