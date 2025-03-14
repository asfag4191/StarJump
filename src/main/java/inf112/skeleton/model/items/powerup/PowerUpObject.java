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
    private final Player player;
    private final Sprite sprite; 
    
    
        public PowerUpObject(GameScreen screen, MapObject object, Player player, Sprite sprite) {
            super(screen, object);
            this.player = player;
            this.sprite = sprite;
            setCollisionFilter();
    }

    private void setCollisionFilter() {
        var filter = fixture.getFilterData();
        filter.categoryBits = StarJump.POWERUP;
        filter.maskBits = StarJump.PLAYER_BIT;
        fixture.setFilterData(filter);
        fixture.setUserData(this); //  Add this line
    }

    @Override
    public void onPlayerCollide() {
        if (!isCollected) {
            System.out.println("Power-up collected!");
            player.getBody().applyLinearImpulse(new Vector2(0, 5f), player.getBody().getWorldCenter(), true);
    
            isCollected = true;
            screen.getPowerUpManager().markForRemoval(this);
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

    
    public void dispose() {
        world.destroyBody(body);
    }

    public void update(float dt) {
        if (isCollected) {
            dispose();
        }
    }

    public Sprite getSprite() {
        return sprite;
    }
    
        }


