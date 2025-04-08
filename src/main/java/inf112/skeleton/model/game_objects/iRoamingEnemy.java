package inf112.skeleton.model.game_objects;

interface iRoamingEnemy {
    /**
     * Sets the roaming state of the enemy.
     *
     * @param roaming A boolean indicating whether the enemy should roam or not.
     */
    void setRoaming(boolean roaming);

    /**
     * Checks if the enemy is currently roaming.
     *
     * @return A boolean indicating whether the enemy is roaming.
     */
    boolean isRoaming();
}
