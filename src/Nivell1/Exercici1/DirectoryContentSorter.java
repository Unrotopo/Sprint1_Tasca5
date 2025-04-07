package Nivell1.Exercici1;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class DirectoryContentSorter {

    public static void showSortedFiles(String path) {

        try {
            File file = new File(path);

            if (!file.exists()) {
                throw new IOException("The specified path does not exist.");
            }

            String[] list = file.list();
            if (list == null) {
                throw new ArrayIndexOutOfBoundsException("No files found in the directory.");
            }

            Arrays.sort(list);
            for (String s : list) {
                System.out.println(s);
            }

        } catch (IOException | ArrayIndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
        }
    }
}


