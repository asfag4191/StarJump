package inf112.skeleton.utility;

import java.io.File;
import java.util.ArrayList;

public class FolderParser {

    public static ArrayList<String> getFilesInFolder(String folderPath) {
        File folder = new File(folderPath);
        File[] listOfFiles = folder.listFiles();
        ArrayList<String> fileNames = new ArrayList<>();
        for (File file : listOfFiles) {
            if (file.isFile()) {
                fileNames.add(file.getName());
            }
        }
        return fileNames;
    }

    public static ArrayList<String> getTMXFilesInFolder(String folderPath) {
        ArrayList<String> fileNames = getFilesInFolder(folderPath);
        ArrayList<String> tmxFiles = new ArrayList<>();
        for (String fileName : fileNames) {
            if (fileName.endsWith(".tmx")) {
                tmxFiles.add(fileName);
            }
        }
        return tmxFiles;
    }
}
