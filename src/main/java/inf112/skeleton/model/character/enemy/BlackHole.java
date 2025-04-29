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

    private int direction;
    private boolean markedForRemoval = false;


    public BlackHole(Character blackHole, World world) {
        super(blackHole, world);
        this.direction = 1;
        enemyCharacter.getBody().setUserData(this); // sets userData to BlackHole instance
        createTopSensor();
        createLeftAndRightSensors();
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
    public void move() {
        CharacterAttributes attributes = enemyCharacter.getAttributes();
        float xVelocity = attributes.getSpeed() * direction;
        float yVelocity = enemyCharacter.getVelocity().y;

        enemyCharacter.setVelocity(new Vector2(xVelocity, yVelocity));
    }

    @Override
    public void update(float dt) {
        move();
    }

    public void changeDirection() {
        direction *= -1;
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
        Texture tex = new Texture(Gdx.files.internal("sprites/blackhole.png"));
        enemyCharacter.animator.addAnimation("idle", tex, 1, 7, 8);
        enemyCharacter.animator.play("idle");
        
    }

    /**
     * Sensors on the left and right side of the BlackHole,
     * so that collisions with platform walls can be detected.
     */
    private void createLeftAndRightSensors() {
        Body body = enemyCharacter.getBody();

        // left sensor
        PolygonShape leftShape = new PolygonShape();
        leftShape.setAsBox(0.05f, 0.3f, new Vector2(-0.6f, 0), 0);

        FixtureDef leftSensorDef = new FixtureDef();
        leftSensorDef.isSensor = true;
        leftSensorDef.shape = leftShape;

        Fixture leftSensor = body.createFixture(leftSensorDef);
        leftSensor.setUserData("leftSensor");

        leftShape.dispose();

        // right sensor
        PolygonShape rightShape = new PolygonShape();
        rightShape.setAsBox(0.05f, 0.3f, new Vector2(0.6f, 0), 0);
     
        FixtureDef rightSensorDef = new FixtureDef();
        rightSensorDef.isSensor = true;
        rightSensorDef.shape = leftShape;

        Fixture rightSensor = body.createFixture(rightSensorDef);
        rightSensor.setUserData("rightSensor");

        rightShape.dispose();
    }

    /**
     * A sensor on top of the BlackHole, 
     * so that the Player can jump on the BlackHole to kill it
     */
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
