package inf112.skeleton.view.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;

import inf112.skeleton.model.StarJump;
import inf112.skeleton.model.tiles.TileLoader;
import inf112.skeleton.model.tiles.TileMap;

public class GameScreen implements Screen {

    final StarJump game;
    private TileLoader tileLoader;
    private TileMap tileMap;
    private int tileSize; // Adjust tile size


    public GameScreen(StarJump game) {
        this.game = game;
        this.tileMap = new TileMap("src/main/assets/map/Empty_map.txt");
        this.tileLoader = new TileLoader(this.tileMap); // Pass the TileMap instance
        updateTileSize();
        
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        input();
        logic();
        draw();
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
    
        int screenWidth = Gdx.graphics.getWidth();
        int screenHeight = Gdx.graphics.getHeight();
    
        // Adjust the tile size so the whole map fits the screen
        int tileSizeX = screenWidth / tileMap.getCols();
        int tileSizeY = screenHeight / tileMap.getRows();
    
        // Use the smaller size to prevent distortion
        this.tileSize = Math.max(32, Math.min(tileSizeX, tileSizeY));
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

    private void logic() {

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
    
        //int totalMapWidth = tileMap.getCols() * tileSize;
        //int totalMapHeight = tileMap.getRows() * tileSize;
    
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
    game.font.getData().setScale((game.viewport.getWorldHeight() * 2) / Gdx.graphics.getHeight());
    game.font.draw(game.batch, "GAME IS NOW ACTIVE WOOP WOOP", 6, Gdx.graphics.getHeight() - 40);
    game.font.draw(game.batch, "Press ESCAPE to go back to home screen", 5, Gdx.graphics.getHeight() - 80);

    game.batch.end();
}


 
     
    
}
    

