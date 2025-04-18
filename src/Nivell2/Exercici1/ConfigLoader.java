package Nivell2.Exercici1;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class ConfigLoader {

    private final Properties properties = new Properties();

    public static ConfigLoader getConfigLoader() {

        Path configPath = Paths.get(System.getProperty("user.dir"), "Nivell2", "Exercici1", "resources", "config.properties");

        if (!Files.exists(configPath)) {
            System.out.println("Configuration file not found: " + configPath);
            return null;
        }

        try {
            ConfigLoader configLoader = new ConfigLoader();
            configLoader.loadConfig(configPath.toString());

            String inputDir = configLoader.getProperty("inputDirectory");
            if (inputDir == null || inputDir.isBlank()) {
                System.out.println("Missing 'inputDirectory' in config file.");
            }

            String outputDir = configLoader.getProperty("outputDirectory");
            if (outputDir == null || outputDir.isBlank()) {
                System.out.println("Missing 'outputDirectory' in config file.");
            }

            String fileName = configLoader.getProperty("fileName");
            if (fileName == null || fileName.isBlank()) {
                System.out.println("Missing 'fileName' in config file.");
            }

            return configLoader;

        } catch (IOException e) {
            System.out.println("Error loading configuration file: " + e.getMessage());
            return null;
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
