package Nivell1.Exercici2;

import java.io.File;
import java.io.IOException;
import java.nio.*;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


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
                    if (Files.isDirectory(child)) {
                        System.out.println("\t" + child.getFileName() + " -- D");
                    } else {
                        System.out.println("\t" + child.getFileName() + " -- F");
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
