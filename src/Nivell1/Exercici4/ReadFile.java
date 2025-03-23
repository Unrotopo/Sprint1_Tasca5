package Nivell1.Exercici4;

import java.io.*;
import java.nio.file.*;
import java.util.Scanner;

public class ReadFile {

    private static final Scanner sc = new Scanner(System.in);

    public static void runFileReader() {
        Path filePath = askFileToRead();
        if (Files.exists(filePath)) {
            readFromFile(filePath);
        } else {
            System.out.println("File not found");
        }
    }

    public static Path askFileToRead() {
        System.out.println("\nYour current path is: " + System.getProperty("user.dir"));
        System.out.println("Please enter the file you want to read: ");
        String fileName = sc.nextLine();
        Path filePath = Paths.get(System.getProperty("user.dir"), fileName);

        if (!Files.exists(filePath)) {
            System.out.println("File not found");
            return null;
        }
        return filePath;
    }

    public static boolean isTextFile(Path path) {
        return path.toString().endsWith(".txt");
    }

    public static void readFromFile(Path path) {
        System.out.println("");
        if (isTextFile(path)) {
            try (BufferedReader reader = new BufferedReader(new FileReader(path.toString()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
