package Nivell1.Exercici1;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        String filePath = ".\\src\\Nivell1\\Exercici1\\resources";

        DirectoryContentSorter fileContentSorter = new DirectoryContentSorter();
        fileContentSorter.showSortedFiles(filePath);
    }
}
