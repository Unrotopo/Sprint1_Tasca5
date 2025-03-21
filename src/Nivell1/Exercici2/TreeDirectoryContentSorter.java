package Nivell1.Exercici2;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class TreeDirectoryContentSorter {

    public void showMoreSortedFiles(Path path) {
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

                System.out.println("\nContents of: \\" + path.getFileName());
                for (Path child : children) {
                    BasicFileAttributes attr = Files.readAttributes(child, BasicFileAttributes.class);
                    if (Files.isDirectory(child)) {
                        System.out.println("\t" + child.getFileName() + " -- D");
                        System.out.println("\t\t[last modified: " + attr.lastModifiedTime() + "]");
                    } else {
                        System.out.println("\t" + child.getFileName() + " -- F");
                        System.out.println("\t\t[last modified: " + attr.lastModifiedTime() + "]");
                    }
                }
                for (Path child : children) {
                    showMoreSortedFiles(child);
                }
            }
        } catch(IOException e){
            System.out.println(e.getMessage());
        }
    }
}
