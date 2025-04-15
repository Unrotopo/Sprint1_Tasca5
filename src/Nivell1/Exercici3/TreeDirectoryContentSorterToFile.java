package Nivell1.Exercici3;

import java.io.*;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class TreeDirectoryContentSorterToFile {

    private final Path destinationPath;
    private final String fileName;

    public TreeDirectoryContentSorterToFile(String destinationPath, String fileName) {
        this.destinationPath = Paths.get(destinationPath);
        this.fileName = fileName;
    }

    public void writeMoreSortedFiles(Path path) {
        if (!Files.isDirectory(path)) return;

        try {
            if (Files.isDirectory(path)) {

                List<Path> children = getSubdirectories(path);
                if (children.isEmpty()) return;
                Collections.sort(children);

                writeToFile(path, children);
                iterateThroughChildren(children);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Path> getSubdirectories(Path path) throws IOException {
        List<Path> children = new ArrayList<>();

        try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(path)) {
            for (Path child : directoryStream) {
                children.add(child);
            }
        }
        return children;
    }

    public void iterateThroughChildren(List<Path> children) {
        for (Path child : children) {
            if (Files.isDirectory(child)) {
                writeMoreSortedFiles(child);
            }
        }
    }

    public void writeToFile(Path path, List<Path> children) throws IOException {

        createDirectory(destinationPath);
        Path outputFile = destinationPath.resolve(fileName);
        createFile(outputFile);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(destinationPath + fileName, true))) {
            writer.write("\nContents of: " + path.getFileName() + "\n");

            for (Path child : children) {
                BasicFileAttributes attr = Files.readAttributes(child, BasicFileAttributes.class);
                String childType = Files.isDirectory(child) ? " -- D" : " -- F";
                writer.write("\t" + child.getFileName() + childType + "\n");
                writer.write("\t\t[last modified: " + attr.lastModifiedTime() + "]\n");
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void createDirectory(Path outputDir) throws IOException {
        if (!Files.exists(outputDir)) {
            Files.createDirectories(outputDir);
            System.out.println("Directory created: " + outputDir);
        }
    }

    public void createFile(Path outputFile) throws IOException {
        if (!Files.exists(outputFile)) {
            Files.createFile(outputFile);
            System.out.println("File created: " + outputFile);
        }
    }
}
