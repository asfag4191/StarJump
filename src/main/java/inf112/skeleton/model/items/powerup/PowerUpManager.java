package inf112.skeleton.model.items.powerup;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.EllipseMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.physics.box2d.World;

import inf112.skeleton.model.character.controllable_characters.Player;
import inf112.skeleton.view.screen.GameScreen;

public class PowerUpManager {
    private final List<PowerUpObject> powerUps = new ArrayList<>();
    private final List<PowerUpObject> removalQueue = new ArrayList<>();

    private final GameScreen screen;
    private final Player player;
    private final World world;


    public PowerUpManager(GameScreen screen, Player player) {
        this.screen = screen;
        this.player = player;
        this.world = screen.getWorld();
        loadPowerUps();
    }

    private void loadPowerUps() {
        TiledMap map = screen.getMap();
        World world = screen.getWorld();

        // Check if layer exists
        if (map.getLayers().get("PowerUp") == null) {
            System.out.println("Error: Layer 'PowerUp' not found!");
            return;
        }

        // Create power-ups for each ellipse object in the Tiled map
        for (MapObject object : map.getLayers().get("PowerUp").getObjects()) {
            if (!(object instanceof EllipseMapObject)) {
                System.out.println("Skipping non-ellipse object: " + object);
                continue;
            }


            PowerUpObject powerUp = new PowerUpObject(screen, object, player);
            powerUps.add(powerUp);

            System.out.println("Power-Up Created at " + object.getProperties());
        }
        System.out.println("Total Power-Ups: " + powerUps.size());
    }

    /**
     * Called every frame (after world.step(...)).
     * This is where we safely remove the Box2D bodies for collected power-ups.
     */
    public void update(float dt) {
        for (PowerUpObject powerUp : removalQueue) {
            if (powerUp.getBody() != null) {
                world.destroyBody(powerUp.getBody());
                powerUp.setBody(null);
            }
            powerUps.remove(powerUp);
        }
        removalQueue.clear();
    }

    public List<PowerUpObject> getPowerUps() {
        return powerUps;
    }

    /**
     * Defer removal so that Box2D bodies are only destroyed after the physics step.
     */
    public void markForRemoval(PowerUpObject powerUp) {
        removalQueue.add(powerUp);
    }
}



