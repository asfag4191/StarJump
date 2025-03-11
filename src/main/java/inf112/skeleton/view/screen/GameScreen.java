package inf112.skeleton.view.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
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

import inf112.skeleton.model.Player;
import inf112.skeleton.model.StarJump;
import inf112.skeleton.model.WorldModel;
import inf112.skeleton.model.items.powerup.PowerUpManager;
import inf112.skeleton.tools.listeners.PowerUpCollisionHandler;
import inf112.skeleton.tools.listeners.WorldContactListener;
import inf112.skeleton.utility.ColliderToBox2D;

public class GameScreen implements Screen {
    private final static boolean DEBUG_MODE = true;

    private final StarJump game;
    private final TiledMap map;
    private final OrthogonalTiledMapRenderer renderer;
    private final OrthographicCamera gamecam;
    private final Stage stage;
    private ShapeRenderer shapeRenderer;
    private final int gridSize = 1;
    private final World world;
    private final WorldModel worldModel;
    private final Player player;
    private Box2DDebugRenderer debugger;
    private PowerUpManager powerUpManager;



    public GameScreen(StarJump game) {
        this.game = game;

        // SINGLE SHARED WORLD instance
        this.world = new World(new Vector2(0, -9.81f), true);

        // Pass the SAME WORLD to WorldModel
        this.worldModel = new WorldModel(world);
        this.player = worldModel.getPlayer();
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

        //this.player = worldModel.getPlayer();
        // Center the camera
        float w = game.gameViewport.getWorldWidth();
        float h = game.gameViewport.getWorldHeight();
        gamecam.position.set(w / 2f, h / 2f, 0f);
        gamecam.update();

        // Creates a world and adds all colliders from tiled map
        //this.world = new World(new Vector2(0,-9.81f), true);
        ColliderToBox2D.parseTiledObjects(this.world, map.getLayers().get("Tiles").getObjects(), map.getProperties().get("tilewidth", Integer.class));
        this.debugger = new Box2DDebugRenderer();


        // Set up power-up 
        powerUpManager = new PowerUpManager(this, player);
            // Instantiate collision handlers
        WorldContactListener contactListener = new WorldContactListener(
            new PowerUpCollisionHandler()
            //new EnemyCollisionHandler(player),
            //new DangerCollisionHandler(player)
        );

    world.setContactListener(contactListener);

        
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
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE))
            player.applyImpulse(new Vector2(0, 4f));
        if (Gdx.input.isKeyJustPressed(Input.Keys.RIGHT) && player.getVelocity().x <= 2)
            player.applyImpulse(new Vector2(1f, 0));
        if (Gdx.input.isKeyJustPressed(Input.Keys.LEFT) && player.getVelocity().x >= -2)
            player.applyImpulse(new Vector2(-1f, 0));
    }

    public void update(float dt) {

        handleInput(dt);
        

        world.step(1 / 60f, 6, 2);

        powerUpManager.update(dt);  

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
            debugger.render(world, gamecam.combined);
            //debugger.render(world, gamecam.combined);
        }
        
    }

    private void logic(float dt) {
        //worldModel.onStep(dt);
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

        powerUpManager.update(dt);
        
        game.gameViewport.apply();
        gamecam.update();

        renderer.setView(gamecam);
        renderer.render();

        game.batch.setProjectionMatrix(gamecam.combined);
        game.batch.begin();
        worldModel.render(game.batch, dt);
        player.render(game.batch, dt);
        game.batch.end();


        renderGrid();
        debug();
    }

    public TiledMap getMap() {
        return map;
    }

    public World getWorld() {
        return world;
    }
    
    public PowerUpManager getPowerUpManager() {
        return powerUpManager;
    }
    




}
