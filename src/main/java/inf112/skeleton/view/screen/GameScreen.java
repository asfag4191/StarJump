package inf112.skeleton.view.screen;

import java.util.List;

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

import inf112.skeleton.app.StarJump;
import inf112.skeleton.model.WorldModel;
import inf112.skeleton.model.character.controllable_characters.Player;
import inf112.skeleton.model.character.enemy.EnemyManager;
import inf112.skeleton.model.items.door.DoorManager;
import inf112.skeleton.model.items.door.DoorObject;
import inf112.skeleton.model.items.powerup.PowerUpManager;
import inf112.skeleton.utility.ColliderToBox2D;
import inf112.skeleton.utility.listeners.CharacterContactHandler;
import inf112.skeleton.utility.listeners.CollisionHandler;
import inf112.skeleton.utility.listeners.EnemyCollisionHandler;
import inf112.skeleton.utility.listeners.PowerUpCollisionHandler;
import inf112.skeleton.utility.listeners.ProjectileCollisionHandler;
import inf112.skeleton.utility.listeners.WorldContactListener;
import inf112.skeleton.view.HUD;

public class GameScreen implements Screen {
    public final static boolean DEBUG_MODE = false;

    private final StarJump game;
    private final TiledMap tmxmap;
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
    private DoorManager doorManager;
    private HUD hud;
    private EnemyManager enemyManager;
    private String currentGame;
    private static Integer hp;

    public GameScreen(StarJump game, String map) {
        this.game = game;

        // SINGLE SHARED WORLD instance
        this.world = new World(new Vector2(0, -9.81f * 2), true);

        // Pass the SAME WORLD to WorldModel
        this.worldModel = new WorldModel(world);
        this.player = worldModel.createPlayer();
        this.debugger = new Box2DDebugRenderer(true, true, true, true, true, true);

        // Use gameViewport (tile-based)
        this.gamecam = (OrthographicCamera) game.gameViewport.getCamera();
        this.stage = new Stage(this.game.gameViewport);
        Gdx.input.setInputProcessor(this.stage);
        this.shapeRenderer = new ShapeRenderer();

        // Load TMX map
        tmxmap = new TmxMapLoader().load("src/main/assets/map/tilemaps/" + map);

        // Set up renderer (assuming tiles are 16x16 pixels)
        renderer = new OrthogonalTiledMapRenderer(tmxmap, 1f / 16f);

        // Center the camera
        float w = game.gameViewport.getWorldWidth();
        float h = game.gameViewport.getWorldHeight();
        gamecam.position.set(w / 2f, h / 2f, 0f);
        gamecam.update();

        // Creates a world and adds all colliders from tiled map
        ColliderToBox2D.parseTiledObjects(this.worldModel.world, tmxmap.getLayers().get("Tiles").getObjects(),
                tmxmap.getProperties().get("tilewidth", Integer.class));
        this.debugger = new Box2DDebugRenderer();

        // Set up power-up
        powerUpManager = new PowerUpManager(this, player.character);

        // Set up door
        doorManager = new DoorManager(this);

        enemyManager = new EnemyManager(this);
        enemyManager.loadEnemiesFromMap(getMap());

        // Instantiate collision handlers
        CollisionHandler[] handlers = { new PowerUpCollisionHandler(), new CharacterContactHandler(),
                new EnemyCollisionHandler(), new ProjectileCollisionHandler() };
        WorldContactListener contactListener = new WorldContactListener(List.of(handlers));

        world.setContactListener(contactListener);

        // Initialize HUD with the game's SpriteBatch
        hud = new HUD(game.batch, player.character);
        this.currentGame = map;
        hp = HUD.getHp();
    }

    public GameScreen(StarJump game) {
        this(game, "map_level_1.tmx");
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float dt) {
        input();
        draw(dt);
        renderGrid();
        debug();
        logic(dt);
    }

    @Override
    public void resize(int width, int height) {
        game.gameViewport.update(width, height, false);
        // Recenter camera based on fixed world size
        hud.getHudViewport().update(width, height, true);
        float w = game.gameViewport.getWorldWidth();
        float h = game.gameViewport.getWorldHeight();
        gamecam.position.set(w / 2f, h / 2f, 0);
        gamecam.update();
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
        tmxmap.dispose();
        renderer.dispose();
        hud.dispose();

    }

    private void debug() {
        if (DEBUG_MODE) {
            debugger.render(worldModel.world, gamecam.combined);
        }

    }

    private void logic(float dt) {
        worldModel.onStep(dt);

        powerUpManager.update(dt);
        enemyManager.update(dt);
        powerUpManager.update(dt);
        //doorManager.update(dt);
        enemyManager.update(dt);
        hud.update(dt); //

        adjustCamera(this.player, 3f);
        doorManager.update(dt);

        checkLevelCompletion();
    }

    private void checkLevelCompletion() {
        if (DoorObject.isTriggered()) {
            transitionScreen();
        } else if (HUD.getHp() <= 0) {
            game.setScreen(new GameOverScreen(game, false));
            dispose();
        }
    }

    private void transitionScreen() {
        if (currentGame.equals("map_level1.tmx")) {
            game.setScreen(new GameScreen(game, "map_level_2.tmx"));
        } else {
            game.setScreen(new GameOverScreen(game, true));
        }
        DoorObject.resetTrigger();
        dispose();
    }

    /**
     * Renders the grid from World, makes for easy tile debugging
     */
    private void renderGrid() {
        if (!DEBUG_MODE)
            return;

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
        this.player.controller.update();
    }

    private void draw(float dt) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.gameViewport.apply();

        renderer.setView(gamecam);
        renderer.render();

        game.batch.setProjectionMatrix(gamecam.combined); // setting up the camera for batch.
        game.batch.begin(); // START the SpriteBatch.
        powerUpManager.render(game.batch); // draw power-ups
        worldModel.render(game.batch, dt);
        enemyManager.render(game.batch, dt);

        game.batch.end(); // END the SpriteBatch

        // Draw HUD last
        hud.hudStage.draw();

    }

    public TiledMap getMap() {
        return tmxmap;
    }

    public World getWorld() {
        return world;
    }

    public PowerUpManager getPowerUpManager() {
        return powerUpManager;
    }

    /**
     * Adjusts the camera to follow the player
     * 
     * @param player         The player to follow
     * @param playerCamDelta The maximum distance the camera can be from the player
     */
    private void adjustCamera(Player player, float playerCamDelta) {
        Vector2 playerPos = player.character.getPosition();
        float playerPosX = playerPos.x;
        float playerPosY = playerPos.y;

        // Viewport height in world units
        float viewportHeight = game.gameViewport.getWorldHeight();
        float viewportWidth = game.gameViewport.getWorldWidth();

        // Calculate clamping boundaries
        float maxCameraY = tmxmap.getProperties().get("height", Integer.class) - (viewportHeight / 2f);
        float minCameraY = viewportHeight / 2f;
        float maxCameraX = tmxmap.getProperties().get("width", Integer.class) - (viewportWidth / 2f);
        float minCameraX = viewportWidth / 2f;

        // Clamp the camera's position to player position
        if (gamecam.position.y < playerPosY - playerCamDelta) {
            gamecam.position.y = playerPosY - playerCamDelta;
        } else if (gamecam.position.y > playerPosY + playerCamDelta) {
            gamecam.position.y = playerPosY + playerCamDelta;
        }
        if (gamecam.position.x < playerPosX - playerCamDelta) {
            gamecam.position.x = playerPosX - playerCamDelta;
        } else if (gamecam.position.x > playerPosX + playerCamDelta) {
            gamecam.position.x = playerPosX + playerCamDelta;
        }

        // Clamp the camera's position
        gamecam.position.y = Math.max(minCameraY, Math.min(gamecam.position.y, maxCameraY));
        gamecam.position.x = Math.max(minCameraX, Math.min(gamecam.position.x, maxCameraX));
        gamecam.update();
    }

    public Player getPlayer() {
        return player;
    }

    public WorldModel getWorldModel() {
        return worldModel;
    }

}
