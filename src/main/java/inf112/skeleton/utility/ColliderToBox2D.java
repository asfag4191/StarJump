package inf112.skeleton.utility;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;

import inf112.skeleton.app.StarJump;

public final class ColliderToBox2D {

    private ColliderToBox2D() throws IllegalAccessException {
        throw new IllegalAccessException("Utility class");
    }

    /**
     * Iterates over mapObjects and creates corresponding objects in world using
     * box2D shapes.
     *
     * Only works with RectangleMapObjects.
     *
     * @param world      world where objects interact
     * @param mapObjects collision objects from TiledMap
     * @param ppt        Pixel Per Tile, found as "tilewidth" in map properties
     */
    public static void parseTiledObjects(World world, MapObjects mapObjects, int ppt) {
        Logger logger = Logger.getLogger(ColliderToBox2D.class.getName());
        for (MapObject obj : mapObjects) {
            Shape shape;
            BodyDef def = new BodyDef();
            def.type = BodyDef.BodyType.StaticBody;

            // Checks object for type and gets corresponding shape
            if (obj instanceof RectangleMapObject) {
                shape = getRectangle((RectangleMapObject) obj, ppt);

            } else {
                String errorMessage = "Error: Unknown map object type: " + obj.getClass().getSimpleName();
                logger.log(Level.WARNING, errorMessage);
                continue;
            }

            // Create the body
            Body body = world.createBody(def);

            // Assign collision filtering
            FixtureDef fixtureDef = new FixtureDef();
            fixtureDef.shape = shape;
            fixtureDef.isSensor = false;

            fixtureDef.filter.categoryBits = StarJump.GROUND_BIT; // Ground objects
            fixtureDef.filter.maskBits = StarJump.PLAYER_BIT | StarJump.GROUND_SENSOR_BIT; // Only players collide with

            body.createFixture(fixtureDef);
            body.setUserData("ground");
            shape.dispose();
        }
    }

    private static PolygonShape getRectangle(RectangleMapObject rectangleObject, int ppt) {
        Rectangle rectangle = rectangleObject.getRectangle();
        PolygonShape polygon = new PolygonShape();
        Vector2 size = new Vector2((rectangle.x + rectangle.width * 0.5f) / ppt,
                (rectangle.y + rectangle.height * 0.5f) / ppt);
        polygon.setAsBox(rectangle.width * 0.5f / ppt,
                rectangle.height * 0.5f / ppt,
                size,
                0.0f);
        return polygon;
    }

}
