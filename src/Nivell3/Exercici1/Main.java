package Nivell3.Exercici1;

import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class Main {
    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidAlgorithmParameterException, NoSuchPaddingException, IOException, InvalidKeyException {

        try {

            byte[] initializationVectorSize = new byte[16];
            new SecureRandom().nextBytes(initializationVectorSize);
            IvParameterSpec initializationVector = new IvParameterSpec(initializationVectorSize);

            ConfigLoader configLoader = new ConfigLoader();
            configLoader.loadConfig("config.properties");

            AESCipher cipher = new AESCipher(configLoader);
            SecretKey secretKey = cipher.generateAESKey();

            cipher.encryptFile(secretKey, initializationVector);
            cipher.decryptFile(secretKey, initializationVector);

        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
