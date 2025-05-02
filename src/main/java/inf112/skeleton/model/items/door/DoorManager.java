package inf112.skeleton.model.items.door;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;

import inf112.skeleton.view.screen.GameScreen;

/**
 * Manages all door objects within the game.
 * 
 * Loads doors from the Tiled map, updates them, and provides access to the door list.
 * 
 */
public class DoorManager {
    private final List<DoorObject> doors = new ArrayList<>();
    private final GameScreen screen;

    /**
     * Constructs a {@code DoorManager} linked to a specific {@link GameScreen}.
     *
     * @param screen The game screen where doors are managed
     */
    public DoorManager(GameScreen screen) {
        this.screen = screen;
        loadDoors();
    }

    private void loadDoors() {
        TiledMap map = screen.getMap();
        if (map.getLayers().get("Door") == null) return;

        for (MapObject object : map.getLayers().get("Door").getObjects()) {
            doors.add(new DoorObject(screen, object, screen.getPlayer()));
        }
    }

    /**
     * Returns the list of all doors managed by this manager.
     *
     * @return a list of {@link DoorObject} instances
     */
    public List<DoorObject> getDoors() {
        return doors;
    }

    /**
     * Updates all doors by checking if the player is at the center of any door.
     *
     * @param dt The delta time since the last update (not used directly here)
     */
    public void update(float dt) {
        for (DoorObject door : doors) {
            door.checkPlayerAtMiddle();
        }
    }

     /**
     * Returns the first door in the list, or {@code null} if no doors exist.
     *
     * @return the first {@link DoorObject} or {@code null} if none are present
     */
    public DoorObject getDoor() {
        return doors.isEmpty() ? null : doors.get(0);
    }
}
