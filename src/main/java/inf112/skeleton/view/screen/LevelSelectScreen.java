package inf112.skeleton.view.screen;

import java.util.ArrayList;
import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.Viewport;

import inf112.skeleton.app.StarJump;
import inf112.skeleton.utility.FolderParser;

public class LevelSelectScreen implements Screen {
    final StarJump game;
    final Texture background;
    private final Stage stage;
    private final Skin skin;
    private final Sound buttonClick;

    /**
     * Level select screen
     * 
     * @param game
     */
    public LevelSelectScreen(StarJump game) {
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
        draw();
        this.stage.act(delta);
        this.stage.draw();

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

        drawTitle(this.game.uiViewport);

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

    private void createUI(Table table) {
        HashMap<String, String> levels = new HashMap<>();
        ArrayList<String> levelFiles = FolderParser.getTMXFilesInFolder("src/main/assets/map/tilemaps");
        for (String levelFile : levelFiles) {
            levels.put(getLevelName(levelFile), levelFile);
        }

        // Create buttons for each level
        ArrayList<TextButton> buttons = new ArrayList<>();
        for (String levelName : levels.keySet()) {
            buttons.add(new TextButton(levelName, this.skin));
            buttons.get(buttons.size() - 1).addListener(new ClickListener() {
                @Override
                public void clicked(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y) {
                    buttonClick.play();
                    game.setScreen(new GameScreen(game, levels.get(levelName)));
                    System.out.println("Selected level: " + levelName);
                }
            });
        }
        for (TextButton button : buttons) {
            table.add(button).pad(10);
            table.row();
        }
    }

    private String getLevelName(String levelFile) {
        String[] words = levelFile.substring(4, levelFile.length() - 4).split("_");
        String levelName = "";
        for (String word : words) {
            levelName += word + " ";
        }
        return levelName;
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
        titleStyle.font.getData().setScale(1f);
        Label titleLabel = new Label("Select Level", titleStyle);
        titleLabel.setPosition(viewport.getWorldWidth() / 2 - titleLabel.getWidth() / 2,
                viewport.getWorldHeight() - (viewport.getWorldHeight() / 3.3f));
        stage.addActor(titleLabel);
    }

}
