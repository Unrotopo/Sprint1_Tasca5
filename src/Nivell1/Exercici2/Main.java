package Nivell1.Exercici2;

import java.io.File;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {


        String directoryPath = System.getProperty("user.dir");
        TreeDirectoryContentSorter t = new TreeDirectoryContentSorter();
        t.showMoreSortedFiles(Paths.get(directoryPath));
    }
}
