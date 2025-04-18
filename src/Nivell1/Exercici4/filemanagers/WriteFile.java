package Nivell1.Exercici4.filemanagers;

import java.io.*;
import java.nio.Buffer;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class WriteFile {

    private static final Scanner sc = new Scanner(System.in);

    public static void runWriter() throws IOException {
        Path startingDirectory = askDirectory("Give me a folder and I will tell you everything it contains:");
        Path destination = askDirectory("Where do you want to save the file?");
        System.out.println("What will be the name of the file? (make it end in .txt)");
        String fileName = sc.nextLine();
        sc.close();

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

    public static void processDirectory(Path father, List<String> contentToWrite) throws IOException {
        if (!Files.exists(father)) {
            System.out.println("So, something happened. This folder does not exist. Bye");
            return;
        }
        try {
            List<Path> children = getSortedDirectoryContents(father);
            appendDirectoryHeader(father, contentToWrite);
            appendDirectoryContent(children, contentToWrite);
            processSubdirectories(children, contentToWrite);
        } catch (IOException e) {
            System.out.println("Error processing directory: " + e.getMessage());
        }
    }

    public static void appendDirectoryHeader (Path father, List<String> contentToWrite) {
        contentToWrite.add("\nContents of " + father.getFileName() + ":\n");
    }

    public static void appendDirectoryContent (List<Path> children, List<String> contentToWrite) throws IOException {
        for (Path child : children) {
            BasicFileAttributes attr = Files.readAttributes(child, BasicFileAttributes.class);
            String childType = Files.isDirectory(child) ? " -- D" : " -- F";
            contentToWrite.add("\t" + child.getFileName() + childType + "\n");
            contentToWrite.add("\t\t[last modified: " + attr.lastModifiedTime() + "]\n");
        }
    }

    public static void processSubdirectories (List<Path> children, List<String> contentToWrite) throws IOException {
        for (Path child : children) {
            if (Files.isDirectory(child)) {
                processDirectory(child, contentToWrite);
            }
        }
    }

    public static List<Path> getSortedDirectoryContents (Path path) {
        List<Path> children = new ArrayList<>();
        try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(path)) {
            for (Path child : directoryStream) {
                children.add(child);
            }
            Collections.sort(children);
        } catch (IOException e){
            System.out.println(e.getMessage());
        }
        return children;
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
