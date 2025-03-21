package Nivell1.Exercici3;

import java.io.*;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class TreeDirectoryContentSorterToFile {

    String destinationPath = System.getProperty("user.dir") + File.separator +
            "src" + File.separator +
            "Nivell1" + File.separator +
            "Exercici3" + File.separator +
            "resources" + File.separator;

    public void writeMoreSortedFiles(Path path) {
        try {
            if (Files.isDirectory(path)) {
                List<Path> children = new ArrayList<>();

                try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(path)) {
                    for (Path child : directoryStream) {
                        children.add(child);
                    }
                }

                if (children.isEmpty()) {
                    return;
                }

                Collections.sort(children);

                try (BufferedWriter writer = new BufferedWriter(new FileWriter(destinationPath + "sorted.txt", true))) {
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
                    System.out.println(e.getMessage());
                }
                for (Path child : children) {
                    writeMoreSortedFiles(child);
                }
            }
        }catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
