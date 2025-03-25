package Nivell1.Exercici1;

import java.io.File;

public class Main {
    public static void main(String[] args) {

        String filePath = System.getProperty("user.dir") + File.separator +
                "src" + File.separator +
                "Nivell1" + File.separator +
                "Exercici1" + File.separator +
                "resources";

        DirectoryContentSorter.showSortedFiles(filePath);
    }
}
