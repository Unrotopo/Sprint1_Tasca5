package Nivell1.Exercici3;

import java.io.File;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {

        String directoryPath = System.getProperty("user.dir") + File.separator + "src";

        TreeDirectoryContentSorterToFile sorterToFile = new TreeDirectoryContentSorterToFile();
        sorterToFile.writeMoreSortedFiles(Paths.get(directoryPath));
    }
}
