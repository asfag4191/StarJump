package inf112.skeleton.model.character.enemy;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

import inf112.skeleton.model.character.Character;

public abstract class SimpleEnemy {
    public final Character enemyCharacter;
    public World world;
    private Vector2 target;

    public SimpleEnemy(Character enemy, World world) {
        this.enemyCharacter = enemy;
        this.world = world;
        setupAnimation();
    }

    /**
     * Get the SimpleEnemy's target
     * 
     * @return Vector2 representing the target's position
     */
    public Vector2 getTarget() {
        return target;
    }

    /**
     * Sets the SimpleEnemy's target.
     * 
     * @param point Vector2 representing the target's position
     */
    public void setTarget(Vector2 point) {
        target = point;
    }

    /**
     * Update the SimpleEnemy's behaviour 
     * after time <code>dt</code> has passed.
     * 
     * E.g. if the SimpleEnemy should be moving, the 
     * SimpleEnemy's <code>move()</code> method should be called here.
     * 
     * @param dt delta time 
     */
    public void update(float dt) {
    }

    /**
     * Render the SimpleEnemy.
     *  
     * @param batch The game's Batch
     * @param dt delta time
     */
    public void render(Batch batch, float dt) {
        enemyCharacter.render(batch, dt);
    }

    /**
     * Sets up the SimpleEnemy's <code>Character.animator</code>,
     * for animations to play when the game is running.
     * 
     * Needs to be implemented by every sub class of SimpleEnemy.
     */
    protected abstract void setupAnimation();

}
