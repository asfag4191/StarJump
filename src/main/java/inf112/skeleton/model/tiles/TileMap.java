package inf112.skeleton.model.tiles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

public class TileMap {
    private int[][] mapData;
    private int rows, cols;

    public TileMap(String filePath) {
        loadMap(filePath);
    }

    private void loadMap(String filePath) {
        FileHandle file = Gdx.files.internal(filePath); 
        String[] lines = file.readString().split("\n"); 

        rows = lines.length;
        cols = lines[0].split(" ").length;

        mapData = new int[rows][cols];

        for (int row = 0; row < rows; row++) {
            char[] values = lines[row].toCharArray(); 
            for (int col = 0; col < cols; col++) {
                mapData[row][col] = Character.getNumericValue(values[col]); 
            }
        }
    }

    public int[][] getMapData() {
        return mapData;
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }
}


