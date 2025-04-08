package inf112.skeleton.utility;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

/**
 * Manages sound effects used in the game.
 */
public class SoundManager {

    /**
     * The sound played when the player collects a power-up and starts flying.
     */
    public final Sound powerup_fly;  


     /**
     * Constructs the {@code SoundManager} and loads sound assets.
     * 
     * All sounds are loaded using LibGDX's internal file handler.
     */
    public SoundManager() {
        powerup_fly = Gdx.audio.newSound(Gdx.files.internal("audio/sounds/PowerUp.ogg")); 

    }

    /**
     * Plays the power-up collection sound.
     * Typically triggered when the player collects a flying power-up.
     */
    public void playPowerup() {
        powerup_fly.play(1.0f);
    }


    /**
     * Disposes all loaded sound resources.
     *
     * Should be called when the game shuts down or the screen is disposed.
     */
    public void dispose() {
        powerup_fly.dispose();

  
    }
}