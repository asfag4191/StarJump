package inf112.skeleton.view;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import inf112.skeleton.model.items.powerup.DiamondPowerUp;

/**
 * The HUD (Heads-Up Display) class manages the game's user interface elements,
 * such as the player's life and score. It displays these elements on the screen
 * using a stage and labels.
 */
public class HUD {
    public Stage hudStage;
    private FitViewport hudViewport;
    private OrthographicCamera hudCamera;

    private Integer life;
    private Integer score;

    Label lifeLabel;
    Label scoreLabel;

     /**
     * Creates a new HUD instance.
     *
     * @param batch The SpriteBatch used for rendering the HUD elements.
     */
    public HUD(SpriteBatch batch) {
        life = 3;    
        score = DiamondPowerUp.getScore(); // Get initial score

        hudCamera = new OrthographicCamera();
        hudViewport = new FitViewport(1280, 720, hudCamera);
        hudStage = new Stage(hudViewport, batch);

        Table table = new Table();
        table.top();
        table.setFillParent(true);

        BitmapFont font = new BitmapFont();
        font.getData().setScale(2f);

        lifeLabel = new Label(String.format("LIFE: %d", life), new Label.LabelStyle(font, Color.WHITE));
        scoreLabel = new Label(String.format("SCORE: %d", score), new Label.LabelStyle(font, Color.WHITE));

        table.add(lifeLabel).expandX().padTop(50).padLeft(100).left();  
        table.add(scoreLabel).expandX().padTop(50).padRight(100).right(); 

        hudStage.addActor(table);
    }


     /**
     * Updates the HUD with new life and score values.
     *
     * @param life The player's current life.
     * @param score The player's current score.
     */
    public void update() {
        this.life = life;
        this.score = DiamondPowerUp.getScore(); // Update score from DiamondPowerUp
        lifeLabel.setText(String.format("LIFE: %d", life));
        scoreLabel.setText(String.format("SCORE: %d", score));
    }

    /**
     * Disposes of the HUD stage to free up resources.
     */
    public void dispose() {
        hudStage.dispose();
    }
        
    /**
     * Gets the viewport used by the HUD.
     *
     * @return The FitViewport used for the HUD.
     */
    public Viewport getHudViewport() {
        return hudViewport;
    }
}
