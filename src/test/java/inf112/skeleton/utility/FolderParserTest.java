package inf112.skeleton.utility;

import org.junit.jupiter.api.*;


import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class FolderParserTest {
    private Path tempDir;

    @BeforeEach
    void setUp() throws IOException {
        // Create a temporary directory
        tempDir = Files.createTempDirectory("testFolder");

        // Create test files
        Files.createFile(tempDir.resolve("file1.txt"));
        Files.createFile(tempDir.resolve("file2.doc"));
        Files.createFile(tempDir.resolve("file3.tmx"));
        Files.createFile(tempDir.resolve("file4.tmx"));
        Files.createDirectory(tempDir.resolve("subfolder"));
    }

    @AfterEach
    void tearDown() throws IOException {
        // Delete all files and the directory
        Files.walk(tempDir)
                .map(Path::toFile)
                .forEach(File::delete);
    }

    @Test
    void testGetFilesInFolder() {
        ArrayList<String> files = FolderParser.getFilesInFolder(tempDir.toString());

        // Ensure the correct number of files are returned
        assertEquals(4, files.size());

        // Check if specific files are in the list
        List<String> expectedFiles = List.of("file1.txt", "file2.doc", "file3.tmx", "file4.tmx");
        assertTrue(files.containsAll(expectedFiles));
    }

    @Test
    void testGetTMXFilesInFolder() {
        ArrayList<String> tmxFiles = FolderParser.getTMXFilesInFolder(tempDir.toString());

        // Ensure only TMX files are returned
        assertEquals(2, tmxFiles.size());
        assertTrue(tmxFiles.contains("file3.tmx"));
        assertTrue(tmxFiles.contains("file4.tmx"));
    }
}
