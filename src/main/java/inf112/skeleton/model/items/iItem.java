package inf112.skeleton.model.items;

/**
 * The {@code iItem} interface represents an item in the game that can be 
 * updated and disposed of.
 * This interface is implemented by all game items that need to be updated periodically 
 * and disposed of when no longer needed.
 */
public interface iItem {

    /**
     * Updates the item each frame. This method is called to update the state of the item.
     *
     * @param dt The delta time (time passed since the last update) used to update the item.
     */
    void update(float dt);

     /**
     * Disposes of the item, releasing any resources associated with it.
     * This method is called when the item is no longer needed.
     */
    void dispose();

}
