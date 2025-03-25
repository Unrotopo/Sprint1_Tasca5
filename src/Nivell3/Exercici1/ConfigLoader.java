package Nivell3.Exercici1;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigLoader {

    private final Properties properties = new Properties();

    public void loadConfig(String filePath) throws IOException {
        try (FileInputStream fis = new FileInputStream(filePath)) {
            properties.load(fis);
        }
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }
}
