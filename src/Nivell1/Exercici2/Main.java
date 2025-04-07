package Nivell1.Exercici2;

import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {

        String directoryPath = System.getProperty("user.dir");
        TreeDirectoryContentSorter treeDirectoryContentSorter = new TreeDirectoryContentSorter();
        treeDirectoryContentSorter.showMoreSortedFiles(Paths.get(directoryPath));
    }
}
