package inf112.skeleton.model.items;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.EllipseMapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Ellipse;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;

import inf112.skeleton.view.screen.GameScreen;

/**
 * Base class for interactive objects like power-ups and obstacles.
 */
public abstract class InteractiveTileObject implements IItem {
    protected World world;
    protected TiledMap map;
    protected Body body;
    protected GameScreen screen;
    protected MapObject object;
    protected Fixture fixture;

    public InteractiveTileObject(GameScreen screen, MapObject object) {
        this.object = object;
        this.screen = screen;
        this.world = screen.getWorld();
    
        BodyDef bdef = new BodyDef();
        FixtureDef fdef = new FixtureDef();
        Shape shape;
    
        bdef.type = BodyDef.BodyType.StaticBody;
    
        if (object instanceof RectangleMapObject) {
            Rectangle rectangle = ((RectangleMapObject) object).getRectangle();
            bdef.position.set(
                (rectangle.x + rectangle.width / 2) / 16f,
                (rectangle.y + rectangle.height / 2) / 16f
            );
            PolygonShape rectShape = new PolygonShape();
            rectShape.setAsBox(rectangle.width / 2 / 16f, rectangle.height / 2 / 16f);
            shape = rectShape;
    
        } else if (object instanceof EllipseMapObject) {
            Ellipse ellipse = ((EllipseMapObject) object).getEllipse();
            bdef.position.set(
                (ellipse.x + ellipse.width / 2) / 16f,
                (ellipse.y + ellipse.height / 2) / 16f
            );
    
            CircleShape circleShape = new CircleShape();
            circleShape.setRadius((ellipse.width / 2) / 16f);
            shape = circleShape;
        } else {
            throw new IllegalArgumentException("Unsupported object type: " + object.getClass().getSimpleName());
        }
    
        body = world.createBody(bdef);
        fdef.shape = shape;
        fdef.isSensor = true;
        fixture = body.createFixture(fdef);
        fixture.setUserData(this); // Important for collision detection
        shape.dispose();
    }

    public abstract void onPlayerCollide();  // Define collision behavior

    public void setCategoryFilter(short filterBit) {
        Filter filter = new Filter();
        filter.categoryBits = filterBit;
        fixture.setFilterData(filter);
    }
}
