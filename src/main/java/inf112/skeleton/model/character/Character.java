package inf112.skeleton.model.character;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
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
public abstract class Character extends HumanoidBody implements Renderable {
    public Animator animator;
    public TextureRegion texture;
    private final String name;
    private Stats stats;  // Change from private to protected
    private float hp;
    public final Vector2 size;
    private final boolean isStatic;

    public Character(String name, Stats stats, Vector2 size, World world, boolean isStatic) {
        super(world, size);
        this.name = name;
        this.stats = stats;
        this.hp = stats.maxHp();
        this.size = size;
        this.isStatic = isStatic;
        this.animator = new Animator();
        this.texture = new TextureRegion(new Texture(Gdx.files.internal("star.png")));
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
    public Stats getStats() {
        return stats;
    }

    public void setStats(Stats stats) {
        this.stats = stats;
    
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
        TextureRegion star = texture;
        if (isStatic) {
            batch.draw(star,
                    bodyPos.x - size.x / 2,
                    bodyPos.y - size.y / 2,
                    size.x, size.y);
        } else {
            TextureRegion nextFrame = animator.update(dt);
            batch.draw(nextFrame,
                    bodyPos.x - size.x / 2,
                    bodyPos.y - size.y / 2,
                    size.x / 2, size.y / 2,   // Origin
                    size.x, size.y,                         // Width and height
                    1f, 1f,                          // Scale (no scaling)
                    bodyDeg);
        }
    }

    public Vector2 getPosition() {
        return this.getTransform().getPosition();
    }

}
