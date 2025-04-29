package inf112.skeleton.model.character.enemy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.badlogic.gdx.backends.headless.HeadlessApplicationConfiguration;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.BeforeAll;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import inf112.skeleton.model.character.Character;
import inf112.skeleton.model.character.CharacterAttributes;

public class BlackHoleTest {
    CharacterAttributes attributes = new CharacterAttributes(1, 1, 0, 3.5f, 1);
    World world = new World(new Vector2(0, -9.81f), true);
    BlackHole blackHole;


    @BeforeAll
    static void initGdx() {
        if (Gdx.app == null) {
            new HeadlessApplication(new ApplicationListener() {
                public void create() {
                }

                public void resize(int width, int height) {
                }

                public void render() {
                }

                public void pause() {
                }

                public void resume() {
                }

                public void dispose() {
                }
            }, new HeadlessApplicationConfiguration());
        }
        Gdx.gl = mock(GL20.class);
        Gdx.gl20 = Gdx.gl;
    }

    @BeforeEach
    void setUpBeforeEach() {
        this.blackHole = new BlackHole(new Character("bh", attributes, new Vector2(1, 1), world));
    }

    @Test
    void testBlackHoleInitialization() {
        assertNotNull(blackHole);
        assertTrue(blackHole.getCharacter().getName().equals("bh"));
    }

    @Test
    void testMoveAfterChangedDirection() {
        blackHole.move();
        CharacterAttributes attributes = blackHole.getCharacter().getAttributes();
        float xVelocity = attributes.getSpeed();
        float yVelocity = blackHole.getCharacter().getVelocity().y;

        Vector2 expectedVelocity = new Vector2(xVelocity, yVelocity);
        assertEquals(blackHole.getCharacter().getVelocity(), expectedVelocity);

        blackHole.changeDirection();
        blackHole.move();
        float xVelocityNew = attributes.getSpeed() * -1;
        float yVelocityNew = blackHole.getCharacter().getVelocity().y;

        Vector2 newExpectedVelocity = new Vector2(xVelocityNew, yVelocityNew);
        assertEquals(blackHole.getCharacter().getVelocity(), newExpectedVelocity);

    }

}
