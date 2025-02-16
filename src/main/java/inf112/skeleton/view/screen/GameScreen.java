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
    private int tileSize = 4; // Adjust tile size


    public GameScreen(StarJump game) {
        this.game = game;
        this.tileLoader = new TileLoader();
        this.tileMap = new TileMap("src/main/assets/map/Empty_map.txt");
        
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
        
        for (int row = 0; row < tileMap.getRows(); row++) {
            for (int col = 0; col < tileMap.getCols(); col++) {
                int tileID = tileMap.getMapData()[row][col];
                Texture texture = tileLoader.getTileTexture(tileID);
        
                if (texture != null) {
                    game.batch.draw(texture, col * tileSize, (tileMap.getRows() - row - 1) * tileSize, tileSize, tileSize);
                }
            }
        }
        //draw text. Remember that x and y are in meters
        // (current resoulution is 64x64, change in StarJump.java)
        game.font.setColor(Color.WHITE);
        game.font.getData().setScale((game.viewport.getWorldHeight()*2) / Gdx.graphics.getHeight());
        game.font.draw(game.batch, "GAME IS NOW ACTIVE WOOP WOOP", 6, 40);
        game.font.draw(game.batch, "Press ESCAPE to go back to home screen", 5, 34);
        game.batch.end();
    }
    }

