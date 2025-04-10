package inf112.skeleton.view;

import java.util.HashMap;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

/**
 * A class for managing 2D animations using {@link Animation}.
 * Allows adding, playing, pausing, and stopping animations.
 * Keeps track of the current animation and updates frames over time.
 *
 * <p>Usage example:</p>
 * <pre>
 *     Animator animator = new Animator();
 *     animator.addAnimation("run", textureSheet, 4, 4, 10);
 *     animator.play("run");
 *     animator.update(deltaTime) --> next frame
 * </pre>
 */
public class Animator {
    private final HashMap<String, Animation<TextureRegion>> animations;
    private Animation<TextureRegion> currentAnimation;
    private String currentKey;
    private float currentTime;
    private boolean isPaused;

    /**
     * Constructs an {@code Animator} with an empty animation collection.
     */
    public Animator() {
     this.animations = new HashMap<>();
    }

    /**
     * Adds an animation with the specified key.
     * @param key  The identifier for the animation.
     * @param anim The {@link Animation} to add.
     */
    public void addAnimation(String key, Animation<TextureRegion> anim) {
        animations.put(key, anim);
    }

     /**
     * Creates and adds an animation from a texture sheet.
     * @param key            The identifier for the animation.
     * @param animationSheet The texture sheet containing animation frames.
     * @param rows           The number of rows in the sheet.
     * @param cols           The number of columns in the sheet.
     * @param fps            The frames per second for the animation.
     */
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

    /**
     * Creates and adds an animation from a texture atlas.
     * @param key        The identifier for the animation.
     * @param atlas      The {@link TextureAtlas} containing the animation regions.
     * @param regionName The name of the region in the atlas.
     * @param fps        The frames per second for the animation.
     */
    public void addAnimation(String key, TextureAtlas atlas, String regionName, float fps) {
        Array<TextureAtlas.AtlasRegion> frames = atlas.findRegions(regionName);
        Animation<TextureRegion> anim = new Animation<>(1/fps, frames);
        addAnimation(key, anim);
    }

    /**
     * Plays the animation associated with the given key.
     * If the animation is already playing, it does nothing.
     * @param key The key of the animation to play.
     * @throws IllegalArgumentException if the key is {@code null}.
     */
    public void play(String key) {
        if (key == null) throw new IllegalArgumentException("key must be none null");
        if (key.equals(currentKey)) return;
        if (!animations.containsKey(key)) return;

        currentAnimation = animations.get(key);
        currentKey = key;
        currentTime = 0;
        isPaused = false;
    }

    /**
     * Stops the currently playing animation.
     */
    public void stop() {
        currentKey = null;
    }

    /**
     * Pauses or resumes the currently playing animation.
     * @param on {@code true} to pause, {@code false} to resume.
     */
    public void pause(boolean on) {
        isPaused = on;
    }

    /**
     * Updates the animation frame based on the elapsed time.
     * @param dt The delta time between frames.
     * @return a {@link TextureRegion} representing the current frame of the animation.
     * if no animation is playing {@code null} is returned.
     */
    public TextureRegion update(float dt) {
        if (!isPaused) currentTime += dt;

        if (currentAnimation == null) {
            return null;
        }

        return currentAnimation.getKeyFrame(currentTime, true);
    }

    /**
     * Checks if an animation is currently playing.
     * An animation is considered playing even if it is paused.
     * Use {@link #isPaused()} to check whether the animation is paused.
     * @return {@code true} if an animation is playing, {@code false} otherwise.
     */
    public boolean isPlaying() {
        return currentKey != null;
    }

    /**
     * Checks if the animation is paused.
     * @return {@code true} if the animation is paused, {@code false} otherwise.
     */
    public boolean isPaused() {
        return isPaused;
    }


     /**
     * Clears all stored animations and resets the current animation.
     */
    public void clearAnimations() {
        animations.clear();
        currentAnimation = null;
        currentKey = null;
        currentTime = 0f;
        isPaused = false;
    }
}
