package inf112.skeleton.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import inf112.skeleton.controller.PlayerController;
import inf112.skeleton.model.character.Character;
import inf112.skeleton.model.character.CharacterAttributes;
import inf112.skeleton.model.character.controllable_characters.Player;
import inf112.skeleton.model.colliders.BoxCollider;
import inf112.skeleton.view.Renderable;

import java.util.ArrayList;
import java.util.List;

public class WorldModel implements Renderable {
    public final World world;
    private final ArrayList<Renderable> ViewableObjects = new ArrayList<>();
    private List<Body> bodiesToRemove = new ArrayList<>();

    public WorldModel(Vector2 gravity, boolean doSleep) {
        this.world = new World(gravity, doSleep);
    }

    public WorldModel(World world) {
        this.world = world;
    }

    public void onStep(float dt) {
        world.step(dt, 3, 3);
        removeBodies();
    }

    public Player createPlayer() {
        CharacterAttributes attributes = new CharacterAttributes(
                5,
                14,
                2,
                3.5f,
                5);

        Character charac = new Character("p1", attributes, new Vector2(1, 1), world);
        charac.setPosition(new Vector2(10, 3));
        Player plr = new Player(charac);

        Texture texture1 = new Texture(Gdx.files.internal("sprites/CoolStar.png"));
        charac.getAnimator().addAnimation("idle", texture1, 1, 1, 0);
        charac.getAnimator().play("idle");
        ViewableObjects.add(charac);

        PlayerController.setupWASD(plr);
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
        return createTile(new Vector2(0, 0), size, texture);
    }

    public void addViewableObject(Renderable obj) {
        ViewableObjects.add(obj);
    }

    public void setBodyForRemove(Body body) {
        this.bodiesToRemove.add(body);
    }

    private void removeBodies() {
        if (bodiesToRemove.isEmpty()) {
            return;
        }
        for (Body body : bodiesToRemove) {
            world.destroyBody(body);
        }
        bodiesToRemove.clear();
    }

    @Override
    public void render(Batch batch, float dt) {
        for (Renderable obj : ViewableObjects) {
            obj.render(batch, dt);
        }
    }
}
