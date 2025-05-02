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

/**
 * The BlackHole class represents a moving enemy in the game.
 * It extends the SimpleEnemy class and implements the iMovingEnemy interface.
 * The BlackHole moves in a specified direction and can attack the player.
 */
public class BlackHole extends SimpleEnemy implements iMovingEnemy {

    private int direction;
    private boolean markedForRemoval = false;

    /**
     * Constructor for BlackHole.
     *
     * @param blackHole the character to be used for the BlackHole
     */
    public BlackHole(Character blackHole) {
        super(blackHole);
        this.direction = 1;
        getCharacter().getBody().setUserData(this); // sets userData to BlackHole instance
        createTopSensor();
        createLeftAndRightSensors();
        setupAnimation();
    }

    /**
     * Constructor for BlackHole.
     *
     * @param name       the name of the BlackHole
     * @param attributes the attributes of the BlackHole
     * @param size       the size of the BlackHole
     * @param world      the world in which the BlackHole exists
     * @param target     the target character (not used in this implementation)
     */
    public BlackHole(String name, CharacterAttributes attributes, Vector2 size, World world, Character target) {
        this(new Character(name, attributes, size, world));
    }

    @Override
    public void attack(Character target) {
        target.takeDamage(getCharacter().getAttributes().getStrength());
    }

    @Override
    public void move() {
        CharacterAttributes attributes = getCharacter().getAttributes();
        float xVelocity = attributes.getSpeed() * direction;
        float yVelocity = getCharacter().getVelocity().y;

        getCharacter().setVelocity(new Vector2(xVelocity, yVelocity));
    }

    @Override
    public void update(float dt) {
        move();
    }

    /**
     * Changes the BlackHole's direction
     * from left to right or vice versa.
     */
    public void changeDirection() {
        direction *= -1;
    }

    /**
     * Marks the BlackHole for removal.
     * Use this to remove the BlackHole from the game.
     */
    public void markForRemoval() {
        this.markedForRemoval = true;
    }

    /**
     * 
     * @return boolean indicating whether this BlackHole needs to be removed.
     */
    public boolean shouldBeRemoved() {
        return markedForRemoval;
    }

    @Override
    protected void setupAnimation() {
        getCharacter().getAnimator().dispose();
        Texture tex = new Texture(Gdx.files.internal("sprites/blackhole.png"));
        getCharacter().getAnimator().addAnimation("idle", tex, 1, 7, 8);
        getCharacter().getAnimator().play("idle");
    }

    /**
     * Sensors on the left and right side of the BlackHole,
     * so that collisions with platform walls can be detected.
     */
    private void createLeftAndRightSensors() {
        Body body = getCharacter().getBody();

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
        Body body = getCharacter().getBody();

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
