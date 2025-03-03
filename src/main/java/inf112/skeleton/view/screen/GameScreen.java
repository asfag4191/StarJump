package inf112.skeleton.view.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import inf112.skeleton.model.StarJump;
import inf112.skeleton.model.WorldModel;
import inf112.skeleton.model.character.Character;
import inf112.skeleton.model.colliders.BoxCollider;

public class GameScreen implements Screen {
    private static boolean DEBUG_MODE = true;

    private final StarJump game;

    private Box2DDebugRenderer debugger;
    private WorldModel worldModel;
    private Viewport view;

    public GameScreen(StarJump game) {
        this.game = game;
        this.worldModel = new WorldModel(new Vector2(0, -9.81f), true);
        this.debugger = new Box2DDebugRenderer(true, true, true, true, true, true);
        this.view = new FitViewport(20*0.5f, 15*0.5f);
    }

    @Override
    public void show() {
        // TEST: CREATE AN OBJECT
        Texture texture = new Texture(Gdx.files.internal("star.png"));
        float middleX = view.getWorldWidth() / 2f;
        float middleY = view.getWorldHeight() / 2f;
        BoxCollider tile = worldModel.createTile(new Vector2(middleX, middleY), new Vector2(1, 1), texture);
        BoxCollider ground = worldModel.createTile(new Vector2(middleX, middleY * 0.25f), new Vector2(10, 2), texture);
        tile.setType(BodyDef.BodyType.DynamicBody);
        // TEST: CREATE CHARACTER
        Character charac =  worldModel.createCharacter(new Vector2(1,1));
        charac.setTransform(new Vector2(middleX+2, middleY));

        Texture texture1 = new Texture(Gdx.files.internal("blackhole.png"));
        charac.animator.addAnimation("idle", texture1, 1, 7, 8);
        charac.animator.play("idle");
    }

    @Override
    public void render(float dt) {
        input();
        draw(dt);
        debug();
        logic(dt);
    }

    @Override
    public void resize(int width, int height) {
        if (width > 0 && height > 0) {
            view.update(width, height, true);
        }
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
    }

    private void debug() {
        if (DEBUG_MODE) {
            debugger.render(worldModel.world, view.getCamera().combined);
        }
    }

    private void logic(float dt) {
        worldModel.onStep(dt);
    }

    private void input() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            game.setScreen(new MainMenuScreen(game));
            dispose();
        }
    }

    private void draw(float dt) {
        view.apply();
        game.batch.setProjectionMatrix(view.getCamera().combined);
        game.batch.begin();

        worldModel.render(game.batch, dt);

        game.batch.end();
    }


}
