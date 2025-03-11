package inf112.skeleton.utility;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.PolygonMapObject;
import com.badlogic.gdx.maps.objects.PolylineMapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import inf112.skeleton.model.StarJump;

import java.util.logging.Level;
import java.util.logging.Logger;

public final class ColliderToBox2D {

    private ColliderToBox2D() throws IllegalAccessException {
        throw new IllegalAccessException("Utility class");
    }
    /**
     * Iterates over mapObjects and creates corresponding objects in world using box2D shapes
     *
     * @param world world where objects interact
     * @param mapObjects collision objects from TiledMap
     * @param ppt Pixel Per Tile, found as "tilewidth" in map properties
     */
    public static void parseTiledObjects(World world, MapObjects mapObjects, int ppt) {
        Logger logger = Logger.getLogger(ColliderToBox2D.class.getName());
        for (MapObject obj : mapObjects) {
            Shape shape;
            BodyDef def = new BodyDef();

            // Checks object for type and gets corresponding shape
            if (obj instanceof PolylineMapObject) {
                shape = getPolyline((PolylineMapObject) obj, ppt);

            } else if (obj instanceof PolygonMapObject) {
                shape = getPolygon((PolygonMapObject) obj, ppt);

            }  else if (obj instanceof RectangleMapObject) {
                shape = getRectangle((RectangleMapObject) obj, ppt);
            } else {
                String errorMessage = "Error: Unknown map object type: " + obj.getClass().getSimpleName();
                logger.log(Level.WARNING, errorMessage);
                continue;
            }

            /*
            Adds body with shape and position to world as static object
            Then disposes the
            */
        // Create the body
        Body body = world.createBody(def);

        // ✅ Assign collision filtering
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.filter.categoryBits = StarJump.GROUND_BIT; // Ground objects
        fixtureDef.filter.maskBits = StarJump.PLAYER_BIT; // Only players collide with it

        body.createFixture(fixtureDef);
        shape.dispose();
        }
    }

    private static PolygonShape getRectangle(RectangleMapObject rectangleObject, int ppt) {
        Rectangle rectangle = rectangleObject.getRectangle();
        PolygonShape polygon = new PolygonShape();
        Vector2 size = new Vector2((rectangle.x + rectangle.width * 0.5f) / ppt,
                (rectangle.y + rectangle.height * 0.5f ) / ppt);
        polygon.setAsBox(rectangle.width * 0.5f / ppt,
                rectangle.height * 0.5f / ppt,
                size,
                0.0f);
        return polygon;
    }

    private static PolygonShape getPolygon(PolygonMapObject polygonObject, int ppt) {
        PolygonShape polygon = new PolygonShape();
        float[] vertices = polygonObject.getPolygon().getTransformedVertices();

        float[] worldVertices = new float[vertices.length];

        for (int i = 0; i < vertices.length; ++i) {
            worldVertices[i] = vertices[i] / ppt;
        }

        polygon.set(worldVertices);
        return polygon;
    }

    private static ChainShape getPolyline(PolylineMapObject polylineObject, int ppt) {
        float[] vertices = polylineObject.getPolyline().getTransformedVertices();
        Vector2[] worldVertices = new Vector2[vertices.length / 2];

        for (int i = 0; i < vertices.length / 2; ++i) {
            worldVertices[i] = new Vector2();
            worldVertices[i].x = vertices[i * 2] / ppt;
            worldVertices[i].y = vertices[i * 2 + 1] / ppt;
        }

        ChainShape chain = new ChainShape();
        chain.createChain(worldVertices);
        return chain;
    }
}
