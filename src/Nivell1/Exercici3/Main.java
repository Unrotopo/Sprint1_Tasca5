package Nivell1.Exercici3;

import java.io.File;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {

        String directoryPath = System.getProperty("user.dir");
        String destinationPath = System.getProperty("user.dir") + File.separator +
                "Nivell1" + File.separator +
                "Exercici3" + File.separator +
                "resources" + File.separator;
        String fileName = "sorted.txt";

        TreeDirectoryContentSorterToFile sorterToFile = new TreeDirectoryContentSorterToFile(destinationPath, fileName);
        sorterToFile.writeMoreSortedFiles(Paths.get(directoryPath));
    }
}
