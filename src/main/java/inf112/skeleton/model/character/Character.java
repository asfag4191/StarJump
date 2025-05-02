package inf112.skeleton.model.character;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import inf112.skeleton.app.StarJump;
import inf112.skeleton.view.Animator;
import inf112.skeleton.view.Renderable;

/**
 * Represents a generic character in the game with full support for
 * animation, movement, physics-based interactions, and state control.
 */
public class Character extends HumanoidBody implements Renderable {
    private final String name;
    private CharacterState state;
    private final CharacterAttributes attributes;
    private final Animator animator;
    private final Vector2 size;
    private boolean isPlayer = false;

    public Character(String name, CharacterAttributes attributes, Vector2 size, World world) {
        super(world, size);
        this.name = name;
        this.attributes = attributes;
        this.state = CharacterState.IDLE;
        this.animator = new Animator();
        this.size = size;
        this.body.setUserData(this);
        setupGroundSensor(this.body, size);
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
     * Gets the current state of the character
     *
     * @return the state of the character.
     */
    public CharacterState getState() {
        return state;
    }

    /**
     * Gets the character's attributes
     *
     * @return the {@link CharacterAttributes} of this character.
     */
    public CharacterAttributes getAttributes() {
        return attributes;
    }

    /**
     * Gets the {@link Animator} associated with this character.
     *
     * @return the {@link Animator} instance for this character.
     */
    public Animator getAnimator() {
        return animator;
    }

    /**
     * Gets the size of the character in world units.
     *
     * @return the character's size as a {@link Vector2}.
     */
    public Vector2 getSize() {
        return size;
    }

    /**
     * Sets the state of the character to the given value.
     * If the character is dead, then this method will do nothing.
     * If the character is freefalling, then it cannot be set to moving.
     *
     * @param state the state of the character.
     */
    public void setState(CharacterState state) {
        if (this.state == CharacterState.DEAD)
            return;
        if (this.state == CharacterState.FREEFALL && state == CharacterState.MOVING)
            return;
        this.state = state;
    }

    /**
     * Reduces the number of health points the characer has by
     * <code>damageTaken</code>.
     * If character's hp reaches zero, the character state will be set
     * to {@link CharacterState#DEAD} and locked.
     *
     * @param damageTaken the amount to reduce the character's hp by
     */
    public void takeDamage(float damageTaken) {
        if (this.getAttributes().addHp(-damageTaken)) {
            setState(CharacterState.DEAD);
        }
    }

    /**
     * Changes the character's state based on the grounded status.
     * <ul>
     * <li>If {@code false}, the character's state is set to
     * {@link CharacterState#FREEFALL}.</li>
     * <li>If {@code true}, the character's state is set to
     * {@link CharacterState#IDLE}, and
     * the remaining jumps are reset to the maximum allowed.</li>
     * </ul>
     *
     * @param isGrounded {@code true} if the character is on the ground,
     *                   {@code false} otherwise.
     */
    public void setGrounded(boolean isGrounded) {
        if (isGrounded) {
            this.getAttributes().setJumpsLeft(getAttributes().getMaxJumps());
            setState(CharacterState.IDLE);
        } else {
            setState(CharacterState.FREEFALL);
        }
    }

    @Override
    public void render(Batch batch, float dt) {
        Vector2 bodyPos = this.getTransform().getPosition();
        float bodyRad = this.getTransform().getRotation();
        TextureRegion nextFrame = getAnimator().update(dt);

        if (nextFrame != null) {
            batch.draw(nextFrame,
                    bodyPos.x - getSize().x / 2,
                    bodyPos.y - getSize().y / 2,
                    getSize().x / 2, getSize().y / 2, // Origin
                    getSize().x, getSize().y, // Width and height
                    1f, 1f, // Scale (no scaling)
                    (float) Math.toDegrees(bodyRad));
        }
    }

    private static void setupGroundSensor(Body mainBody, Vector2 bodySize) {
        // Sensor properties
        float width = bodySize.x;
        float widthAdjustmentScale = 0.05f; // to avoid contact from the sides.
        float yOffset = -(bodySize.y / 2);

        // Sensor shape setup
        EdgeShape sensorShape = new EdgeShape();
        sensorShape.set(
                new Vector2(-width * (1 - widthAdjustmentScale) / 2, yOffset),
                new Vector2(width * (1 - widthAdjustmentScale) / 2, yOffset));

        // Sensor fixture setup
        FixtureDef fixDef = new FixtureDef();
        fixDef.shape = sensorShape;
        fixDef.isSensor = true;
        fixDef.density = 0;
        fixDef.filter.categoryBits = StarJump.GROUND_SENSOR_BIT;
        fixDef.filter.maskBits = StarJump.GROUND_BIT;

        // Add the fixture to the body
        Fixture sensorFixture = mainBody.createFixture(fixDef);
        sensorFixture.setUserData("sensor");
        sensorShape.dispose();
    }

    /**
     * Gets the position of the character in the world.
     */
    public Vector2 getPosition() {
        return this.getTransform().getPosition();
    }

    /**
     * Marks this character as a player
     * 
     * @param isPlayer if the character should be a player or not
     */
    public void setPlayer(boolean isPlayer) {
        this.isPlayer = isPlayer;
    }

    /**
     * Wether this character is a player or not
     * 
     * @return {@code true} if the character is a player, else {@code false}
     */
    public boolean isPlayer() {
        return isPlayer;
    }
}
