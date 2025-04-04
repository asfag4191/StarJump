package inf112.skeleton.app;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import inf112.skeleton.model.GameState;
import inf112.skeleton.model.Settings;
import inf112.skeleton.view.screen.MainMenuScreen;

public class StarJump extends Game {
    public SpriteBatch batch;
    public BitmapFont font;

    // Separate viewports for different screens
    public StretchViewport uiViewport; // For menus (in pixels)
    public FitViewport gameViewport; // For the game (in tiles)

    // Feltvariabler for viewport-størrelser
    private static final float UI_WIDTH = 1280;
    private static final float UI_HEIGHT = 720;
    private static final float GAME_WIDTH = 20;
    private static final float GAME_HEIGHT = 15;

    // Collision bits for Box2D
    public static final short PLAYER_BIT = 1;
    public static final short POWERUP = 2;
    public static final short DANGEROUS_OBJECTS = 4;
    public static final short GROUND_BIT = 8;
    public static final short DOOR_BIT = 9;
    
    private GameState gameState;
    public Settings settings;

    @Override
    public void create() {
        this.batch = new SpriteBatch();
        this.font = new BitmapFont();

        // UI uses a standard pixel-based viewport
        this.uiViewport = new StretchViewport(UI_WIDTH, UI_HEIGHT);
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
