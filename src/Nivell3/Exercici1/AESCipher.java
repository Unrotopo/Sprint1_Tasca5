package Nivell3.Exercici1;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class AESCipher {
    public final String ALGORITHM = "AES";
    public final String TRANSFORMATION = "AES/CBC/PKCS5Padding";
    private String inputDirectory;
    private String outputDirectory;
    private String inFileName;
    private String outFileName;


    public AESCipher(ConfigLoader config) {
        inputDirectory = config.getProperty("inputDirectoryCrypt");
        outputDirectory = config.getProperty("outputDirectoryCrypt");
        inFileName = config.getProperty("inFileNameCrypt");
        outFileName = config.getProperty("outFileNameCrypt");

    }

    public void encryptFile(SecretKey secretKey, IvParameterSpec initializationVector) throws IOException, InvalidAlgorithmParameterException, InvalidKeyException, NoSuchPaddingException, NoSuchAlgorithmException {

        byte[] initializationVectorSize = new byte[16];

        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, initializationVector);

        try (FileInputStream fis = new FileInputStream(inputDirectory + inFileName);
             FileOutputStream fos = new FileOutputStream(outputDirectory + outFileName);
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

    public void decryptFile(SecretKey secretKey, IvParameterSpec initializationVector) throws Exception {

        byte[] initializationVectorSize = new byte[16];

        try (FileInputStream fis = new FileInputStream(outputDirectory + outFileName);
             FileOutputStream fos = new FileOutputStream(outputDirectory + inFileName)) {

            fis.read(initializationVectorSize);

            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            cipher.init(Cipher.DECRYPT_MODE, secretKey, initializationVector);

            try (CipherInputStream cis = new CipherInputStream(fis, cipher)) {
                byte[] buffer = new byte[2048];
                int bytesRead;
                while ((bytesRead = cis.read(buffer)) != -1) {
                    fos.write(buffer, 0, bytesRead);
                }
            }
        }
    }

    public SecretKey generateAESKey() throws NoSuchAlgorithmException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance(ALGORITHM);
        keyGenerator.init(256);
        return keyGenerator.generateKey();
    }
}
