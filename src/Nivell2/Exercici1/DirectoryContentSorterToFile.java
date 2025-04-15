package Nivell2.Exercici1;

import java.io.*;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class DirectoryContentSorterToFile {

    private final String inputDirectory;
    private final String outputDirectory;
    private final String fileName;

    public DirectoryContentSorterToFile(ConfigLoader config) {
        this.inputDirectory = config.getProperty("inputDirectory");
        this.outputDirectory = config.getProperty("outputDirectory");
        this.fileName = config.getProperty("fileName");
    }

    public void runSorter() {
        writeMoreSortedFiles(Paths.get(inputDirectory));
    }

    public void writeMoreSortedFiles(Path path) {
        if (!Files.isDirectory(path)) return;

        try {
            List<Path> children = getChildren(path);
            if (children.isEmpty()) return;
            Collections.sort(children);

            Path outputPath = getOutputPath();
            writeToFile(path, children, outputPath);
            iterateThroughChildren(children);

        } catch (IOException e) {
            System.out.println("Error going over directories: " + e.getMessage());
        }
    }

    public List<Path> getChildren(Path path) {
        List<Path> children = new ArrayList<>();
        try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(path)) {
            for (Path child : directoryStream) {
                children.add(child);
            }
        } catch (IOException e) {
            System.out.println("Error reading directory: " + e.getMessage());
        }
        return children;
    }

    public Path getOutputPath() throws IOException {
        Path outputPath = Paths.get(outputDirectory, fileName);
        Files.createDirectories(Paths.get(outputDirectory));
        if (Files.notExists(outputPath)) {
            Files.createFile(outputPath);
        }
        return outputPath;
    }

    public void iterateThroughChildren(List<Path> children) {
        for (Path child : children) {
            if (Files.isDirectory(child)) {
                writeMoreSortedFiles(child);
            }
        }
    }

    public void writeToFile(Path path, List<Path> children, Path outputPath) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputPath.toString(), true))) {
            writer.write("\nContents of: " + path.getFileName() + "\n");

            for (Path child : children) {
                BasicFileAttributes attr = Files.readAttributes(child, BasicFileAttributes.class);
                if (Files.isDirectory(child)) {
                    writer.write("\t" + child.getFileName() + " -- D\n");
                    writer.write("\t\t[last modified: " + attr.lastModifiedTime() + "]\n");
                } else {
                    writer.write("\t" + child.getFileName() + " -- F\n");
                    writer.write("\t\t[last modified: " + attr.lastModifiedTime() + "]\n");
                }
            }
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }
}