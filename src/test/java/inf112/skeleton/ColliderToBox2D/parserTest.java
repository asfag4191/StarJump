
package inf112.skeleton.ColliderToBox2D;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.PolygonMapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

import inf112.skeleton.utility.ColliderToBox2D;

public class parserTest {
    World world;
    TiledMap map;

    @BeforeEach
    void setup() {
        this.world = new World(new Vector2(0, -9.81f), true);
        ColliderToBox2D.parseTiledObjects(this.world,
                this.getMapObjects(),
                16);
    }

    private MapObjects getMapObjects() {
        MapObjects mapObjects = new MapObjects();
        for (int i = 1; i < 6; i++) {
            mapObjects.add(new RectangleMapObject(i, i, i, i));
        }
        for (MapObject object : mapObjects) {
            RectangleMapObject rectangleObject = (RectangleMapObject) object;
            // System.out.println("Object is in pos: " + rectangleObject.getRectangle());
        }
        return mapObjects;
    }

    @Test
    void checkAmountOfColliders() {
        Array<Fixture> fixtures = new Array<>();
        world.getFixtures(fixtures);
        assertEquals(5, world.getFixtureCount(), "Should be 5 fixtures!");
    }

    @Test
    void collidersAreStatic() {
        Array<Fixture> fixtures = new Array<>();
        world.getFixtures(fixtures);
        for (Fixture fixture : fixtures) {
            assertTrue(fixture.getBody().getType() == BodyDef.BodyType.StaticBody);
        }
        ;
    }

    private Vector2[] getVertices(Fixture fixture) {
        PolygonShape shape = (PolygonShape) fixture.getShape();
        Vector2[] v = new Vector2[shape.getVertexCount()];

        for (int i = 0; i < shape.getVertexCount(); i++) {
            Vector2 vertex = new Vector2();
            shape.getVertex(i, vertex);
            v[i] = vertex;
        }
        return v;
    }

    @Test
    void collidersAreCorrectShape() {
        Array<Fixture> fixtures = new Array<>();
        world.getFixtures(fixtures);
        for (Fixture fixture : fixtures) {
            Vector2[] v = new Vector2[4];
            v = getVertices(fixture);
            float width = v[1].x - v[0].x;
            float height = v[3].y - v[0].y;
            assertEquals(width, height, "This polygon should be a square! w: " + width + " h: " + height);
        }
    }

    @Test
    void collidersAreCorrectSize() {
        Array<Fixture> fixtures = new Array<>();
        world.getFixtures(fixtures);

        // create a list of possible sizes
        ArrayList<Float> widths = new ArrayList<>();
        ArrayList<Float> heights = new ArrayList<>();
        for (float i = 1; i < 6; i++) {
            widths.add(i);
            heights.add(i);
        }

        for (Fixture fixture : fixtures) {
            Vector2[] v = new Vector2[4];
            v = getVertices(fixture);

            float width = v[1].x - v[0].x;
            float height = v[3].y - v[0].y;

            // scaled with the ppm
            width *= 16;
            height *= 16;

            // As libgdx saves the fixtures in random order, we can only confirm that the
            // size is one of our specified values
            assertTrue(widths.contains(width));
            widths.remove(width);
            assertTrue(heights.contains(height));
            heights.remove(height);
        }
    }

    @Test
    void collidersAreInCorrectPosition() {
        Array<Fixture> fixtures = new Array<>();
        world.getFixtures(fixtures);

        // create a list of possible sizes
        ArrayList<Float> posX = new ArrayList<>();
        ArrayList<Float> posY = new ArrayList<>();

        for (float i = 1; i < 6; i++) {
            posX.add(i);
            posY.add(i);
        }

        for (Fixture fixture : fixtures) {
            Vector2[] v = new Vector2[4];
            v = getVertices(fixture);

            // scaled with the ppm
            float x = v[0].x * 16;
            float y = v[0].y * 16;

            // As libgdx saves the fixtures in random order, we can only confirm that the
            // position is one of our specified values
            assertTrue(posX.contains(x));
            posX.remove(x);
            assertTrue(posY.contains(y));
            posY.remove(y);
        }
    }

}
