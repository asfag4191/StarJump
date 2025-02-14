package inf112.skeleton.view.screen;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.ScreenUtils;
import inf112.skeleton.model.StarJump;

public class MainMenuScreen implements Screen {
    final StarJump game;

    public MainMenuScreen(StarJump game) {
        this.game = game;
    }

    @Override
    public void show() {
        
    }

    @Override
    public void render(float delta) {

        game.viewport.apply();
        game.batch.setProjectionMatrix(game.viewport.getCamera().combined);

        game.batch.begin();
        //draw text. Remember that x and y are in meters
        // (current resoulution is 64x64, change in StarJump.java)
        game.font.setColor(Color.WHITE);
        game.font.getData().setScale(game.viewport.getWorldHeight() / Gdx.graphics.getHeight());
        game.font.draw(game.batch, "Welcome to StarJump", 32 - 7, 40);
        game.font.draw(game.batch, "Tap anywhere to begin!", 32f - 7.1f, 38);
        game.batch.end();

        if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
            game.setScreen(new GameScreen(game));
            dispose();
        }
    }

    @Override
    public void resize(int width, int height) {
        game.viewport.update(width, height, true);
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
}
