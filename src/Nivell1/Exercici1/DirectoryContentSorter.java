package Nivell1.Exercici1;

import java.io.File;
import java.util.Arrays;

public class DirectoryContentSorter {

    public static void showSortedFiles(String path) {
        File file = new File(path);
        String[] list = file.list();

        if (list != null) {
            Arrays.sort(list);
            for (String s : list) {
                System.out.println(s);
            }
        }
    }
}

