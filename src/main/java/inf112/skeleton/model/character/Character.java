package inf112.skeleton.model.character;

/**
 * This abstract class represenst a general character in the game.
 * Examples of children classes can be: Player and different types of Enemies
 */
public abstract class Character {
    private String name;
    private float hp;
    private final float maxHp;
    private int strength;

    public Character(String name, float maxHp, int strength) {
        this.name = name;
        this.maxHp = maxHp;
        this.hp = maxHp;
        this.strength = strength;
    }

    /**
     * Get name of the character
     * 
     * @return name of character
     */
    public String getName() {
        return this.name;
    }

    /**
     * Get current health poitns of the character
     * 
     * @return current health points of character
     */
    public float getHp() {
        return this.hp;
    }

    /**
     * Get maximum health points of the character
     * 
     * @return maximum health points of character
     */
    public float getMaxHp() {
        return this.maxHp;
    }

    /**
     * Get strength of the character
     * 
     * @return strength of character
     */
    public int getStrength() {
        return this.strength;
    }

    /**
     * The character takes a given amount of damage. This method reduces 
     * the number of health points the characer has by <code>damageTaken</code>.
     * It is not possible to give negative damage.
     * 
     * @param damageTaken the amount to reduce the character's hp by
     */
    public void takeDamage(int damageTaken) {
        if (damageTaken > 0) {
            hp -= damageTaken;
        }
        if (hp < 0) {
            hp = 0;
        }
    }

    @Override
    public String toString() {
        return name + "";
    }





}
