package inf112.skeleton.model.items.powerup;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

import inf112.skeleton.model.character.Character;

/**
 * A power-up representing a collectible diamond that increases the player's score.
 * 
 * When collected, this power-up adds a fixed score value to the global score.
 * This class implements {@link iPowerUp} for unified interaction with the power-up system.
 */
public class DiamondPowerUp implements iPowerUp {
   private final Character character;
    private final Sprite sprite;
    private final Vector2 position;
    private final int scoreValue = 10;
    private static int score;


     /**
     * Constructs a DiamondPowerUp.
     *
     * @param character   The character collecting the diamond.
     * @param position The position where the diamond appears.
     * @param sprite   The graphical representation of the diamond.
     */
    public DiamondPowerUp(Character character, Vector2 position, Sprite sprite) {
        this.character = character;
        this.position = position;
        this.sprite = sprite;
        this.sprite.setPosition(position.x, position.y);
        this.score=score;
    }
    
    /**
     * Increases the global score by the specified value.
     *
     * @param value The amount of score to add.
     */
    public void addScore(int value) {
        score += value;
    }

    /**
     * Gets the current score accumulated from all diamonds.
     *
     * @return The total diamond score.
     */
    public static int getScore() {
        return score;
    }

    @Override
    public void applyPowerUpEffect() {
        addScore(scoreValue);
        System.out.println("Diamond collected! Current score: " + score); // Debug
    }

    @Override
    public Sprite getSprite() {
        return sprite;
    }

    public static void disposeScore() { score = 0; }
}