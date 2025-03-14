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

    private void loadPowerUps() {
        TiledMap map = screen.getMap();
    
        if (map.getLayers().get("PowerUp") == null) {
            System.out.println("Error: Layer 'PowerUp' not found!");
            return;
        }
    
        for (MapObject object : map.getLayers().get("PowerUp").getObjects()) {
            if (!(object instanceof EllipseMapObject ellipseObj)) continue;
    
            Ellipse ellipse = ellipseObj.getEllipse();
    
            // Correct Box2D position
            Vector2 position = new Vector2(
                (ellipse.x + ellipse.width / 2f) / 16f, 
                (ellipse.y + ellipse.height / 2f) / 16f
            );
    
            // Properly create AbstractPowerUp from factory (with sprite!)
            AbstractPowerUp flyingPowerUp = factory.createFlyingPowerUp(player, position);
    
            // Use the sprite from AbstractPowerUp directly
            Sprite sprite = flyingPowerUp.getSprite();
            sprite.setPosition(position.x - sprite.getWidth() / 2f, position.y - sprite.getHeight() / 2f);
    
            PowerUpObject powerUpObject = new PowerUpObject(screen, object, flyingPowerUp, player, sprite);
            powerUps.add(powerUpObject);
        }
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
            // Remove the sprite from rendering (e.g., by setting visibility)
            powerUp.getSprite().setAlpha(0f); // or remove from your render list
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



