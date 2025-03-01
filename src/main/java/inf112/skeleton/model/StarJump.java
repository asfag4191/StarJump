package inf112.skeleton.model;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;

import inf112.skeleton.view.screen.MainMenuScreen;

public class StarJump extends Game {
    public SpriteBatch batch;
    public BitmapFont font;

    // Separate viewports for different screens
    public FitViewport uiViewport;    // For menus (in pixels)
    public FitViewport gameViewport;  // For the game (in tiles)

    // Feltvariabler for viewport-størrelser
    private static final float UI_WIDTH = 1280;
    private static final float UI_HEIGHT = 720;
    private static final float GAME_WIDTH = 15; // Økt fra 15 til 20 for å vise mer av kartet
    private static final float GAME_HEIGHT = 13; // Økt fra 10 til 13

    private GameState gameState;
    public Settings settings;

    @Override
    public void create() {
        this.batch = new SpriteBatch();
        this.font = new BitmapFont();

        // UI uses a standard pixel-based viewport
        this.uiViewport = new FitViewport(UI_WIDTH, UI_HEIGHT);
        this.gameViewport = new FitViewport(GAME_WIDTH, GAME_HEIGHT);


        this.gameState = GameState.HOME_SCREEN;
        this.settings = new Settings();

        font.setUseIntegerPositions(false);
        font.getData().setScale(uiViewport.getWorldHeight() / Gdx.graphics.getHeight());

        this.updateSettings();

        // Set the first screen (Main Menu uses the UI viewport)
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
        // Update both viewports
        uiViewport.update(width, height, true);
        gameViewport.update(width, height, false);

        updateFontScale();
    }

    private void updateFontScale() {
        font.getData().setScale(uiViewport.getWorldHeight() / Gdx.graphics.getHeight());
    }

    public GameState getGameState() {
        return this.gameState;
    }

    public void updateSettings() {
        this.settings.applySettings();
    }
}

