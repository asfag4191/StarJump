package inf112.skeleton.model.character;

import com.badlogic.gdx.ApplicationListener;

public record BlankApplication() implements ApplicationListener {
    @Override
    public void create() {
        // Intet oppsett nødvendig her
    }

    @Override
    public void resize(int width, int height) {
        // Ingen handling på størrelsesendring
    }

    @Override
    public void render() {
        // Ingen rendering nødvendig
    }

    @Override
    public void pause() {
        // Ingen pausehandling
    }

    @Override
    public void resume() {
        // Ingen fortsettelsehandling
    }

    @Override
    public void dispose() {
        // Ingen ryddehandling
    }
}
