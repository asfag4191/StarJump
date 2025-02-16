package inf112.skeleton.model.tiles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;

/**
 * The {@code TileLoader} class is responsible for managing and loading tile textures
 * using LibGDX's {@link AssetManager}.
 * 
 * This class ensures that the necessary tile assets are preloaded before use,
 * and provides a method to retrieve textures based on a given tile ID.
 * 
 */
public class TileLoader {
    private AssetManager manager;
    private int tileSize; 
    private TileMap tileMap;

    /**
     * Constructs a new {@code TileLoader} object. and loads the tile textures.
     * The textures are preloaded using LibGDX's {@link AssetManager}.
     */
    public TileLoader(TileMap tileMap) {
        if (tileMap == null) {
            System.out.println("Error: Passed TileMap is null!");
        } else {
            System.out.println("TileMap is successfully passed to TileLoader.");
        }
        this.tileMap = tileMap;
        manager = new AssetManager();
        manager.load("src/main/assets/brick.png", Texture.class);
        manager.finishLoading();
    }

    /**
     * Returns the texture of a tile based on the given tile ID.
     * 
     * @param tileID the ID of the tile
     * @return the texture of the tile
     */
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

        /**
     * Updates the tile size dynamically based on the screen size.
     */
    public void updateTileSize() {
        if (tileMap == null) {
            return;
        }

        int screenWidth = Gdx.graphics.getWidth();
        int screenHeight = Gdx.graphics.getHeight();

    
        // Use the tile map size to compute tile size dynamically
        this.tileSize = Math.min(screenWidth / tileMap.getCols(), screenHeight / tileMap.getRows());


    }
    
    public int getTileSize() {  // 🔹 Add this method
        return this.tileSize;
    }

    /**
    * Disposes of the asset manager and releases all loaded assets.     
    */
    public void dispose() {
        manager.dispose();
    }
}



    
