package inf112.skeleton.model.character;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import inf112.skeleton.view.Animator;
import inf112.skeleton.view.Renderable;

/**
 * This abstract class represenst a general character in the game.
 * Examples of children classes can be: Player and different types of Enemies
 */
public class Character extends HumanoidBody implements Renderable {
    public final Animator animator;
    private final String name;
    private Stats stats;
    private float hp;
    public final Vector2 size;

    public Character(String name, Stats stats, Vector2 size, World world) {
        super(world, size);
        this.animator = new Animator();
        this.name = name;
        this.stats = stats;
        this.hp = stats.maxHp();
        this.size = size;
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
     * Get maximum health points of the character
     * 
     * @return maximum health points of character
     */
    public float getMaxHp() {
        return stats.maxHp();
    }

    /**
     * Get strength of the character
     * 
     * @return strength of character
     */
    public float getStrength() {
        return stats.strength();
    }

    /**
     * The character takes a given amount of damage. This method reduces 
     * the number of health points the characer has by <code>damageTaken</code>.
     * It is not possible to give negative damage.
     * 
     * @param damageTaken the amount to reduce the character's hp by
     */
    public void takeDamage(float damageTaken) {
        hp -= damageTaken;
        if (hp < 0) {
            hp = 0;
        }
    }

    @Override
    public void render(Batch batch, float dt) {
        Vector2 bodyPos = this.getTransform().getPosition();
        float bodyDeg = this.getTransform().getRotation();
        TextureRegion nextFrame = animator.update(dt);
        batch.draw(nextFrame, bodyPos.x - size.x/2, bodyPos.y - size.y/2, size.x , size.y);
    }
}
