package inf112.skeleton.model.character.enemy;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

import inf112.skeleton.model.character.Character;
import inf112.skeleton.model.character.CharacterAttributes;

public class BlackHole extends SimpleEnemy implements iMovingEnemy {

    private boolean isMoving;
    private boolean markedForRemoval = false;


    public BlackHole(Character blackHole, World world) {
        super(blackHole, world);
        this.isMoving = true;
        enemyCharacter.getBody().setUserData(this); // sets userData to BlackHole instance
        //added a getbody in rigidbody, because did not now how to else. 
        createTopSensor();
        setupAnimation();
    }

    public BlackHole(String name, CharacterAttributes attributes, Vector2 size, World world, Character target) {
        this(new Character(name, attributes, size, world), world);
    }

    @Override
    public void attack(Character target) {
        target.takeDamage(enemyCharacter.getAttributes().getStrength());
    }

    public Character getEnemyCharacter() {
        return this.enemyCharacter;
    }

    @Override
    public void setMoving(boolean moving) {
        this.isMoving = moving;
    }

    @Override
    public boolean isMoving() {
        return isMoving;
    }

    public void markForRemoval() {
        this.markedForRemoval = true;
    }

    public boolean shouldBeRemoved() {
        return markedForRemoval;
    }

    // This is what draw the enemies taken out of the factory so 
    // the pictures dont got mixed. (can be some debug here still)
    @Override
    protected void setupAnimation() {
        enemyCharacter.animator.clearAnimations();
        Texture tex = new Texture(Gdx.files.internal("sprites/simple_blackhole.png"));
        enemyCharacter.animator.addAnimation("idle", tex, 1, 1, 0);
        enemyCharacter.animator.play("idle");
        
    }

    //added a sensor on top of the black hole, so when the player jumps on it, it will be removed
    private void createTopSensor() {
        Body body = enemyCharacter.getBody();

        PolygonShape sensorShape = new PolygonShape();
        sensorShape.setAsBox(0.4f, 0.1f, new Vector2(0, 0.55f), 0); // Slightly above the center

        FixtureDef sensorDef = new FixtureDef();
        sensorDef.shape = sensorShape;
        sensorDef.isSensor = true;

        Fixture sensorFixture = body.createFixture(sensorDef);
        sensorFixture.setUserData("blackhole_sensor");

        sensorShape.dispose();
    }
}
