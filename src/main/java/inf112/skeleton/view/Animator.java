package inf112.skeleton.view;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.HashMap;

public class Animator {
    private HashMap<String, Animation<TextureRegion>> animations;
    private float currentTime;
    private boolean isPlaying;
    private boolean isPaused;

    public void createAnimation(String key, Texture animationSheet, int rows, int cols, float fps) {
        TextureRegion[][] temp = TextureRegion.split(animationSheet,
                animationSheet.getWidth()/cols,
                animationSheet.getHeight()/rows);

        TextureRegion[] animFrames = new TextureRegion[rows * cols];
        for (int i = 0, index = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++, index++) {
                animFrames[index] =  temp[i][j];
            }
        }

        Animation<TextureRegion> anim = new Animation<>(1/fps, animFrames);
        animations.put(key, anim);
    }

    public TextureRegion updateAnimation(String key, float dt) {
        if (!isPlaying) {
            currentTime = 0;
            isPlaying = true;
            isPaused = false;
        }

        if (!isPaused) currentTime += dt;

        return animations.get(key).getKeyFrame(currentTime, true);
    }

    public void pause() {
        isPaused = true;
    }

    public boolean isPlaying() {
        return isPlaying;
    }




}
