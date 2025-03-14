package inf112.skeleton.model.items.powerup;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

import inf112.skeleton.model.character.controllable_characters.Player;
import inf112.skeleton.view.screen.GameScreen;


public class PowerUpFactory {
    private final GameScreen screen;

    public PowerUpFactory(GameScreen screen) {
        this.screen = screen;

    }

    public AbstractPowerUp createFlyingPowerUp(Player player, Vector2 position) {
        Texture texture = new Texture("map/tilemaps/tilesets/rainbow16.png"); // Load PNG directly
        Sprite sprite = new Sprite(texture);

        sprite.setSize( 1, 1); // Set sprite size to 1x1 tiles
        
        return new FlyingPowerUp(player, position, sprite);
    }
}
