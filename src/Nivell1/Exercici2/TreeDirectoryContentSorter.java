package Nivell1.Exercici2;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class TreeDirectoryContentSorter {

    public void showMoreSortedFiles(Path path) {
        if (!Files.isDirectory(path)) {
            return;
        }

        try {
            List<Path> children = getDirectoryChildren(path);
            if (children.isEmpty()) {
                return;
            }
            processChildren(children, path);

        } catch(IOException e){
            System.out.println(e.getMessage());
        }
    }

    public List<Path> getDirectoryChildren(Path path) throws IOException {
        List<Path> children = new ArrayList<>();

        try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(path)) {
            for (Path child : directoryStream) {
                children.add(child);
            }
        }
        return children;
    }

    public void printFiles(List<Path> children, Path path) throws IOException {

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
    }

    public void processChildren(List<Path> children, Path path) throws IOException {
        Collections.sort(children);
        printFiles(children, path);

        for (Path child : children) {
            if (Files.isDirectory(child) && !Files.isSymbolicLink(child)) {
                showMoreSortedFiles(child);
            }
        }
    }
}
