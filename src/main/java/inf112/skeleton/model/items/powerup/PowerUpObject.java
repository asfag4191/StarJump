package inf112.skeleton.model.items.powerup;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;

import inf112.skeleton.app.StarJump;
import inf112.skeleton.model.character.controllable_characters.Player;
import inf112.skeleton.model.items.InteractiveTileObject;
import inf112.skeleton.view.screen.GameScreen;

public class PowerUpObject extends InteractiveTileObject {

    private boolean isCollected = false;
    private final AbstractPowerUp powerUp;
    private final Player player;
    
        public PowerUpObject(GameScreen screen, MapObject object, AbstractPowerUp powerUp, Player player, Sprite sprite) {
            super(screen, object);
            this.powerUp = powerUp;
            this.player = player;
            setCollisionFilter();
    }

    private void setCollisionFilter() {
        var filter = fixture.getFilterData();
        filter.categoryBits = StarJump.POWERUP;
        filter.maskBits = StarJump.PLAYER_BIT;
        fixture.setFilterData(filter);
        fixture.setUserData(this); 
    }

    /**
    * Handles collision with player, applying power-up effect.
    */
    @Override
    public void onPlayerCollide() {
        if (!isCollected) {
            powerUp.applyPowerUpEffect();
            screen.getPowerUpManager().markForRemoval(this);
    
            player.getBody().setLinearVelocity(player.getBody().getLinearVelocity().x, 0);
            player.getBody().applyLinearImpulse(new Vector2(0, 10f), player.getBody().getWorldCenter(), true);
    
            isCollected = true;
        }
    }

    public boolean isCollected() {
        return isCollected;
    }

    public void setCollected(boolean collected) {
        isCollected = collected;
    }

    public World getWorld() {
        return world;
    }

    public Body getBody() {
        return body;
    }
    public void setBody(Body body) {
        this.body = body;

    }

    @Override
    public void dispose() {
        world.destroyBody(body);
    }

@Override
public void update(float dt) {
    if (isCollected) {
        dispose();
    }
}

    public Sprite getSprite() {
        return powerUp.getSprite();
    }
    
}


