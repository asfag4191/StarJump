package inf112.skeleton.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import inf112.skeleton.model.character.Character;
import inf112.skeleton.model.character.Stats;
import inf112.skeleton.model.colliders.BoxCollider;
//import inf112.skeleton.model.colliders.WorldContactListener;
import inf112.skeleton.utility.TiledManager;
import inf112.skeleton.view.Renderable;

import java.util.ArrayList;

public class WorldModel implements Renderable {
    public World world;
    private final ArrayList<Renderable> ViewableObjects;
    //private final Character tester;
    private final Player player;

    public WorldModel(Vector2 gravity, boolean doSleep) {
        world = new World(gravity, doSleep);
        ViewableObjects = new ArrayList<>();
        //this.tester = createCharacter(new Vector2(1,1));
        this.player = createPlayer(new Vector2(1,1));
    }

    /**
     * Creates a new Player instance with the specified size, sets its initial position,
     * and adds it to the list of viewable objects.
     *
     * @param size the dimensions of the player as a Vector2 object
     * @return the newly created player
     */
    public Player createPlayer(Vector2 size) {
        Player player = new Player(size, world);
        player.setPosition(new Vector2(10, 10));
        ViewableObjects.add(player);
        return player;
    }

    /**
     * Retrieves the current Player instance.
     *
     * @return the player associated with this class
     */
    public Player getPlayer() {
        return this.player;
    }

    public void onStep(float dt) {
        world.step(dt, 3, 3);
    }

//    public Character createCharacter(Vector2 size) {
//        Stats stats = new Stats(
//                100,
//                2,
//                16,
//                5,
//                1
//        );
//        Character charac = new Character("Tester", stats, size, world, false);
//
//        ViewableObjects.add(charac);
//        return charac;
//    }

    public BoxCollider createTile(Vector2 position, Vector2 size, Texture texture) {
        BodyDef def = new BodyDef();
        def.type = BodyDef.BodyType.StaticBody;
        def.position.set(position);
        def.fixedRotation = true;
        BoxCollider box = new BoxCollider(world, def, texture, size);

        ViewableObjects.add(box);
        return box;
    }

    public BoxCollider createTile(Vector2 size, Texture texture) {
        return createTile(new Vector2(0,0), size, texture);
    }

    @Override
    public void render(Batch batch, float dt) {
        for (Renderable obj: ViewableObjects) {
            obj.render(batch, dt);
        }
    }
}
