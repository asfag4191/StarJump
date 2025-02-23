package inf112.skeleton.view.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import inf112.skeleton.model.StarJump;
import inf112.skeleton.model.WorldModel;
import inf112.skeleton.model.colliders.BoxCollider;
import inf112.skeleton.model.colliders.RigidBody;

public class GameScreen implements Screen {
    private static boolean DEBUG_MODE = true;

    private Box2DDebugRenderer debugger;
    final StarJump game;
    WorldModel worldModel;

    public GameScreen(StarJump game) {
        this.game = game;
        this.worldModel = new WorldModel(new Vector2(0, -9.81f), true);
        this.debugger = new Box2DDebugRenderer(true,true,true,true,true,true);
    }

    @Override
    public void show() {
        // TEST: CREATE AN OBJECT
        Texture texture = new Texture(Gdx.files.internal("star.png"));
        float middleX = game.viewport.getCamera().viewportWidth/2f;
        float middleY = game.viewport.getCamera().viewportHeight/2f;
        BoxCollider tile = worldModel.createTile(new Vector2(middleX, middleY), new Vector2(10,10), texture);
        BoxCollider ground = worldModel.createTile(new Vector2(middleX, middleY*0.25f), new Vector2(40,6), texture);
        tile.setType(BodyDef.BodyType.DynamicBody);
    }

    @Override
    public void render(float dt) {
        input();
        draw();
        debug();
        logic(dt);
    }

    @Override
    public void resize(int i, int i1) {

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
            debugger.render(worldModel.world, game.viewport.getCamera().combined);
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

    private void draw() {
        game.viewport.apply();
        game.batch.setProjectionMatrix(game.viewport.getCamera().combined);

        game.batch.begin();
        //draw text. Remember that x and y are in meters
        // (current resoulution is 64x64, change in StarJump.java)
        game.font.setColor(Color.WHITE);
        game.font.getData().setScale((game.viewport.getWorldHeight()*2) / Gdx.graphics.getHeight());
        game.font.draw(game.batch, "GAME IS NOW ACTIVE WOOP WOOP", 6, 40);
        game.font.draw(game.batch, "Press ESCAPE to go back to home screen", 5, 34);
        worldModel.render(game.batch);
        game.batch.end();
    }
}
