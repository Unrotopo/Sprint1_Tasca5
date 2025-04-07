package Nivell2.Exercici1;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        try {
            ConfigLoader configLoader = ConfigLoader.getConfigLoader();

            DirectoryContentSorterToFile sorter = new DirectoryContentSorterToFile(configLoader);
            sorter.runSorter();

            System.out.println("Sorting completed successfully.");

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
