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
     * 
     * @param tileMap The {@code TileMap} instance containing the tile data.
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
     * Retrieves the texture of a tile based on the given tile ID.
     *      
     * @param tileID the ID of the tile
     * @return the texture of the tile, otherwise {@code null}.
     */
    public Texture getTileTexture(int tileID) {
        if (!manager.isLoaded("src/main/assets/brick.png")) {
            return null;
        }

        if (tileID == 1) {
            return manager.get("src/main/assets/brick.png", Texture.class);
        } else if (tileID == 0) {
            return null; 
        }

        return null; 
    }

    
    /**
     * Gets the current tile size.
     * 
     * @return The size of a single tile in pixels.
     */
    public int getTileSize() {  
        return this.tileSize;
    }

    /**
    * Disposes of the asset manager and releases all loaded assets.
    */
    public void dispose() {
        if (manager != null) {
            manager.dispose();
        }
    }
}



    
