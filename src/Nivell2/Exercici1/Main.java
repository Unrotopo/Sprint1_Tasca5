package Nivell2.Exercici1;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {

        try {
            ConfigLoader configLoader = new ConfigLoader();
            configLoader.loadConfig("config.properties");

            DirectoryContentSorterToFile sorter = new DirectoryContentSorterToFile(configLoader);
            sorter.runSorter();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
