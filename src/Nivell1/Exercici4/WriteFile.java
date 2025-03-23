package Nivell1.Exercici4;

import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class WriteFile {

    private static final Scanner sc = new Scanner(System.in);
    private static Path destination = null;

    public static void runWriter() {
        getCurrentAndChildren(askStartingDirectory());
    }

    public static Path askStartingDirectory() {
        System.out.println("\nYour current directory is: " + System.getProperty("user.dir") + "\n");
        System.out.println("Give me a folder and I will tell you everything it contains:");

        String givenDirectory = sc.nextLine();
        Path startingDirectory = Paths.get(System.getProperty("user.dir") + givenDirectory);

        if (!Files.exists(startingDirectory)) {
            System.out.println("Directory " + startingDirectory + " does not exist.");
        }

        return startingDirectory;
    }

    public static Path askDestination() {
        if (destination == null) {
            System.out.println("Where do you want to save the file?:");
            destination = Paths.get(System.getProperty("user.dir") + sc.nextLine());
            try {
                if (!Files.exists(destination)) {
                    Files.createDirectories(destination);
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
        return destination;
    }

    public static void getCurrentAndChildren(Path father) {
        try {
            if (Files.isDirectory(father)) {
                List<Path> children = new ArrayList<>();

                try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(father)) {
                    for (Path child : directoryStream) {
                        children.add(child);
                    }
                }

                if (children.isEmpty()) {
                    return;
                }

                Collections.sort(children);

                writeCurrentDirectoryAndChildren(children, father);

                for (Path child : children) {
                    getCurrentAndChildren(child);
                }
            }
        } catch(IOException e){
            System.out.println(e.getMessage());
        }
    }

    public static void writeCurrentDirectoryAndChildren(List<Path> children, Path father) {
        Path destination = askDestination();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(destination + File.separator +  "DirectoryTree.txt", true))) {
            writer.write("\nContents of: " + father.getFileName() + "\n");

            for (Path child : children) {
                BasicFileAttributes attr = Files.readAttributes(child, BasicFileAttributes.class);
                String childType = Files.isDirectory(child) ? "D" : "F";
                writer.write("\t" + child.getFileName() + childType + "\n");
                writer.write("\t\t[last modified: " + attr.lastModifiedTime() + "]\n");
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
