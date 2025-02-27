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
import inf112.skeleton.model.tiles.TileLoader;
import inf112.skeleton.model.tiles.TileMap;

public class GameScreen implements Screen {
    private static boolean DEBUG_MODE = true;

    private final StarJump game;
    private final TileLoader tileLoader;
    private final TileMap tileMap;
    private float tileSize; // Adjust tile size

    private Box2DDebugRenderer debugger;
    private WorldModel worldModel;

    public GameScreen(StarJump game) {
        this.game = game;
        this.worldModel = new WorldModel(new Vector2(0, -9.81f), true);
        this.debugger = new Box2DDebugRenderer(true,true,true,true,true,true);
        this.tileMap = new TileMap("src/main/assets/map/Empty_map.txt");
        this.tileLoader = new TileLoader(this.tileMap); // Pass the TileMap instance
        updateTileSize();
    }

    @Override
    public void show() {
        // TEST: CREATE AN OBJECT
        Texture texture = new Texture(Gdx.files.internal("star.png"));
        float middleX = game.viewport.getCamera().viewportWidth/2f;
        float middleY = game.viewport.getCamera().viewportHeight/2f;
        BoxCollider tile = worldModel.createTile(new Vector2(middleX, middleY), new Vector2(1,1), texture);
        BoxCollider ground = worldModel.createTile(new Vector2(middleX, middleY*0.25f), new Vector2(10,2), texture);
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
    public void resize(int width, int height) {
        if (width > 0 && height > 0) {
            game.viewport.update(width, height, true);
            updateTileSize();
        }
    }


    private void updateTileSize() {
        if (tileMap == null) return;

        float screenWidth = this.game.viewport.getWorldWidth();
        float screenHeight = this.game.viewport.getWorldHeight();

        // Adjust the tile size so the whole map fits the screen
        float tileSizeX = screenWidth / tileMap.getCols();
        float tileSizeY = screenHeight / tileMap.getRows();

        // Use the smaller size to prevent distortion
        // this.tileSize = Math.max(32, Math.min(tileSizeX, tileSizeY));
        this.tileSize = Math.min(tileSizeX, tileSizeY);
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
        tileLoader.dispose();
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

        // Calculate the starting position to center the map
        int startX = 0;
        int startY = 0;

        for (int row = 0; row < tileMap.getRows(); row++) {
            for (int col = 0; col < tileMap.getCols(); col++) {
                int tileID = tileMap.getMapData()[row][col];
                Texture texture = tileLoader.getTileTexture(tileID);

                if (texture != null) {
                    game.batch.draw(texture, startX + col * tileSize, startY + (tileMap.getRows() - row - 1) * tileSize, tileSize, tileSize);
                }
            }
        }

    // Draw text (adjusted so it stays within viewport)
    game.font.setColor(Color.WHITE);
    game.font.getData().setScale((game.viewport.getWorldWidth() * 2) / game.viewport.getScreenWidth());
    game.font.draw(game.batch, "GAME IS NOW ACTIVE WOOP WOOP", game.viewport.getWorldWidth() / 3f, game.viewport.getWorldHeight() / 1.8f);
    game.font.draw(game.batch, "Press ESCAPE to go back to home screen", game.viewport.getWorldWidth() / 3f, game.viewport.getWorldHeight() / 2f);
    worldModel.render(game.batch);

    game.batch.end();
}





}
