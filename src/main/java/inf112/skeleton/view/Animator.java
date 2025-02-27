package inf112.skeleton.view;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

import java.util.HashMap;

public class Animator {
    private final HashMap<String, Animation<TextureRegion>> animations;
    private Animation<TextureRegion> currentAnimation;
    private String currentKey;
    private float currentTime;
    private boolean isPaused;

    public Animator() {
     this.animations = new HashMap<>();
    }

    public void addAnimation(String key, Animation<TextureRegion> anim) {
        animations.put(key, anim);
    }

    public void addAnimation(String key, Texture animationSheet, int rows, int cols, float fps) {
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
        addAnimation(key, anim);
    }

    public void addAnimation(String key, TextureAtlas atlas, String regionName, float fps) {
        Array<TextureAtlas.AtlasRegion> frames = atlas.findRegions(regionName);
        Animation<TextureRegion> anim = new Animation<>(1/fps, frames);
        addAnimation(key, anim);
    }

    public void play(String key) {
        if (key == null) throw new IllegalArgumentException("key must be none null");
        if (key.equals(currentKey)) return;

        currentAnimation = animations.get(key);
        currentKey = key;
        currentTime = 0;
        isPaused = false;
    }

    public void stop() {
        currentKey = null;
    }

    public void pause() {
        isPaused = true;
    }

    public TextureRegion update(float dt) {
        if (!isPaused) currentTime += dt;
        return currentAnimation.getKeyFrame(currentTime, true);
    }

    public boolean isPlaying() {
        return currentKey != null;
    }

    public boolean isPaused() {
        return isPaused;
    }
}
