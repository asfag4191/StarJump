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

import inf112.skeleton.model.character.Character;
import inf112.skeleton.model.character.CharacterAttributes;
import inf112.skeleton.model.character.controllable_characters.Player;
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

    private Character character;
    private static Integer hp;
    private Integer score;

    Label hpLabel;
    Label scoreLabel;

    /**
     * Creates a new HUD instance.
     *
     * @param batch The SpriteBatch used for rendering the HUD elements.
     */
    public HUD(SpriteBatch batch, Character character) {
        this.character = character;
        hp = ((int) character.attributes.getHp());
        score = DiamondPowerUp.getScore(); // Get initial score

        hudCamera = new OrthographicCamera();
        hudViewport = new FitViewport(1280, 720, hudCamera);
        hudStage = new Stage(hudViewport, batch);

        Table table = new Table();
        table.top();
        table.setFillParent(true);

        BitmapFont font = new BitmapFont();
        font.getData().setScale(2f);

        hpLabel = new Label(String.format("HP: %d", hp), new Label.LabelStyle(font, Color.WHITE));
        scoreLabel = new Label(String.format("SCORE: %d", score), new Label.LabelStyle(font, Color.WHITE));

        table.add(hpLabel).expandX().padTop(50).padLeft(100).left();
        table.add(scoreLabel).expandX().padTop(50).padRight(100).right();

        hudStage.addActor(table);
    }

    /**
     * Updates the HUD with new life and score values.
     *
     * @param life  The player's current life.
     * @param score The player's current score.
     */
    public void update(float dt) {
        this.hp = ((int) character.attributes.getHp());
        this.score = DiamondPowerUp.getScore(); // Update score from DiamondPowerUp
        hpLabel.setText(String.format("LIFE: %d", hp));
        scoreLabel.setText(String.format("SCORE: %d", score));
        this.hudStage.act(dt);
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


    /**
     * Gets the current HP total
     *
     * @return the current HP total as shown in HUD
     */
    public static int getHp() {return hp;}
}
