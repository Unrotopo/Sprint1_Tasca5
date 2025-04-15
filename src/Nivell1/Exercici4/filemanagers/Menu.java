package Nivell1.Exercici4.filemanagers;

import java.io.IOException;
import java.util.Scanner;

public class Menu {

    public static void menu() throws IOException {
        Scanner sc = new Scanner(System.in);

        System.out.println("Do you want to get the contents of a given directory into a .txt file? (Y/N)");
        if (sc.next().equalsIgnoreCase("y")) {
            WriteFile.runWriter();
        }

        System.out.println("Do you want to read the contents of a given file? (Y/N)");
        if (sc.next().equalsIgnoreCase("y")) {
            ReadFile.runFileReader();
        }
    }
}
