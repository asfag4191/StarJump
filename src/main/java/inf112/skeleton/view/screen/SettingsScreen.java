package inf112.skeleton.view.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import inf112.skeleton.model.StarJump;


/*
TODO:
 [] Get better skin for settingsscreen
 [] Update layout and add missing settings
    [] Screen resolution
    [] Player controlls
 [] Make the menu scale with resoultion
 [x] Allow for changing of settings without applying them permanently
 */

public class SettingsScreen implements Screen {
    private final StarJump game;
    private final Stage stage;
    private final Skin skin;
    private final Preferences prefs;
    private final Slider volumeSlider;
    private final CheckBox fullscreenCheck;

    public SettingsScreen(StarJump game) {
        this.game = game;
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        // Change this to a new skin, preferably not as bland as the default skin
        skin = new Skin(Gdx.files.internal("src/main/assets/skins/default/skin/uiskin.json"));

        // Load preferences
        prefs = Gdx.app.getPreferences("GameSettings");

        // UI ( will need to be refined later, also a better skin for the page would be prefered )
        Table table = new Table();
        table.setFillParent(true);
        stage.addActor(table);

        Label titleLabel = new Label("Settings", skin, "default"); // Use custom font
        titleLabel.setFontScale(3f);

        Label volumeLabel = new Label("Volume:", skin);
        volumeSlider = new Slider(0, 100, 1, false, skin);
        volumeSlider.setValue(prefs.getInteger("volume", 50));

        fullscreenCheck = new CheckBox("Fullscreen", skin);
        fullscreenCheck.setChecked(prefs.getBoolean("fullscreen", false));

        TextButton saveButton = new TextButton("Save", skin);
        TextButton resetButton = new TextButton("Reset to Default", skin);
        TextButton closeButton = new TextButton("Close", skin);

        // Button Listeners
        saveButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                saveSettings();
            }
        });

        resetButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                resetSettings();
            }
        });

        closeButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new MainMenuScreen(game));
            }
        });

        // Layout
        table.add(titleLabel).colspan(2).padBottom(20).row();
        table.add(volumeLabel).left().pad(10);
        table.add(volumeSlider).width(200).pad(10).row();
        table.add(fullscreenCheck).colspan(2).pad(10).row();
        table.add(saveButton).left().width(150).pad(10).padRight(0);
        table.add(resetButton).right().width(150).pad(10).padLeft(0).row();
        table.add(closeButton).center().colspan(2).width(300).pad(10).row();
    }

    /**
    Adjusts values in preferences
    Then calls for the game to update the settings
     */
    private void saveSettings() {
        prefs.putInteger("volume", (int) volumeSlider.getValue());
        prefs.putBoolean("fullscreen", fullscreenCheck.isChecked());
        prefs.flush();
        Gdx.app.getPreferences("GameSettings").putInteger("volume", (int) volumeSlider.getValue());
        Gdx.app.getPreferences("GameSettings").putBoolean("fullscreen", fullscreenCheck.isChecked());

        game.updateSettings();
        System.out.println("Settings saved!");
        game.setScreen(new SettingsScreen(game));
    }

    /**
    Sets settings back to default state as defined in this method
     */
    private void resetSettings() {
        prefs.clear();
        prefs.flush();
        volumeSlider.setValue(50);
        fullscreenCheck.setChecked(false);
        System.out.println("Settings reset to default!");
    }

    @Override
    public void show() { Gdx.input.setInputProcessor(stage); }

    @Override
    public void render(float delta) {
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void hide() { dispose(); }

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void dispose() {
        stage.dispose();
        skin.dispose();
    }
}
