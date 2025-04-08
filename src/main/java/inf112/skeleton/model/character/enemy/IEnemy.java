package inf112.skeleton.model.character.enemy;

import inf112.skeleton.model.character.Character;

public interface IEnemy {

    /**
     * Attack <code>target</code> using <code>this</code> BlackHole's strength
     */
    public void attack(Character target);
}
