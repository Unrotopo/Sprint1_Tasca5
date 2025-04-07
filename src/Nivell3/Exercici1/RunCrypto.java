package Nivell3.Exercici1;

import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class RunCrypto {

    public static void runEncryption() {

        try {
            byte[] initializationVectorSize = new byte[16];
            new SecureRandom().nextBytes(initializationVectorSize);
            IvParameterSpec initializationVector = new IvParameterSpec(initializationVectorSize);

            ConfigLoader configLoader = ConfigLoader.getConfigLoader();
            AESCipher cipher = new AESCipher(configLoader);
            SecretKey secretKey = cipher.generateAESKey();

            cipher.encryptFile(secretKey, initializationVector);

        } catch (IOException | InvalidAlgorithmParameterException | InvalidKeyException | NoSuchPaddingException |
                 NoSuchAlgorithmException e) {
            System.out.println("Error encrypting the file: " + e.getMessage());
        }
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

