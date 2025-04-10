package inf112.skeleton.model.items.powerup;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.EllipseMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Ellipse;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

import inf112.skeleton.model.character.Character;
import inf112.skeleton.view.screen.GameScreen;

 /**
 * Manages the power-ups in the game.
 * Loads power-ups from the Tiled map, updates their state, and handles their removal.
 */
public class PowerUpManager {
    private final List<PowerUpObject> powerUps = new ArrayList<>();
    private final List<PowerUpObject> removalQueue = new ArrayList<>();

    private final GameScreen screen;
    private final Character character;
    private final World world;
    private final PowerUpFactoryProvider factoryProvider;

    /**
     * Constructor for PowerUpManager.
     *
     * @param screen The game screen where the power-ups are managed.
     * @param character The character that can collect power-ups.
     */

    public PowerUpManager(GameScreen screen, Character character) {
        this.screen = screen;
        this.character = character;
        this.world = screen.getWorld();

        this.factoryProvider = new PowerUpFactoryProvider();

        loadPowerUps();
    }

    /**
    * Loads and initializes power-ups defined in the Tiled map.
    */
private void loadPowerUps() {
    TiledMap map = screen.getMap();
    String[] layers = {"PowerUp", "Diamonds"};

    for (String layerName : layers) {
        if (map.getLayers().get(layerName) == null) continue;

        for (MapObject object : map.getLayers().get(layerName).getObjects()) {
            if (!(object instanceof EllipseMapObject ellipseObj)) continue;

            Ellipse ellipse = ellipseObj.getEllipse();
            Vector2 position = new Vector2(
                (ellipse.x + ellipse.width / 2f) / 16f,
                (ellipse.y + ellipse.height / 2f) / 16f
            );

            String typeStr = object.getProperties().get("type", String.class);
            if (typeStr == null) {
                typeStr = map.getLayers().get(layerName).getProperties().get("type", String.class);
            }

            if (typeStr == null) {
                Gdx.app.error("PowerUpManager", "Missing 'type' property for object in layer: " + layerName);
                continue;
            }

            PowerUpEnum type;
            try {
                type = PowerUpEnum.valueOf(typeStr.toUpperCase());
            } catch (IllegalArgumentException e) {
                Gdx.app.error("PowerUpManager", "Invalid power-up type: '" + typeStr + "' in layer: " + layerName, e);
                continue;
            }

            PowerUpFactory factory = factoryProvider.getFactory(type);
            iPowerUp powerUp = factory.create(character, position);

            Sprite sprite = powerUp.getSprite();
            sprite.setPosition(position.x - sprite.getWidth() / 2f, position.y - sprite.getHeight() / 2f);

            PowerUpObject powerUpObject = new PowerUpObject(screen, object, powerUp, character, sprite);

            powerUps.add(powerUpObject);
        }
    }
}

    /**
    * Updates and safely removes power-ups collected by the player.
    * Called after the physics update step each frame.
    *
    * @param dt Delta time since last frame.
    */
    public void update(float dt) {
        for (PowerUpObject powerUp : removalQueue) {
            if (powerUp.getBody() != null) {
                world.destroyBody(powerUp.getBody());
                powerUp.setBody(null);
            }
            powerUp.getSprite().setAlpha(0f); 
            powerUps.remove(powerUp);
        }
        removalQueue.clear();
    }

    public List<PowerUpObject> getPowerUps() {
        return powerUps;
    }

    /**
    * Marks a power-up object for removal.
    * Actual removal happens in update() method after physics step.
    *
    * @param powerUp The PowerUpObject to remove.
    */
    public void markForRemoval(PowerUpObject powerUp) {
        removalQueue.add(powerUp);
    }

    /**
    * Gets the removal queue.
    *
    * @return The removal queue.
    */
    public List<PowerUpObject> getRemovalQueue() {
        return removalQueue;
    }
    

     /**
     * Renders all power-ups that are not collected.
     * 
     * @param batch
     */
    public void render(SpriteBatch batch) {
        for (PowerUpObject powerUp : getPowerUps()) {
            if (!powerUp.isCollected()) {
                powerUp.getSprite().draw(batch);
            }
        }
    }
}



