package inf112.skeleton.model.character.enemy;

import inf112.skeleton.model.character.Character;

interface iMovingEnemy {

    /**
     * Attack <code>target</code> using <code>this</code> enemyCharacter's strength
     */
    public void attack(Character target);

    /**
     * Moves the enemy in <code>this<\code> enemy's direction, if possible.
     */
    public void move();
}
