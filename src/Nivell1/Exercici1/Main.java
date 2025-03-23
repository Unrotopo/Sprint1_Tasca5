package Nivell1.Exercici1;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        String filePath = System.getProperty("user.dir") + File.separator +
                "src" + File.separator +
                "Nivell1" + File.separator +
                "Exercici1" + File.separator +
                "resources";

        DirectoryContentSorter.showSortedFiles(filePath);
    }
}
