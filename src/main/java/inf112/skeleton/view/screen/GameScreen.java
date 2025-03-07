package inf112.skeleton.view.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;

import inf112.skeleton.model.StarJump;
import inf112.skeleton.utility.ColliderToBox2D;
import inf112.skeleton.model.WorldModel;

public class GameScreen implements Screen {
    private static boolean DEBUG_MODE = true;

    private final StarJump game;
    private final TiledMap map;
    private final OrthogonalTiledMapRenderer renderer;
    private final OrthographicCamera gamecam;
    private final Stage stage;
    private ShapeRenderer shapeRenderer;
    private final int gridSize = 1;
    private World world;
    private final WorldModel worldModel;
    private Box2DDebugRenderer debugger;

    public GameScreen(StarJump game) {
        this.game = game;
        this.worldModel = new WorldModel(new Vector2(0, -9.81f), true);
        this.debugger = new Box2DDebugRenderer(true, true, true, true, true, true);

        // Use gameViewport (tile-based)
        this.gamecam = (OrthographicCamera) game.gameViewport.getCamera();
        this.stage = new Stage(this.game.gameViewport);
        Gdx.input.setInputProcessor(this.stage);
        shapeRenderer = new ShapeRenderer();

        // Load TMX map
        map = new TmxMapLoader().load("src/main/assets/map/tilemaps/map1.tmx");

        // Set up renderer (assuming tiles are 16x16 pixels)
        renderer = new OrthogonalTiledMapRenderer(map, 1f / 16f);

        // Center the camera
        float w = game.gameViewport.getWorldWidth();
        float h = game.gameViewport.getWorldHeight();
        gamecam.position.set(w / 2f, h / 2f, 0f);
        gamecam.update();

        // Creates a world and adds all colliders from tiled map
        this.world = new World(new Vector2(0,-9.81f), true);
        ColliderToBox2D.parseTiledObjects(this.world, map.getLayers().get("Tiles").getObjects(), map.getProperties().get("tilewidth", Integer.class));
        this.debugger = new Box2DDebugRenderer();
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    public void handleInput(float dt) {
        if (Gdx.input.isTouched())
            gamecam.position.y += 10 * dt;
        if (Gdx.input.isKeyJustPressed(com.badlogic.gdx.Input.Keys.ESCAPE)) {
            game.setScreen(new MainMenuScreen(game));
        }
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

    /**
     * Renders the grid from World, makes for easy tile debugging
     */
    private void renderGrid() {
        shapeRenderer.setProjectionMatrix(gamecam.combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(1, 1, 1, 1); // White grid

        // Draw vertical lines
        for (int x = 0; x <= Gdx.graphics.getWidth(); x += gridSize) {
            shapeRenderer.line(x, 0, x, Gdx.graphics.getHeight());
        }

        // Draw horizontal lines
        for (int y = 0; y <= Gdx.graphics.getHeight(); y += gridSize) {
            shapeRenderer.line(0, y, Gdx.graphics.getWidth(), y);
        }

        shapeRenderer.end();
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

        game.batch.begin();
        worldModel.render(game.batch, dt);
        game.batch.end();

        renderGrid();
        debug();
    }
}
