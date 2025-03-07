package inf112.skeleton.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import inf112.skeleton.model.character.Character;
import inf112.skeleton.model.character.Stats;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.*;


/**
 * Represents the player character in the game.
 * This class extends the Character class and provides
 * player-specific functionality such as jumping and rendering.
 */
public class Player extends Character {
    private static final float JUMP_FORCE = 5f;
    private boolean isGrounded;
    TextureRegion texture = new TextureRegion(new Texture(Gdx.files.internal("star.png")));

    /**
     * Constructs a new Player with the specified size and physics world.
     *
     * @param size  the size of the player (width and height)
     * @param world the Box2D world in which the player exists
     */
    public Player(Vector2 size, World world) {
        super("Star", new Stats(
                        100,
                        2,
                        16,
                        5,
                        1),
                size, world, true);
        //createFootSensor();
    }

    /**
     * Sets the player's position in the world.
     * @param position the new position as a {@link Vector2} (x and y coordinates)
     */
    public void setPosition(Vector2 position) {
        getBody().setTransform(position, getBody().getAngle());
    }

    private Body getBody() {
        return this.body;
    }
    @Override
    public void render(Batch batch, float dt) {
        super.render(batch, dt);
    }
}