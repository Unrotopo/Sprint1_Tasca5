package Nivell3.Exercici1;

import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class Main {
    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidAlgorithmParameterException, NoSuchPaddingException, IOException, InvalidKeyException {

        try {
            ConfigLoader configLoader = new ConfigLoader();
            configLoader.loadConfig("config.properties");

            AESCipher cipher = new AESCipher(configLoader);
            SecretKey secretKey = cipher.generateAESKey();
            cipher.encryptFile(secretKey);

            // cipher.decryptFile(secretKey, "decryptedFile.txt");

        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
