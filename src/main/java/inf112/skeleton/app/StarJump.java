package inf112.skeleton.app;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;

import inf112.skeleton.view.screen.MainMenuScreen;

public class StarJump extends Game {
    public SpriteBatch batch;
    public BitmapFont font;

    public StretchViewport uiViewport; // For menus (in pixels)
    public FitViewport gameViewport; // For the game (in tiles)

    // Field variables for viewport sizes
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
    public static final short GROUND_SENSOR_BIT = 10;

    @Override
    public void create() {
        this.batch = new SpriteBatch();
        this.font = new BitmapFont();

        // UI uses a standard pixel-based viewport
        this.uiViewport = new StretchViewport(UI_WIDTH, UI_HEIGHT);
        this.gameViewport = new FitViewport(GAME_WIDTH, GAME_HEIGHT);

        font.setUseIntegerPositions(false);
        font.getData().setScale(uiViewport.getWorldHeight() / Gdx.graphics.getHeight());

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
        uiViewport.update(width, height, true);
        gameViewport.update(width, height, false);

        updateFontScale();
    }

    private void updateFontScale() {
        font.getData().setScale(uiViewport.getWorldHeight() / Gdx.graphics.getHeight());
    }

}
