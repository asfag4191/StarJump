package inf112.skeleton.view.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import inf112.skeleton.model.Player;
import inf112.skeleton.model.StarJump;
import inf112.skeleton.model.WorldModel;

public class GameScreen implements Screen {
    private final static boolean DEBUG_MODE = true;

    private final StarJump game;
    private final TiledMap map;
    private final OrthogonalTiledMapRenderer renderer;
    private final OrthographicCamera gamecam;
    private final Stage stage;
    private final Box2DDebugRenderer debugger;
    private final WorldModel worldModel;
    private final Player player;

    public GameScreen(StarJump game) {
        this.game = game;
        this.worldModel = new WorldModel(new Vector2(0, -3f), true);//-9.81f), true);
        this.debugger = new Box2DDebugRenderer(true, true, true, true, true, true);

        // Use gameViewport (tile-based)
        this.gamecam = (OrthographicCamera) game.gameViewport.getCamera();
        this.stage = new Stage(this.game.gameViewport);
        Gdx.input.setInputProcessor(this.stage);



        // Load TMX map
        map = new TmxMapLoader().load("src/main/assets/map/tilemaps/map1.tmx");

        // Set up renderer (assuming tiles are 16x16 pixels)
        renderer = new OrthogonalTiledMapRenderer(map, 1f / 16f);

        this.player = worldModel.getPlayer();
        // Center the camera
        float w = game.gameViewport.getWorldWidth();
        float h = game.gameViewport.getWorldHeight();
        gamecam.position.set(w / 2f, h / 2f, 0f);
        gamecam.update();
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    public void handleInput(float dt) {
        if (Gdx.input.isTouched())
            gamecam.position.y += 10 * dt;
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE))
            player.applyImpulse(new Vector2(0, 4f));
        if (Gdx.input.isKeyJustPressed(Input.Keys.RIGHT) && player.getVelocity().x <= 2)
            player.applyImpulse(new Vector2(1f, 0));
        if (Gdx.input.isKeyJustPressed(Input.Keys.LEFT) && player.getVelocity().x >= -2)
            player.applyImpulse(new Vector2(-1f, 0));
    }

    public void update(float dt) {
        handleInput(dt);

        int mapTileHeight = map.getProperties().get("height", Integer.class);
        float mapWorldHeight = mapTileHeight; // since 1 world unit equals 1 tile

        // Viewport height in world units
        float viewportHeight = game.gameViewport.getWorldHeight();

        // Calculate clamping boundaries
        float maxCameraY = mapWorldHeight - (viewportHeight / 2f);
        float minCameraY = viewportHeight / 2f;

        // Clamp the camera's Y position
        gamecam.position.y = Math.max(minCameraY, Math.min(gamecam.position.y, maxCameraY));

        gamecam.update();
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
        game.gameViewport.update(width, height, false);
        // Recenter camera based on fixed world size
        float w = game.gameViewport.getWorldWidth();
        float h = game.gameViewport.getWorldHeight();
        gamecam.position.set(w / 2f, h / 2f, 0);
        gamecam.update();
    }

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {
        //player.dispose();
        map.dispose();
        renderer.dispose();
    }

    private void debug() {
        if (DEBUG_MODE) {
            debugger.render(worldModel.world, gamecam.combined);
        }
    }

    private void logic(float dt) {
        worldModel.onStep(dt);
    }

    private void input() {
    }

    private void draw(float dt) {
        update(dt);
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.gameViewport.apply();
        gamecam.update();

        renderer.setView(gamecam);
        renderer.render();

        game.batch.setProjectionMatrix(gamecam.combined);
        game.batch.begin();
        worldModel.render(game.batch, dt);
        player.render(game.batch, dt);
        game.batch.end();
    }
}
