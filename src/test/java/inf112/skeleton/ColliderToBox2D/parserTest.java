/*
 * package inf112.skeleton.ColliderToBox2D;
 * 
 * import static org.junit.jupiter.api.Assertions.assertEquals;
 * import static org.junit.jupiter.api.Assertions.assertTrue;
 * 
 * import org.junit.jupiter.api.BeforeEach;
 * import org.junit.jupiter.api.Test;
 * 
 * import com.badlogic.gdx.maps.tiled.TiledMap;
 * import com.badlogic.gdx.maps.tiled.TmxMapLoader;
 * import com.badlogic.gdx.math.Vector2;
 * import com.badlogic.gdx.physics.box2d.Body;
 * import com.badlogic.gdx.physics.box2d.BodyDef;
 * import com.badlogic.gdx.physics.box2d.World;
 * import com.badlogic.gdx.utils.Array;
 * 
 * import inf112.skeleton.utility.ColliderToBox2D;
 * 
 * public class parserTest {
 * World world;
 * TiledMap map;
 * 
 * @BeforeEach
 * void setup() {
 * this.world = new World(new Vector2(0, -9.81f), true);
 * this.map = new
 * TmxMapLoader().load("inf112/skeleton/ColliderToBox2D/maps/testmap1.tmx");
 * // load in map
 * ColliderToBox2D.parseTiledObjects(this.world,
 * this.map.getLayers().get("Colliders").getObjects(),
 * this.map.getProperties().get("tilewidth", Integer.class));
 * 
 * }
 * 
 * @Test
 * void checkAmountOfColliders() {
 * assertEquals(5, world.getBodyCount());
 * }
 * 
 * @Test
 * void collidersAreStatic() {
 * Array<Body> bodies = new Array<>();
 * world.getBodies(bodies);
 * for (Body body : bodies) {
 * assertTrue(body.getType() == BodyDef.BodyType.StaticBody);
 * }
 * }
 * 
 * /**
 * Test if the colliders are in the correct position
 */
/*
 * @Test
 * void collidersAreInCorrectPosition() {
 * Array<Body> bodies = new Array<>();
 * world.getBodies(bodies);
 * for (Body body : bodies) {
 * if (body.getPosition().x == 0 && body.getPosition().y == 0) {
 * assertTrue(true, "Body is in pos: " + body.getPosition());
 * 
 * }
 * System.out.println("Body is in pos: " + body.getPosition());
 * }
 * }
 * }
 */
