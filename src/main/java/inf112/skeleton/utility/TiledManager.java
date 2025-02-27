package inf112.skeleton.utility;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.*;
import com.badlogic.gdx.math.Ellipse;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

public final class TiledManager {
    public static void parseTiledObjects(World world, MapObjects mapObjects) {
        for (MapObject obj : mapObjects) {
            Shape shape;
            BodyDef def = new BodyDef();
            def.type = BodyDef.BodyType.StaticBody;

            if (obj instanceof PolylineMapObject) {
                shape = applyChain((PolylineMapObject) obj);

            } else if (obj instanceof PolygonMapObject) {
                shape = applyPolygon((PolygonMapObject) obj);

            } else if (obj instanceof EllipseMapObject) {
                shape = applyCircle((EllipseMapObject) obj);

            } else if (obj instanceof RectangleMapObject) {
                shape = applyRect((RectangleMapObject) obj);
                def.position.set(32,32);
            } else {
                continue;
            }


            Body body = world.createBody(def);
            body.createFixture(shape, 1f);
            shape.dispose();
        }
    }

    private static Shape applyChain(PolylineMapObject obj) {
        System.out.println("its a line");

        float[] vertices = obj.getPolyline().getTransformedVertices();

        ChainShape shape = new ChainShape();
        shape.createChain(vertices);
        return shape;
    }

    private static Shape applyPolygon(PolygonMapObject obj) {
        System.out.println("its polygon");

        float[] vertices = obj.getPolygon().getTransformedVertices();

        PolygonShape shape = new PolygonShape();
        shape.set(vertices);
        return shape;
    }

    private static Shape applyCircle(EllipseMapObject obj) {
        System.out.println("its ellipse");

        Ellipse ellipse = obj.getEllipse();
        float width = ellipse.width;
        float height = ellipse.height;
        float x = ellipse.x;
        float y = ellipse.y;

        if (width != height) {
            return applyRect(width, height);
        }

        CircleShape shape = new CircleShape();
        shape.setRadius(width);
        return shape;
    }

    private static Shape applyRect(RectangleMapObject obj) {
        System.out.println("its rect");

        Rectangle rect = obj.getRectangle();
        return applyRect(rect.width, rect.height);
    }

    private static Shape applyRect(float width, float height) {
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(width / 2, height / 2);
        return shape;
    }
}
