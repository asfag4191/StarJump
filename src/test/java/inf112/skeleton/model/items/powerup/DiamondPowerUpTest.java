package inf112.skeleton.model.items.powerup;

import static org.junit.jupiter.api.Assertions.assertEquals;

import inf112.skeleton.model.character.Character;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

import inf112.skeleton.model.character.controllable_characters.Player;

class DiamondPowerUpTest {

    private Character mockCharacter;
    private Sprite mockSprite;
    private DiamondPowerUp diamondPowerUp;

    @BeforeEach
    void setUp() {
        Texture texture = mock(Texture.class); 
        mockSprite = new Sprite(texture);
        mockSprite.setSize(1, 1);

        DiamondPowerUpTestHelper.resetScore();

        Vector2 position = new Vector2(10, 20);
        diamondPowerUp = new DiamondPowerUp(mockCharacter, position, mockSprite);
    }

    @Test
    void testApplyPowerUpEffectIncreasesScore() {
        assertEquals(0, DiamondPowerUp.getScore(), "Initial score should be 0");
        diamondPowerUp.applyPowerUpEffect();
        assertEquals(10, DiamondPowerUp.getScore(), "Score should increase by 10 after collecting one diamond");
    }

    @Test
    void testMultipleDiamondsIncreaseScore() {
        DiamondPowerUp another = new DiamondPowerUp(mockCharacter, new Vector2(0, 0), new Sprite(mock(Texture.class)));
        diamondPowerUp.applyPowerUpEffect();
        another.applyPowerUpEffect();
        assertEquals(20, DiamondPowerUp.getScore(), "Two diamonds should give 20 score");
    }

    @Test
    void testSpritePositionIsSet() {
        assertEquals(10, mockSprite.getX(), 0.01);
        assertEquals(20, mockSprite.getY(), 0.01);
    }

    // Helper class to reset static score for isolation
    static class DiamondPowerUpTestHelper {
        static void resetScore() {
            try {
                var field = DiamondPowerUp.class.getDeclaredField("score");
                field.setAccessible(true);
                field.set(null, 0);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
