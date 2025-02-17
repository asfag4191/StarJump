package inf112.skeleton.Tile;


import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

import inf112.skeleton.model.tiles.TileMap;

public class TileMapTest {
    private TileMap tileMap;

    /**
     * Mock LibGDX file handling before running tests.
     */
    @BeforeAll
    static void setUpBeforeAll() {
        Gdx.files = Mockito.mock(com.badlogic.gdx.Files.class);
    }

    /**
     * Setup a mock file before each test.
     */
    @BeforeEach
    void setUp() {
        String fakeMap = "1 0 1\n0 1 0";
        FileHandle mockFileHandle = Mockito.mock(FileHandle.class);
        
        Mockito.when(mockFileHandle.readString()).thenReturn(fakeMap);
        Mockito.when(Gdx.files.internal("test_map.txt")).thenReturn(mockFileHandle);
        tileMap = new TileMap("test_map.txt");
    }
    

    /**
     * Tests if the tile map loads the correct number of rows.
     */
    @Test
    void testTileMapRows() {
        assertEquals(2, tileMap.getRows(), "Tile map should have 2 rows.");
    }

    /**
     * Tests if the tile map loads the correct number of columns.
     */
    @Test
    void testTileMapColumns() {
        assertEquals(3, tileMap.getCols(), "Tile map should have 3 columns.");
    }

    /**
     * Tests if the tile map loads the correct tile values.
     */
    @Test
    void testTileMapData() {
        int[][] expected = {
            {1, 0, 1},
            {0, 1, 0}
        };
        assertArrayEquals(expected, tileMap.getMapData(), "Tile map data should match expected values.");
    }
}
