package inf112.skeleton.view.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import inf112.skeleton.app.StarJump;
import inf112.skeleton.model.items.powerup.DiamondPowerUp;
import inf112.skeleton.view.HUD;

public class GameOverScreen implements Screen {

    final StarJump game;
    private final Stage stage;
    private final Skin skin;
    private final Texture background;

    public GameOverScreen(StarJump game) {
        this.game = game;

        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        skin = new Skin(Gdx.files.internal("src/main/assets/skins/craftacular/skin/craftacular-ui.json"));
        background = new Texture(Gdx.files.internal("src/main/assets/backgrounds/main_menu_background.png"));

        Table table = new Table();
        table.setFillParent(true);
        stage.addActor(table);

        Label gameOverLabel = new Label(text(), skin, "title");
        Label scoreLabel = new Label("Score: " + DiamondPowerUp.getScore(), skin, "title");

        gameOverLabel.setFontScale(2f);
        scoreLabel.setFontScale(0.7f);

        TextButton menuButton = new TextButton("Main Menu", skin);
        TextButton quitButton = new TextButton("Quit", skin);


        menuButton.addListener(new ClickListener() {
            @Override
            public void clicked(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y) {
                game.setScreen(new MainMenuScreen(game));
                DiamondPowerUp.disposeScore();
                dispose();
            }
        });

        quitButton.addListener(new ClickListener() {
            @Override
            public void clicked(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });

        table.add(gameOverLabel).padBottom(40).row();
        table.add(scoreLabel).padBottom(35).row();
        table.add(menuButton).width(200).pad(10).row();
        table.add(quitButton).width(200).pad(10);
    }

    private String text(){
        int hp = HUD.getHp();
        if (hp >= 0){
            return "You won!";
        } else return "You lost :(";
    }


    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        stage.getBatch().begin();
        stage.getBatch().draw(background, 0, 0, stage.getViewport().getWorldWidth(), stage.getViewport().getWorldHeight());
        stage.getBatch().end();

        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {
        stage.dispose();
        skin.dispose();
        background.dispose();
    }
}
