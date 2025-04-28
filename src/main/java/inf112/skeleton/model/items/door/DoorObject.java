package inf112.skeleton.model.items.door;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import inf112.skeleton.app.StarJump;
import inf112.skeleton.model.character.controllable_characters.Player;
import inf112.skeleton.model.items.InteractiveTileObject;
import inf112.skeleton.view.screen.GameScreen;

/**
 * Represents a door object in the game world.
 * 
 * A door can detect when the player is standing at its middle and triggers an event.
 * 
 */
public class DoorObject extends InteractiveTileObject {

    private final Player player;
    private final Rectangle bounds;
    private static boolean triggered = false;


     /**
     * Creates a new {@code DoorObject} linked to a given {@link GameScreen} and {@link Player}.
     *
     * @param screen the game screen this door belongs to
     * @param object the TiledMap object representing the door (must be a {@link RectangleMapObject})
     * @param player the player character interacting with the door
     * @throws IllegalArgumentException if the provided {@code object} is not a {@code RectangleMapObject}
     */
    public DoorObject(GameScreen screen, MapObject object, Player player) {
        super(screen, object, StarJump.DOOR_BIT);
        this.player = player;

        if (object instanceof RectangleMapObject rectObj) {
            this.bounds = rectObj.getRectangle();
        } else {
            throw new IllegalArgumentException("Door must be RectangleMapObject");
        }
    }

    @Override
    public void onPlayerCollide() {
    }

    @Override
    public void update(float delta) {
    }

    @Override
    public void dispose() {
    }

     /**
     * Checks if the player is positioned near the center of the door.
     * 
     * If the player is close enough, triggers the door.
     * 
     */
    public void checkPlayerAtMiddle() {
        if (triggered) return; 

        Vector2 playerPos = player.character.getTransform().getPosition();

        float doorCenterX = (bounds.x + bounds.width / 2f) / 16f;
        float doorBottomY = bounds.y / 16f;

        float marginX = 0.8f;
        float marginY = 0.8f;

        boolean inMiddle =
                Math.abs(playerPos.x - doorCenterX) < marginX &&
                Math.abs(playerPos.y - doorBottomY) < marginY;

        if (inMiddle) {
            triggered = true;
        }
    }

     /**
     * Checks whether the door trigger has been activated.
     *
     * @return {@code true} if the door has been triggered; {@code false} otherwise
     */
    public static boolean isTriggered() {
        return triggered;
    }

     /**
     * Resets the triggered state of the door.
     * 
     * Useful for re-enabling door detection after a level reload or restart.
     * 
     */
    public static void resetTrigger() { triggered = false; }
}
