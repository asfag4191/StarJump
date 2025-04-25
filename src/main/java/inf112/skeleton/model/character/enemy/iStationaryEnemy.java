package inf112.skeleton.model.character.enemy;

import com.badlogic.gdx.math.Vector2;

interface iStationaryEnemy {

    /**
     * Fires projectile in direction
     *
     * @param point The target position as a Vector2.
     */
    void shoot(Vector2 target, float bulletSpeed);

    /**
     * Checks if the enemy can see the target.
     *
     * @param PlayerPos
     */
    boolean seesTarget(Vector2 playerPos);
}
