package Nivell1.Exercici4.filemanagers;

import java.io.*;
import java.nio.file.*;
import java.util.Scanner;

public class ReadFile {

    private static final Scanner sc = new Scanner(System.in);

    public static void runFileReader() {
        Path filePath = askFileToRead();
        readFromFile(filePath);
    }

    public static Path askFileToRead() {

        Path filePath;
        while (true) {
            System.out.println("Please enter the file you want to read: ");
            String fileName = sc.nextLine();

            if (!fileName.endsWith(".txt")) {
                System.out.println("Please make sure the file ends in .txt");
                continue;
            }

            filePath = Paths.get(System.getProperty("user.dir"), fileName);

            if (Files.exists(filePath)) {
                break;
            }
            System.out.println("I cannot seem to find that file");
        }
        sc.close();
        return filePath;
    }

    public static void readFromFile(Path path) {
        System.out.println("");
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
