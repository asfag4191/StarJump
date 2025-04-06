package inf112.skeleton.model.colliders;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RigidBodyTest {
    RigidBody rigidBody;

    @BeforeEach
    void setUpBeforeEach() {
        World world = new World(new Vector2(0,0), true);

        BodyDef def = new BodyDef();
        def.fixedRotation = true;
        def.type = BodyDef.BodyType.StaticBody;

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(1,1);

        FixtureDef fixDef = new FixtureDef();
        fixDef.shape = shape;

        rigidBody = new RigidBody(world, def, fixDef);
    }

    @Test
    void setVelocity() {
        
    }

    @Test
    void setType() {
    }

    @Test
    void setTransform() {
    }

    @Test
    void testSetTransform() {
    }
}