package inf112.skeleton.model;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;

import inf112.skeleton.view.screen.MainMenuScreen;

public class StarJump extends Game {

    /* ------------------- FIELD VARIABLES ------------------- */

    public SpriteBatch batch;
    public BitmapFont font;
    public FitViewport viewport;
    private GameState gameState;

    /* ----------------------- METHODS ----------------------- */


    @Override
    public void create() {
        this.batch = new SpriteBatch();
        this.font = new BitmapFont();
        this.viewport = new FitViewport(64, 64);
        this.gameState = gameState.HOME_SCREEN;

        // fits the font to use our viewport
        font.setUseIntegerPositions(false);
        font.getData().setScale(viewport.getWorldHeight() / Gdx.graphics.getHeight());

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

    public GameState getGameState() {
        return this.gameState;
    }


}
