package inf112.skeleton.view.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.Viewport;
import inf112.skeleton.model.StarJump;

import java.util.concurrent.TimeUnit;

/*
TODO:
 [] Change the layout to use a table instead of forced values
        - Allows for easier implementation of new features later
 */

public class MainMenuScreen implements Screen {
    final StarJump game;
    final Texture background;
    private final Stage stage;
    private final Skin skin;
    private final Sound buttonClick;
    /**
     * Main menu screen
     * @param game
     */
    public MainMenuScreen(StarJump game) {
        this.game = game;
        this.background = new Texture(Gdx.files.internal("src/main/assets/backgrounds/main_menu_background.png"));
        // Use the UI viewport (pixel-based)
        this.stage = new Stage(this.game.uiViewport);
        Gdx.input.setInputProcessor(this.stage);

        /*  TO CHANGE FONT OF TITLE AND BUTTONSTYLE, CHANGE THIS SKIN ONLY  */
        this.skin = new Skin(Gdx.files.internal("src/main/assets/skins/craftacular/skin/craftacular-ui.json"));
        /*  TO CHANGE BUTTON CLICK AUDIO, CHANGE THIS  */
        this.buttonClick = Gdx.audio.newSound(Gdx.files.internal("src/main/assets/audio/sounds/buttonClick-short.mp3"));
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        input();
        logic();
        draw();
        this.stage.act(delta);
        this.stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        game.uiViewport.update(width, height, true);
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
        this.stage.dispose();
        this.skin.dispose();
        this.background.dispose();
    }

    private void draw() {
        game.uiViewport.apply();
        game.batch.setProjectionMatrix(game.uiViewport.getCamera().combined);
        float width = game.uiViewport.getWorldWidth();
        float height = game.uiViewport.getWorldHeight();

        // draw background
        game.batch.begin();
        game.batch.draw(background, 0, 0, width, height);
        game.batch.end();

        // Draw UI and title
        drawTitle(this.game.uiViewport);
        createUI(this.game.uiViewport);
    }

    private void input() {
    }

    private void logic() {

    }

    /**
     * Draws the UI onto the screen
     * Subject to change
     * @param viewport
     */
    private void createUI(Viewport viewport) {
        float buttonGap = viewport.getWorldHeight() / 90f;
        float buttonWidth = viewport.getWorldWidth() / 3;
        float buttonHeight = viewport.getWorldHeight() / 10;
        float buttonX = viewport.getWorldWidth() / 2f - buttonWidth / 2;
        float buttonY = viewport.getWorldHeight() / 2f;
        //float buttonClickVolume = 0.5f * (Float.parseFloat(this.game.settings.getSetting("volume")) / 100);

        // Create buttons
        TextButton startButton = new TextButton("Start Game", skin);
        startButton.setSize(buttonWidth, buttonHeight);
        startButton.setPosition(buttonX, buttonY);

        TextButton optionsButton = new TextButton("Options", skin);
        optionsButton.setSize(buttonWidth, buttonHeight);
        optionsButton.setPosition(buttonX, buttonY - buttonHeight - buttonGap);

        TextButton quitButton = new TextButton("Quit", skin);
        quitButton.setSize(buttonWidth, buttonHeight);
        quitButton.setPosition(buttonX, buttonY - 2*buttonHeight - 2*buttonGap);

        // Add buttons click events
        startButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                //buttonClick.play(buttonClickVolume);
                game.setScreen(new GameScreen(game)); // Switch to GameScreen
                dispose();
            }
        });

        quitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                //buttonClick.play(buttonClickVolume);
                // just to allow the buttonclick-sound to play
                try {
                    TimeUnit.MILLISECONDS.sleep(400);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                Gdx.app.exit();
            }
        });

        optionsButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                //buttonClick.play(buttonClickVolume);
                game.setScreen(new SettingsScreen(game));
            }
        });

        // Add buttons to the stage
        stage.addActor(startButton);
        stage.addActor(optionsButton);
        stage.addActor(quitButton);
    }

    /**
     * Draws title on menu screen
     * Separated from rest of UI in case we want to animate the screen
     * @param viewport
     */
    private void drawTitle(Viewport viewport) {
        Label.LabelStyle titleStyle = new Label.LabelStyle();
        titleStyle.font = this.skin.getFont("title");
        titleStyle.font.getData().setScale(1.6f);
        Label titleLabel = new Label("StarJump", titleStyle);
        titleLabel.setPosition(viewport.getWorldWidth() / 2 - titleLabel.getWidth() / 2,
                viewport.getWorldHeight() - (viewport.getWorldHeight() / 3.3f));
        stage.addActor(titleLabel);
    }
}
