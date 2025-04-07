package Nivell3.Exercici1;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class ConfigLoader {

    private final Properties properties = new Properties();

    public static ConfigLoader getConfigLoader() throws IOException {

        Path configPath = Paths.get(System.getProperty("user.dir"), "Nivell3", "Exercici1", "resources", "config.properties");

        if (!Files.exists(configPath)) {
            System.out.println("Configuration file not found: " + configPath);
        }

        try {
            ConfigLoader configLoader = new ConfigLoader();
            configLoader.loadConfig(configPath.toString());

            String inputDir = configLoader.getProperty("inputDirectoryCrypt");
            if (inputDir == null || inputDir.isBlank()) {
                System.out.println("Missing 'input directory' in config file.");
            }

            String outputDir = configLoader.getProperty("outputDirectoryCrypt");
            if (outputDir == null || outputDir.isBlank()) {
                System.out.println("Missing 'output directory' in config file.");
            }

            String inFileName = configLoader.getProperty("inFileNameCrypt");
            if (inFileName == null || inFileName.isBlank()) {
                System.out.println("Missing 'input file name' in config file.");
            }

            String outFileName = configLoader.getProperty("outFileNameCrypt");
            if (outFileName == null || outFileName.isBlank()) {
                System.out.println("Missing 'output file name' in config file.");
            }

            return configLoader;

        } catch (IOException e) {
            System.out.println("Error loading configuration file: " + e.getMessage());
            throw e;
        }
    }

    public void loadConfig(String filePath) throws IOException {
        try (FileInputStream fis = new FileInputStream(filePath)) {
            properties.load(fis);
        }
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }
}
