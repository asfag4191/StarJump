package inf112.skeleton.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

/*
TODO:
 [] Make changing the volume actually change volume
 [] Add the rest of the settings
 */

public class Settings {
    Preferences prefs;

    /**
     * Simple class for getting and setting new game settings
     */
    public Settings() {
        this.prefs = Gdx.app.getPreferences("GameSettings");
    }

    public void setSetting(String key, String value) {
        if (prefs.contains(key)) {
            prefs.putString(key, value);
            System.out.println("Setting '" + key + "' to '" + value + "'");
        } else {
            System.out.println("Setting '" + key + " does not exist");
            prefs.putString(key, value);
            System.out.println("Setting '" + key + "' to '" + value + "'");
        }
    }

    public String getSetting(String key) {
        return prefs.getString(key, null);
    }

    /**
     * Updates screen based on settings
     */
    public void applySettings() {
        prefs.flush();

        if (prefs.getBoolean("fullscreen", false)) {
            Gdx.graphics.setFullscreenMode(Gdx.graphics.getDisplayMode());
        } else {
            // TODO: Make it possible to change resolution from dropdown in SettingsScreen
            Gdx.graphics.setWindowedMode(prefs.getInteger("windowWidth", 1280), prefs.getInteger("windowheight", 720));
        }

        // debug for settings
        for (String key : prefs.get().keySet()) {
            System.out.println("Setting '" + key + "' -> " + prefs.getString(key, null));
        }
    }
}
