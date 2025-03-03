package inf112.skeleton.model;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;

import inf112.skeleton.model.colliders.BoxCollider;
import inf112.skeleton.model.colliders.ViewableBody;
import inf112.skeleton.utility.TiledManager;
import inf112.skeleton.view.Renderable;

public class WorldModel implements Renderable {
    public World world;
    private final ArrayList<ViewableBody> ViewableObjects;

    public WorldModel(Vector2 gravity, boolean doSleep) {
        world = new World(gravity, doSleep);
        ViewableObjects = new ArrayList<>();

        TiledMap tiledMap = new TmxMapLoader().load("src/main/assets/map/tilemaps/map1.tmx");
        System.out.println("Map loaded successfully!");
        MapObjects objects = tiledMap.getLayers().get("Tiles").getObjects();
        if (tiledMap.getLayers().get("Tiles") == null) {
            System.out.println("Error: Layer 'Tiles' not found in TMX file!");
        }
        TiledManager.parseTiledObjects(world, objects);
    }

    public void onStep(float dt) {
        world.step(dt, 3, 3);
    }

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
    public void render(Batch batch) {
        for (ViewableBody body: ViewableObjects) {
            body.render(batch);
        }
    }
}
