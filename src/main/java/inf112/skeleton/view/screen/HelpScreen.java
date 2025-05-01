package inf112.skeleton.view.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import inf112.skeleton.app.StarJump;

/**
 * Create a help screen for the game, how to play.
 */
public class HelpScreen implements Screen {

    private final StarJump game;
    private final Stage stage;
    private final Skin skin;
    private final Texture background;
    private final SpriteBatch batch;

    private final Texture diamondTexture;
    private final Texture rainbowTexture;
    private final Texture playerTexture;
    private final Texture enemyTexture;

    public HelpScreen(StarJump game) {
        this.game = game;
        this.stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        this.skin = new Skin(Gdx.files.internal("src/main/assets/skins/default/skin/uiskin.json"));
        this.background = new Texture(Gdx.files.internal("src/main/assets/backgrounds/main_menu_background.png"));
        this.batch = new SpriteBatch();

        this.diamondTexture = new Texture(Gdx.files.internal("src/main/assets/map/tilemaps/tilesets/Diamond.png"));
        this.rainbowTexture = new Texture(Gdx.files.internal("src/main/assets/map/tilemaps/tilesets/rainbow16.png"));
        this.playerTexture = new Texture(Gdx.files.internal("src/main/assets/sprites/CoolStar.png"));
        this.enemyTexture = new Texture(Gdx.files.internal("src/main/assets/sprites/simple_blackhole.png"));
        Table mainTable = new Table();
        mainTable.setFillParent(true);
        mainTable.center();
        stage.addActor(mainTable);

        Table box = new Table();
        box.setBackground(skin.newDrawable("white", 0f, 0f, 0f, 0.7f));
        box.pad(30);
        box.defaults().space(12);

        Label title = new Label("How to Play!", skin, "default");
        title.setFontScale(2f);
        box.add(title).center().row();

        Label movement = new Label("Press arrow keys og A&D to move left/right", skin);
        Label jump = new Label("Use the space bar to jump (you can double jump!)", skin);

        movement.setFontScale(1.2f);
        jump.setFontScale(1.2f);
        box.add(movement).center().row();
        box.add(jump).center().row();

        Image playerImage = new Image(playerTexture);
        Label playerLabel = new Label("The player", skin);
        playerLabel.setFontScale(1.1f);
        Table playerRow = new Table();
        playerRow.add(playerImage).size(48).padRight(10);
        playerRow.add(playerLabel).center();
        box.add(playerRow).center().row();

        Image diamondImage = new Image(diamondTexture);
        Label collectLabel = new Label("Collect diamonds", skin);
        collectLabel.setFontScale(1.2f);
        Table diamondRow = new Table();
        diamondRow.add(diamondImage).size(32).padRight(10);
        diamondRow.add(collectLabel);
        box.add(diamondRow).row();

        Image rainbowImage = new Image(rainbowTexture);
        Label powerupLabel = new Label("Grab power-ups to fly!", skin);
        powerupLabel.setFontScale(1.2f);
        Table powerupRow = new Table();
        powerupRow.add(rainbowImage).size(32).padRight(10);
        powerupRow.add(powerupLabel);
        box.add(powerupRow).row();

        Image enemyImage = new Image(enemyTexture);
        Label avoidLabel = new Label("Avoid the black holes!", skin);
        avoidLabel.setFontScale(1.2f);

        Table avoidRow = new Table();
        avoidRow.add(enemyImage).size(32).padRight(10);
        avoidRow.add(avoidLabel);

        box.add(avoidRow).center().padTop(10).row();

        TextButton backButton = new TextButton("Back to Menu", skin);
        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new MainMenuScreen(game));
            }
        });

        box.add(backButton).width(200).padTop(20).center();
        mainTable.add(box);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        batch.setProjectionMatrix(stage.getCamera().combined);
        batch.begin();
        batch.draw(background, 0, 0, stage.getViewport().getWorldWidth(), stage.getViewport().getWorldHeight());
        batch.end();

        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
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
        stage.dispose();
        skin.dispose();
        background.dispose();
        batch.dispose();
        diamondTexture.dispose();
        rainbowTexture.dispose();
        playerTexture.dispose();
    }
}
