package inf112.skeleton.model.character;

public class SimpleEnemy extends Character{

    public SimpleEnemy(String name, float maxHp, int strength) {
        super(name, maxHp, strength);
    }
    
    @Override
    public void attack(Character target) {
        target.takeDamage(getStrength());
    }
    
}
