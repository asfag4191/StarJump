package inf112.skeleton.model.items.powerup;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

import inf112.skeleton.model.character.controllable_characters.Player;
import inf112.skeleton.view.screen.GameScreen;

/**
 * Factory for creating specific power-up objects.
 * Simplifies the process of adding new power-ups in the future.
 */
public class PowerUpFactory {
    private final GameScreen screen;

    public PowerUpFactory(GameScreen screen) {
        this.screen = screen;
    }

    /**
     * Creates an iPowerUp instance based on given enum type.
     *
     * @param type     The PowerUpEnum type.
     * @param player   Player who receives the power-up effect.
     * @param position The position where the power-up appears.
     * @return Instance of specified AbstractPowerUp.
     */
    public iPowerUp createPowerUp(PowerUpEnum type, Player player, Vector2 position) {
        Texture texture;
        return switch (type) {
            case FLYING -> {
                texture = new Texture("map/tilemaps/tilesets/rainbow16.png");
                Sprite sprite = new Sprite(texture);
                sprite.setSize(1, 1);
                sprite.setPosition(position.x, position.y);
                yield new FlyingPowerUp(player, position, sprite);
            }
            default -> throw new IllegalArgumentException("Unknown power-up type: " + type);
            case DIAMOND -> {
                texture = new Texture("map/tilemaps/tilesets/Diamond.png");
                Sprite sprite = new Sprite(texture);
                sprite.setSize(1, 1);
                sprite.setPosition(position.x, position.y);
                yield new DiamondPowerUp(player, position, sprite);
            }
        };
    }
}
