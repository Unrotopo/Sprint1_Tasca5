package Nivell1.Exercici2;

import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {

        String directoryPath = ".\\src";
        TreeDirectoryContentSorter t = new TreeDirectoryContentSorter();
        t.showMoreSortedFiles(Paths.get(directoryPath));
    }
}
