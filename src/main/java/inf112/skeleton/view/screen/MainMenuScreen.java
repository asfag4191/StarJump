package inf112.skeleton.view.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.Viewport;
import inf112.skeleton.app.StarJump;

import java.util.concurrent.TimeUnit;

public class MainMenuScreen implements Screen {
    final StarJump game;
    final Texture background;
    private final Stage stage;
    private final Skin skin;
    private final Sound buttonClick;
    private float buttonClickVolume = 0.5f;

    /**
     * Main menu screen
     * 
     * @param game
     */
    public MainMenuScreen(StarJump game) {
        this.game = game;

        /* LOAD ASSETS */

        // TO CHANGE FONT OF TITLE AND BUTTONSTYLE, CHANGE THIS SKIN ONLY
        this.skin = new Skin(Gdx.files.internal("src/main/assets/skins/craftacular/skin/craftacular-ui.json"));

        // TO CHANGE MAIN MENU BACKGROUND, CHANGE THIS
        this.background = new Texture(Gdx.files.internal("src/main/assets/backgrounds/main_menu_background.png"));

        // TO CHANGE BUTTON CLICK AUDIO, CHANGE THIS
        this.buttonClick = Gdx.audio.newSound(Gdx.files.internal("src/main/assets/audio/sounds/buttonClick-short.mp3"));

        // Use the UI viewport
        this.stage = new Stage(this.game.uiViewport);
        Gdx.input.setInputProcessor(this.stage);

        // Create UI
        Table table = new Table();
        table.setFillParent(true);
        createUI(table);
        stage.addActor(table);
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
    }

    private void input() {
    }

    private void logic() {

    }

    /**
     * Creates the UI for the main menu screen
     *
     * To add more elements:
     * 1. Create the UI-element
     * 2. Add a listener
     * 3. Add the element to the table (with padding)
     * 
     * @param table
     */
    private void createUI(Table table) {
        // UI elements to add
        TextButton startButton = new TextButton("Start Game", skin);
        TextButton levelSelectButton = new TextButton("Select Level", skin);
        TextButton optionsButton = new TextButton("Options", skin);
        TextButton howToPlayButton = new TextButton("How to play", skin);
        TextButton quitButton = new TextButton("Quit", skin);

        // Add buttons click events
        startButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                // buttonClick.play(buttonClickVolume);
                game.setScreen(new GameScreen(game, "map_level1.tmx")); // Switch to GameScreen
                dispose();
            }
        });

        levelSelectButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                buttonClick.play(buttonClickVolume);
                game.setScreen(new LevelSelectScreen(game));
            }
        });

        quitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                buttonClick.play(buttonClickVolume);
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
                buttonClick.play(buttonClickVolume);
                game.setScreen(new SettingsScreen(game));
            }
        });

        howToPlayButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                buttonClick.play(buttonClickVolume);
                game.setScreen(new HelpScreen(game));
            }
        });

        // Add buttons to the table
        table.add(startButton).fillX().uniformX();
        table.row().pad(0, 0, 0, 0);
        table.add(levelSelectButton).fillX().uniformX();
        table.row().pad(0, 0, 0, 0);
        table.add(optionsButton).fillX().uniformX();
        table.row().pad(0, 0, 0, 0);
        table.add(howToPlayButton).fillX().uniformX(); 
        table.row().pad(0, 0, 0, 0);
        table.add(quitButton).fillX().uniformX();
    }

    /**
     * Draws title on menu screen
     * Separated from rest of UI in case we want to animate the screen
     * 
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
