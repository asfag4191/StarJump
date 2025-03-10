package inf112.skeleton.model.items.powerup;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.Shape;

import inf112.skeleton.model.Player;
import inf112.skeleton.model.colliders.RigidBody;
import inf112.skeleton.view.Renderable;
import inf112.skeleton.view.screen.GameScreen;

/**
 * Abstract class for power-ups in the game.
 */
public abstract class AbstractPowerUp extends RigidBody implements Renderable {
    protected GameScreen screen;
    protected Player player;
    private MapObject mapObject;  // Reference to Tiled Map object
    protected boolean isRemovable = false;
    private float duration = 5f; // Power-up duration in seconds

    public AbstractPowerUp(GameScreen screen, Vector2 position, Player player, MapObject mapObject) {
        super(screen.getWorld(), createBodyDef(position), createShape());
        this.screen = screen;
        this.player = player;
        this.mapObject = mapObject;
        this.body.setUserData(this); // Identify the Power-Up in collision detection


        // Make the fixture a sensor so it only detects collisions
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = createShape();
        fixtureDef.isSensor = true;  // Detect collisions but don't block movement

        this.body.createFixture(fixtureDef);
        createShape().dispose(); // Clean up after using the shape
}
    

    private static BodyDef createBodyDef(Vector2 position) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(position);
        bodyDef.type = BodyDef.BodyType.StaticBody;
        return bodyDef;
    }

    private static Shape createShape() {
        CircleShape shape = new CircleShape();
        shape.setRadius(6f);
        return shape;
    }

    /**
     * Apply the power-up effect when collected.
     */
    public void collectPowerUp() {
        applyEffect();
        isRemovable = true;
    
        if (mapObject != null) {
            screen.getMap().getLayers().get("PowerUp").getObjects().remove(mapObject);
        }
    
        screen.getWorld().destroyBody(this.body);  // ✅ Ensure it's removed from physics world
    }
    
    
    /**
     * Abstract method for applying the power-up effect.
     */
    protected abstract void applyEffect();

    /**
     * Abstract method for removing the power-up effect after expiration.
     */
    protected abstract void removeEffect();

    /**
     * Removes the power-up object from the Tiled Map.
     */
    private void removeFromTiledMap() {
        if (mapObject != null) {
            screen.getMap().getLayers().get("PowerUp").getObjects().remove(mapObject);
        }
    }

    /**
     * Updates power-up duration.
     */
    public void update(float dt) {
        if (isRemovable) {
            duration -= dt;
            if (duration <= 0) {
                removeEffect();
            }
        }
    }
}









    

