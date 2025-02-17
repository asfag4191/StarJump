package inf112.skeleton.model.tiles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

/**
 * The {@code TileMap} class is responsible for loading and storing tile map data.
 * 
 * This class reads a text file containing tile data, and stores it in a 2D array.
 * Each tile in the map is represented by an integer, where different numbers
 * correspond to different tile types (e.g., 0 = empty, 1 = brick).
 */
public class TileMap {
    private int[][] mapData;
    private int rows, cols;


    /**
     * Constructs a new {@code TileMap} object.
     * 
     * @param filePath the path to the tile map file
     */
    public TileMap(String filePath) {
        loadMap(filePath);
        System.out.println("Loaded TileMap with " + rows + " rows and " + cols + " columns.");
    }

    /**
     * Loads the tile map data from the given file path.
     * 
     * @param filePath the path to the tile map file
     */
    private void loadMap(String filePath) {
        FileHandle file = Gdx.files.internal(filePath); 
        String[] lines = file.readString().split("\n"); 
    
        rows = lines.length;
        cols = lines[0].split(" ").length; 
    
        mapData = new int[rows][cols];
        
    
        for (int row = 0; row < rows; row++) {
            String[] values = lines[row].split(" "); 
            for (int col = 0; col < cols; col++) {
                mapData[row][col] = Integer.parseInt(values[col]); 
            }
        }
    }

    /**
     * Returns the tile map data as a 2D array.
     * 
     * @return the tile map data
     */
    public int[][] getMapData() {
        return mapData;
    }

    /**
     * Returns the number of rows in the tile map.
     * 
     * @return the number of rows
     */
    public int getRows() {
        return rows;
    }

    /**
     * Returns the number of columns in the tile map.
     * 
     * @return the number of columns
     */
    public int getCols() {
        return cols;
    }
}


