package Nivell3.Exercici1;

import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class RunCrypto {

    public static void runEncryption() {

        try {
            IvParameterSpec initializationVector = getIV();
            ConfigLoader configLoader = ConfigLoader.getConfigLoader();
            AESCipher cipher = new AESCipher(configLoader);
            SecretKey secretKey = cipher.generateAESKey();

            cipher.encryptFile(secretKey, initializationVector);

        } catch (IOException | NoSuchAlgorithmException e) {
            System.out.println("Error encrypting the file: " + e.getMessage());
        }
    }

    public static IvParameterSpec getIV() {
        byte[] initializationVectorSize = new byte[16];
        new SecureRandom().nextBytes(initializationVectorSize);
        return new IvParameterSpec(initializationVectorSize);
    }

    public static void runDecryption() {

        try {
            ConfigLoader configLoader = ConfigLoader.getConfigLoader();
            AESCipher cipher = new AESCipher(configLoader);
            SecretKey secretKey = cipher.generateAESKey();

            cipher.decryptFile(secretKey);

        } catch (Exception e) {
            System.out.println("Error decrypting the file: " + e.getMessage());
        }
    }
}

