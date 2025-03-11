package inf112.skeleton.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import inf112.skeleton.controller.PlayerController;
import inf112.skeleton.model.character.Character;
import inf112.skeleton.model.character.Stats;
import inf112.skeleton.model.colliders.BoxCollider;
import inf112.skeleton.model.game_objects.Player;
import inf112.skeleton.utility.TiledManager;
import inf112.skeleton.view.Renderable;

import java.util.ArrayList;

public class WorldModel implements Renderable {
    public final World world;
    private final ArrayList<Renderable> ViewableObjects = new ArrayList<>();

    public WorldModel(Vector2 gravity, boolean doSleep) {
        this.world = new World(gravity, doSleep);
    }

    public void onStep(float dt) {
        world.step(dt, 3, 3);
    }

    public Player createPlayer() {
        Stats stats = new Stats(
                100,
                10,
                3.5f,
                5,
                1
        );

        Character charac = new Character("p1", stats, new Vector2(1,1), world);
        Texture texture1 = new Texture(Gdx.files.internal("blackhole.png"));
        charac.animator.addAnimation("idle", texture1, 1, 7, 8);
        charac.animator.play("idle");

        Player plr = new Player(charac);
        PlayerController.setupWASD(plr);
        ViewableObjects.add(plr.character);
        return plr;
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
    public void render(Batch batch, float dt) {
        for (Renderable obj: ViewableObjects) {
            obj.render(batch, dt);
        }
    }
}
