package inf112.skeleton.model;

/**
 * Something that updates every frame (like game objects).
 */
public interface iUpdateable {

    /**
     * Called every frame to update the object.
     *
     * @param dt time since last frame
     */
    void update(float dt);
}
