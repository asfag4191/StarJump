package inf112.skeleton.model.character.enemy;

import inf112.skeleton.model.character.Character;

interface iMovingEnemy {

    /**
     * Attack <code>target</code> using <code>this</code> enemyCharacter's strength
     */
    public void attack(Character target);

    /**
     * Sets the moving state of the enemy.
     *
     * @param roaming A boolean indicating whether the enemy should move or not.
     */
    void setMoving(boolean moving);

    /**
     * Checks if the enemy is currently moving.
     *
     * @return A boolean indicating whether the enemy is moving.
     */
    boolean isMoving();
}
