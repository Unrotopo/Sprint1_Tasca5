package Nivell3.Exercici1;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class AESCipher {
    public final String ALGORITHM = "AES";
    public final String TRANSFORMATION = "AES/CBC/PKCS5Padding";
    private String inputDirectory;
    private String outputDirectory;
    private String fileName;

    public AESCipher(ConfigLoader config) {
        inputDirectory = config.getProperty("inputDirectoryCrypt");
        outputDirectory = config.getProperty("outputDirectoryCrypt");
        fileName = config.getProperty("fileNameCrypt");
    }

    public void encryptFile(SecretKey secretKey) throws IOException, InvalidAlgorithmParameterException, InvalidKeyException, NoSuchPaddingException, NoSuchAlgorithmException {
        byte[] initializationVectorSize = new byte[16];
        new SecureRandom().nextBytes(initializationVectorSize);
        IvParameterSpec initializationVector = new IvParameterSpec(initializationVectorSize);

        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, initializationVector);

        try (FileInputStream fis = new FileInputStream(inputDirectory + fileName);
             FileOutputStream fos = new FileOutputStream(outputDirectory + "SecretKnowledge.aes");
             CipherOutputStream cos = new CipherOutputStream(fos, cipher)) {

            fos.write(initializationVectorSize);

            byte[] buffer = new byte[2048];
            int bytesRead;
            while ((bytesRead = fis.read(buffer)) != -1) {
                cos.write(buffer, 0, bytesRead);
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void decryptFile(SecretKey secretKey, String newDecryptedFile) throws Exception {
        
    }

    public SecretKey generateAESKey() throws NoSuchAlgorithmException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance(ALGORITHM);
        keyGenerator.init(256);
        return keyGenerator.generateKey();
    }
}
