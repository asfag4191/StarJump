package inf112.skeleton.model.items.powerup;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.EllipseMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Ellipse;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

import inf112.skeleton.model.character.controllable_characters.Player;
import inf112.skeleton.view.screen.GameScreen;

public class PowerUpManager {
    private final List<PowerUpObject> powerUps = new ArrayList<>();
    private final List<PowerUpObject> removalQueue = new ArrayList<>();

    private final GameScreen screen;
    private final Player player;
    private final World world;
    private final PowerUpFactory factory;


    public PowerUpManager(GameScreen screen, Player player) {
        this.screen = screen;
        this.player = player;
        this.world = screen.getWorld();
        this.factory=new PowerUpFactory(screen);
        loadPowerUps();
    }

    /**
    * Loads and initializes power-ups defined in the Tiled map.
    */
    private void loadPowerUps() {
        TiledMap map = screen.getMap();
    
        if (map.getLayers().get("PowerUp") == null) {
            return;
        }
        for (MapObject object : map.getLayers().get("PowerUp").getObjects()) {
            if (!(object instanceof EllipseMapObject ellipseObj)) continue;

            Ellipse ellipse = ellipseObj.getEllipse();
    
            Vector2 position = new Vector2(
                (ellipse.x + ellipse.width / 2f) / 16f, 
                (ellipse.y + ellipse.height / 2f) / 16f
            );
    
            String typeStr = object.getProperties().get("type", String.class);
            if (typeStr == null) {
            typeStr = "FLYING"; // Default value for testing purposes
            }

            PowerUpEnum type = PowerUpEnum.valueOf(typeStr.toUpperCase());
            AbstractPowerUp flyingPowerUp = factory.createFlyingPowerUp(type, player, position);
    
            Sprite sprite = flyingPowerUp.getSprite();
            sprite.setPosition(position.x - sprite.getWidth() / 2f, position.y - sprite.getHeight() / 2f);
    
            PowerUpObject powerUpObject = new PowerUpObject(screen, object, flyingPowerUp, player, sprite);
            powerUps.add(powerUpObject);
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
}



