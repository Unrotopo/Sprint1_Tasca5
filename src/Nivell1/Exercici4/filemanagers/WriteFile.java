package Nivell1.Exercici4.filemanagers;

import java.io.*;
import java.nio.Buffer;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class WriteFile {

    private static final Scanner sc = new Scanner(System.in);

    public static void runWriter() {
        Path startingDirectory = askDirectory("Give me a folder and I will tell you everything it contains:");
        Path destination = askDirectory("Where do you want to save the file?");
        System.out.println("What will be the name of the file? (make it end in .txt)");
        String fileName = sc.nextLine();

        List<String> contentToWrite = new ArrayList<>();
        processDirectory(startingDirectory, contentToWrite);
        writeFile(destination, fileName, contentToWrite);
    }

    public static Path askDirectory(String message) {
        System.out.println(message);
        Path directory = Paths.get(System.getProperty("user.dir"), sc.nextLine());

        if (!Files.exists(directory)) {
            System.out.println("Directory " + directory + " does not exist. Let me help you.");
            try {
                Files.createDirectories(directory);
                System.out.println("There, directory created.");
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
        return directory;
    }

    public static void processDirectory(Path father, List<String> contentToWrite) {
        if (!Files.exists(father)) {
            System.out.println("So, something happened. This folder does not exist. Bye");
            return;
        }

        List<Path> children = new ArrayList<>();
        try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(father)) {
            for (Path child : directoryStream) {
                children.add(child);
            }
            Collections.sort(children);

            contentToWrite.add("\nContents of " + father.getFileName() + ":\n");
            for (Path child : children) {
                BasicFileAttributes attr = Files.readAttributes(child, BasicFileAttributes.class);
                String childType = Files.isDirectory(child) ? " -- D" : " -- F";
                contentToWrite.add("\t" + child.getFileName() + childType + "\n");
                contentToWrite.add("\t\t[last modified: " + attr.lastModifiedTime() + "]\n");
            }

            for (Path child : children) {
                if (Files.isDirectory(child)) {
                    processDirectory(child, contentToWrite);
                }
            }


        } catch(IOException e){
            System.out.println(e.getMessage());
        }
    }

    public static void writeFile(Path destination, String fileName, List<String> content) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(destination + File.separator + fileName))) {
            for (String line : content) {
                writer.write(line);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
