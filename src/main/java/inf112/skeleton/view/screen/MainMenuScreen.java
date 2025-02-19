package inf112.skeleton.view.screen;

import inf112.skeleton.model.StarJump;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.Viewport;

public class MainMenuScreen implements Screen {
    final StarJump game;
    final Texture background;
    private final Stage stage;
    private final Skin skin;
    private final Sound buttonClick;


    public MainMenuScreen(StarJump game) {
        this.game = game;
        this.background = new Texture(Gdx.files.internal("src/main/assets/backgrounds/main_menu_background.png"));
        this.stage = new Stage(this.game.viewport);
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
        this.stage.dispose();
        this.skin.dispose();
        this.background.dispose();
    }

    private void draw() {
        game.viewport.apply();
        game.batch.setProjectionMatrix(game.viewport.getCamera().combined);
        float width = game.viewport.getWorldWidth();
        float height = game.viewport.getWorldHeight();

        // draw background
        game.batch.begin();
        game.batch.draw(background, 0, 0, width, height);
        game.batch.end();

        // Draw UI and title
        drawTitle(this.game.viewport);
        createUI(this.game.viewport);
    }

    private void input() {
    }

    private void logic() {

    }

    private void createUI(Viewport viewport) {
        float buttonGap = viewport.getWorldHeight() / 90f;
        float buttonWidth = viewport.getWorldWidth() / 3;
        float buttonHeight = viewport.getWorldHeight() / 10;
        float buttonX = viewport.getWorldWidth() / 2f - buttonWidth / 2;
        float buttonY = viewport.getWorldHeight() / 2f;

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
                buttonClick.play(1.0f);
                game.setScreen(new GameScreen(game)); // Switch to GameScreen
                dispose();
            }
        });

        quitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                buttonClick.play(1.0f);
                Gdx.app.exit();
            }
        });

        optionsButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                buttonClick.play(1.0f);
                System.out.println("OPTIONS NOT YET IMPLEMENTED");
            }
        });

        // Add button to the stage
        stage.addActor(startButton);
        stage.addActor(optionsButton);
        stage.addActor(quitButton);
    }

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
