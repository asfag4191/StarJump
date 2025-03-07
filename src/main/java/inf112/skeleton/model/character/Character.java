package inf112.skeleton.model.character;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import inf112.skeleton.view.Animator;
import inf112.skeleton.view.Renderable;

/**
 * This class represenst a general character in the game.
 * Examples of children classes can be: Player and different types of Enemies
 */
public class Character extends HumanoidBody implements Renderable {
    public final Animator animator;
    private final String name;
    private Stats stats;
    private float hp;
    public final Vector2 size;
    private CharacterState state;

    public Character(String name, Stats stats, Vector2 size, World world) {
        super(world, size);
        this.animator = new Animator();
        this.name = name;
        this.stats = stats;
        this.hp = stats.maxHp();
        this.size = size;
        this.state = CharacterState.IDLE;
    }

    /**
     * Get name of the character
     * 
     * @return name of character
     */
    public String getName() {
        return name;
    }

    /**
     * Get current health poitns of the character
     * 
     * @return current health points of character
     */
    public float getHp() {
        return hp;
    }

    /**
     * Gets the stats of the character.
     * @return The {@link Stats} associated with this character.
     */
    public Stats getStats() {
        return stats;
    }

    /**
     * Gets the current state of the character
     * @return the state of the character.
     */
    public CharacterState getState() {
        return state;
    }

    /**
     * Sets the stats for the character.
     * @param stats The new {@link Stats} to assign.
     */
    public void setStats(Stats stats) {
        this.stats = stats;
    }

    /**
     * Sets the state of the character to the given value.
     * @param state the state of the character.
     */
    public void setState(CharacterState state) {
        if (this.state == CharacterState.DEAD) return;
        this.state = state;
    }

    /**
     * The character takes a given amount of damage. This method reduces 
     * the number of health points the characer has by <code>damageTaken</code>.
     * If the hp of the character gets to zero, the character state will be set
     * to {@link CharacterState#DEAD} and locked.
     * @param damageTaken the amount to reduce the character's hp by
     */
    public void takeDamage(float damageTaken) {
        hp -= damageTaken;
        if (hp <= 0) {
            hp = 0;
            setState(CharacterState.DEAD);
        }
    }

    @Override
    public void render(Batch batch, float dt) {
        Vector2 bodyPos = this.getTransform().getPosition();
        float bodyDeg = this.getTransform().getRotation();
        TextureRegion nextFrame = animator.update(dt);

        if (nextFrame != null) {
            batch.draw(nextFrame,
                    bodyPos.x - size.x / 2,
                    bodyPos.y - size.y / 2,
                    size.x / 2, size.y / 2,   // Origin
                    size.x, size.y,                         // Width and height
                    1f, 1f,                          // Scale (no scaling)
                    bodyDeg);
        }
    }
}
