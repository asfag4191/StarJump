package inf112.skeleton.model.items.door;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;

import inf112.skeleton.view.screen.GameScreen;

/**
 * Manages the door objects in the game.
 */
public class DoorManager {
    private final List<DoorObject> doors = new ArrayList<>();
    private final GameScreen screen;

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

    public List<DoorObject> getDoors() {
        return doors;
    }

    public void update(float dt) {
        for (DoorObject door : doors) {
            door.checkPlayerAtMiddle();
        }
    }
}
