package inf112.skeleton.model.items.door;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;

import inf112.skeleton.view.screen.GameScreen;

public class doorManager {
    private final List<doorObject> doors = new ArrayList<>();
    private final GameScreen screen;

    public doorManager(GameScreen screen) {
        this.screen = screen;
        loadDoors();
    }

    private void loadDoors() {
        TiledMap map = screen.getMap();
        if (map.getLayers().get("Door") == null) return;

        for (MapObject object : map.getLayers().get("Door").getObjects()) {
            doors.add(new doorObject(screen, object, screen.getPlayer()));
        }
    }

    public List<doorObject> getDoors() {
        return doors;
    }

    public void update(float dt) {
        for (doorObject door : doors) {
            door.checkPlayerAtMiddle();
        }
    }
}
