package inf112.skeleton.model.tiles;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;

public class TileLoader {
    private AssetManager manager; 

    public TileLoader() {
        manager = new AssetManager();
        
        // Correct path (files are inside "assets/" but you only use the name)
        manager.load("src/main/assets/brick.png", Texture.class);
        
        manager.finishLoading(); // Ensure all assets are loaded before using them
    }

    public Texture getTileTexture(int tileID) {
        if (!manager.isLoaded("src/main/assets/brick.png")) {
            System.out.println("Error: Assets not loaded!");
            return null;
        }

        if (tileID == 1) {
            return manager.get("src/main/assets/brick.png", Texture.class);
        } else if (tileID == 0) {
            return null; // Transparent, no texture drawn
        }

        return null; // Handle unknown tileID
    }

    public void dispose() {
        manager.dispose();
    }
}



    
