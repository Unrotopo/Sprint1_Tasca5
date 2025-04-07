package Nivell3.Exercici1;

public class DirectoryCheck {

    public static void checkDirectory(String directoryPath) {
        java.io.File directory = new java.io.File(directoryPath);
        if (!directory.exists()) {
            System.out.println("Directory does not exist: " + directoryPath);
            if (directory.mkdirs()) {
                System.out.println("Directory created: " + directoryPath);
            } else {
                System.out.println("Failed to create directory: " + directoryPath);
            }
        } else {
            System.out.println("Directory exists: " + directoryPath);
        }
    }
}
