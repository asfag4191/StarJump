package inf112.skeleton.view.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;

import inf112.skeleton.model.StarJump;

public class GameScreen implements Screen {
    private final StarJump game;
    private final TiledMap map;
    private final OrthogonalTiledMapRenderer renderer;
    private final OrthographicCamera gamecam;
    //private final Player player;
    private final Stage stage;

    public GameScreen(StarJump game) {
        this.game = game;

        // Use gameViewport (tile-based)
        gamecam = (OrthographicCamera) game.gameViewport.getCamera();
        this.stage = new Stage(this.game.gameViewport);
        Gdx.input.setInputProcessor(this.stage);


        // Load TMX map
        map = new TmxMapLoader().load("src/main/assets/map/tilemaps/map_level1.tmx");
        if (map == null) {
            System.out.println("ERROR: TMX map failed to load!");
        } else {
            System.out.println("SUCCESS: TMX map loaded!");
        }

        // Set up renderer (assuming tiles are 16x16 pixels)
        renderer = new OrthogonalTiledMapRenderer(map, 1f / 16f);

        // Center the camera
        gamecam.position.set(7.5f, 5f, 0);
        gamecam.update();
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    public void handleInput(float dt) {
        if (Gdx.input.isTouched())
            gamecam.position.y += 10 * dt;
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
        
        // Debug output
        System.out.println("Camera Y: " + gamecam.position.y);
        System.out.println("Max Camera Y: " + maxCameraY);
        System.out.println("Min Camera Y: " + minCameraY);
        
        gamecam.update();
    }
    
    
    
    

    @Override
    public void render(float delta) {
        update(delta);
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.gameViewport.apply();
        gamecam.update();

        renderer.setView(gamecam);
        renderer.render();
    }

    @Override
    public void resize(int width, int height) {
        float aspectRatio = (float) height / width; // Calculate aspect ratio
        float worldWidth = 15f; // Keep 15 tiles wide
        float worldHeight = worldWidth * aspectRatio; // Adjust height dynamically
    
        game.gameViewport.setWorldSize(worldWidth, worldHeight);
        game.gameViewport.update(width, height, false);
    
        // Recenter camera
        gamecam.position.set(worldWidth / 2f, worldHeight / 2f, 0);
        gamecam.update();
    }

    @Override public void pause() { }
    @Override public void resume() { }
    @Override public void hide() { }

    @Override
    public void dispose() {
        map.dispose();
        renderer.dispose();
    }
}

