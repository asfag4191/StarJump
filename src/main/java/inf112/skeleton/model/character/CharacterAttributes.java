package inf112.skeleton.model.character;

/**
 * A class that represents the attributes of a character.
 */
public class CharacterAttributes {
    private float maxHp;
    private float jumpPower;
    private int maxJumps;
    private float speed;
    private float strength;

    private float hp;
    private int jumpsLeft;

    /**
     * Constructs a CharacterAttributes object with the given stats.
     *
     * @param maxHp     The maximum health of the character.
     * @param jumpPower The jumping power of the character.
     * @param maxJumps  The maximum number of consecutive jumps.
     * @param speed     The movement speed of the character.
     * @param strength  The strength of the character.
     */
    public CharacterAttributes(float maxHp, float jumpPower, int maxJumps, float speed, float strength) {
        setMaxHp(maxHp);
        setJumpPower(jumpPower);
        setMaxJumps(maxJumps);
        setSpeed(speed);
        setStrength(strength);

        setHp(maxHp);
        setJumpsLeft(maxJumps);
    }

    /**
     * Constructs a CharacterAttributes object by copying another one.
     *
     * @param attributes The CharacterAttributes to copy.
     */
    public CharacterAttributes(CharacterAttributes attributes) {
        this(attributes.getMaxHp(),
             attributes.getJumpPower(),
             attributes.getMaxJumps(),
             attributes.getSpeed(),
             attributes.getStrength()
        );
    }

    /**
     * Gets the maximum health of the character.
     *
     * @return The max hp.
     */
    public float getMaxHp() {
        return maxHp;
    }

    /**
     * Sets the maximum health of the character.
     * The maximum health cannot be non-positive.
     *
     * @param maxHp The new max hp.
     */
    public void setMaxHp(float maxHp) {
        if (maxHp <= 0)
            throw new IllegalArgumentException("maxHp cannot be non-positive");
        this.maxHp = maxHp;
    }

    /**
     * Gets the jumping power of the character.
     *
     * @return The jump power.
     */
    public float getJumpPower() {
        return jumpPower;
    }

    /**
     * Sets the jump power of the character.
     * The jump power cannot be non-positive.
     *
     * @param jumpPower The new jump power.
     */
    public void setJumpPower(float jumpPower) {
        if (jumpPower <= 0)
            throw new IllegalArgumentException("maxHp cannot be non-positive");
        this.jumpPower = jumpPower;
    }

    /**
     * Gets the maximum number of consecutive jumps.
     *
     * @return The max jump count.
     */
    public int getMaxJumps() {
        return maxJumps;
    }

    /**
     * Sets the maximum number of consecutive jumps.
     * The max jump count cannot be negative.
     *
     * @param maxJumps The new maximum jumps count.
     */
    public void setMaxJumps(int maxJumps) {
        if (maxJumps < 0)
            throw new IllegalArgumentException("maxJump cannot be negative");
        this.maxJumps = maxJumps;
    }

    /**
     * Gets the movement speed of the character.
     *
     * @return The speed.
     */
    public float getSpeed() {
        return speed;
    }

    /**
     * Sets the movement speed of the character.
     * The speed cannot be non-positive.
     *
     * @param speed The new speed.
     */
    public void setSpeed(float speed) {
        if (speed <= 0)
            throw new IllegalArgumentException("maxHp cannot be non-positive");
        this.speed = speed;
    }

    /**
     * Gets the strength of the character.
     *
     * @return The strength.
     */
    public float getStrength() {
        return strength;
    }

    /**
     * Sets the strength of the character.
     * Negative strength means, the opposite of inflicting damage, which is healing.
     *
     * @param strength The new strength.
     */
    public void setStrength(float strength) {
        this.strength = strength;
    }

    /**
     * Gets the current hp of the character.
     *
     * @return The current HP.
     */
    public float getHp() {
        return this.hp;
    }

    /**
     * Sets the current hp of the character.
     * The current hp cannot be negative, or exceed max hp.
     *
     * @param hp The new hp.
     */
    public void setHp(float hp) {
        if (this.hp < 0 || this.hp > maxHp)
            throw new IllegalArgumentException("currentHp must be between 0 and the maximum hp");
        this.hp = hp;
    }

    /**
     * Adds to the current hp.
     * The hp will be capped between 0 and max hp.
     *
     * @param amount The amount of hp to add.
     * @return true if the hp is set to 0, false otherwise.
     */
    public boolean addHp(float amount) {
        float hp = Math.max(0, Math.min(this.hp + amount, maxHp));
        setHp(hp);
        return hp == 0;
    }

    /**
     * Gets the number of jumps left.
     *
     * @return The remaining jumps.
     */
    public int getJumpsLeft() {
        return jumpsLeft;
    }

    /**
     * Sets the number of jumps left.
     * The jumps left cannot be negative, or exceed max jumps.
     *
     * @param jumpsLeft The new jumps left.
     */
    public void setJumpsLeft(int jumpsLeft) {
        if (jumpsLeft < 0 || jumpsLeft > maxJumps)
            throw new IllegalArgumentException("jumpsLeft must be between 0 and maxJump");
        this.jumpsLeft = jumpsLeft;
    }

    /**
     * Adds jumps.
     * The jumps will be capped between 0 and max jumps.
     *
     * @param amount The amount of jumps to add.
     */
    public void addJumps(int amount) {
        setJumpsLeft(Math.max(0, Math.min(jumpsLeft + amount, maxJumps)));
    }

    @Override
    public String toString() {
        return "CharacterAttributes{" +
                "maxHp=" + maxHp +
                ", jumpPower=" + jumpPower +
                ", maxJumps=" + maxJumps +
                ", speed=" + speed +
                ", strength=" + strength +
                ", hp=" + hp +
                ", jumpsLeft=" + jumpsLeft +
                '}';
    }
}
