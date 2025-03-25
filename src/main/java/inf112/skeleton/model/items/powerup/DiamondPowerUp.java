package inf112.skeleton.model.items.powerup;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

import inf112.skeleton.model.character.controllable_characters.Player;

/**
 * A power-up representing a collectible diamond that increases the player's score.
 */
public class DiamondPowerUp implements iPowerUp {

    private final Player player;
    private final Sprite sprite;
    private final Vector2 position;
    private final int scoreValue = 10;
    private int score;


    public DiamondPowerUp(Player player, Vector2 position, Sprite sprite) {
        this.player = player;
        this.position = position;
        this.sprite = sprite;
        this.sprite.setPosition(position.x, position.y);
        this.score=score;
    }

    public void addScore(int value) {
        score += value;
    }
    @Override
    public void applyPowerUpEffect() {
    addScore(scoreValue);
    System.out.print("Diamond is collected");
    }

    @Override
    public Sprite getSprite() {
        return sprite;
    }
}