package inf112.skeleton.utility;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

public class SoundManager {

    public final Sound powerup_fly;   // "woo!" when collecting a powerup


    public SoundManager() {
        powerup_fly = Gdx.audio.newSound(Gdx.files.internal("audio/sounds/PowerUp.ogg")); 

    }

    public void playPowerup() {
        powerup_fly.play(1.0f);
    }



    public void dispose() {
        powerup_fly.dispose();

  
    }
}