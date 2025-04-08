package inf112.skeleton.model.character.enemy;

import com.badlogic.gdx.math.Vector2;

interface iStationaryEnemy {

    /**
     * Fires projectile in direction
     *
     * @param point The target position as a Vector2.
     */
    void shoot(Vector2 direction, float bulletSpeed);

    /**
     * Checks if the enemy can see the target.
     *
     * @param targetPosition The position of the target as a Vector2.
     */
    boolean seesTarget(Vector2 targetPosition);
}
