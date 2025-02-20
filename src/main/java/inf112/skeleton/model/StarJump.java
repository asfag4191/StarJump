package inf112.skeleton.model;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.StretchViewport;

import inf112.skeleton.view.screen.MainMenuScreen;

public class StarJump extends Game {

    /* ------------------- FIELD VARIABLES ------------------- */

    public SpriteBatch batch;
    public BitmapFont font;
    public StretchViewport viewport;
    private GameState gameState;
    public Settings settings;

    /* ----------------------- METHODS ----------------------- */


    @Override
    public void create() {
        this.batch = new SpriteBatch();
        this.font = new BitmapFont();
        this.viewport = new StretchViewport(1280, 720);  // Set a fixed logical size
        this.gameState = gameState.HOME_SCREEN;
        this.settings = new Settings();

        // fits the font to use our viewport
        font.setUseIntegerPositions(false);
        font.getData().setScale(viewport.getWorldHeight() / Gdx.graphics.getHeight());

        // get preferences and apply
        this.updateSettings();

        this.setScreen(new MainMenuScreen(this));
    }

    @Override
    public void render() {
        ScreenUtils.clear(Color.DARK_GRAY);
        super.render();
    }

    @Override
    public void dispose() {
        batch.dispose();
        font.dispose();
    }

    @Override
    public void resize(int width, int height) {
        if (width > 0 && height > 0) {  
            viewport.update(width, height, true);
            updateFontScale();
        }
    }

    /**
     * Updates the font scale based on the viewport size.
     */
    private void updateFontScale() {
        font.getData().setScale(viewport.getWorldHeight() / Gdx.graphics.getHeight());
    }

    /**
     * Might be redundant, in case remove later
     * @return current game state
     */
    public GameState getGameState() {
        return this.gameState;
    }

    public void updateSettings() {
        this.settings.applySettings();
    }

}
