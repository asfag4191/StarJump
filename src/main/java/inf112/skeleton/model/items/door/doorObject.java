package inf112.skeleton.model.items.door;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import inf112.skeleton.app.StarJump;
import inf112.skeleton.model.character.controllable_characters.Player;
import inf112.skeleton.model.items.InteractiveTileObject;
import inf112.skeleton.view.screen.GameScreen;

public class DoorObject extends InteractiveTileObject {

    private final Player player;
    private final Rectangle bounds;
    private boolean triggered = false;

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
        // Not needed, we use position check instead
    }

    @Override
    public void update(float delta) {
        // Not used
    }

    @Override
    public void dispose() {
        // Nothing extra to dispose
    }

    public void checkPlayerAtMiddle() {
        if (triggered) return; // Only trigger once

        Vector2 playerPos = player.getBody().getPosition();

        // Calculate center X and bottom Y
        float doorCenterX = (bounds.x + bounds.width / 2f) / 16f;
        float doorBottomY = bounds.y / 16f;

        float marginX = 0.8f;
        float marginY = 0.8f;

        boolean inMiddle =
                Math.abs(playerPos.x - doorCenterX) < marginX &&
                Math.abs(playerPos.y - doorBottomY) < marginY;

        if (inMiddle) {
            System.out.println("Player is detecting the door!");
            triggered = true;
            // Optional: Trigger level complete here
        }
    }
    public boolean isTriggered() {
        return triggered;
    }
}
