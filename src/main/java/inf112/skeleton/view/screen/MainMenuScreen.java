package inf112.skeleton.view.screen;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;

import inf112.skeleton.model.StarJump;

public class MainMenuScreen implements Screen {
    final StarJump game;
    final Texture background;

    public MainMenuScreen(StarJump game) {
        this.game = game;
        this.background = new Texture(Gdx.files.internal("src/main/assets/backgrounds/main_menu_background.png"));
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

    private void draw() {
        game.viewport.apply();
        game.batch.setProjectionMatrix(game.viewport.getCamera().combined);
        float width = game.viewport.getWorldWidth();
        float height = game.viewport.getWorldHeight();

        game.batch.begin();
        // draw text. Remember that x and y are in meters
        // (current resolution is 64x64, change in StarJump.java)

        String text = "Welcome to StarJump";
        GlyphLayout layout = new GlyphLayout();

        float centerY = game.viewport.getWorldHeight() / 2;

        game.batch.draw(background, 0, 0, width, height);
        game.font.setColor(Color.WHITE);
        game.font.getData().setScale(game.viewport.getWorldHeight() * 3 / Gdx.graphics.getHeight());

        layout.setText(game.font, text);

        game.font.draw(game.batch, layout, game.viewport.getWorldWidth() / 2 - layout.width / 2,
                game.viewport.getWorldHeight() - (game.viewport.getWorldHeight() / 8));

        game.font.getData().setScale(game.viewport.getWorldHeight() * 2 / Gdx.graphics.getHeight());
        layout.setText(game.font, "Tap anywhere to begin!");
        game.font.draw(game.batch, layout, game.viewport.getWorldWidth() / 2 - layout.width / 2,
                game.viewport.getWorldHeight() - (game.viewport.getWorldHeight() / 4));
        game.batch.end();
    }

    private void input() {
        if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
            game.setScreen(new GameScreen(game));
            dispose();
        }
    }

    private void logic() {

    }
}
