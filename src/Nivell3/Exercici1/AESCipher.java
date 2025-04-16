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
    private final String inputDirectory;
    private final String outputDirectory;
    private final String inFileName;
    private final String outFileName;


    public AESCipher(ConfigLoader config) {
        inputDirectory = config.getProperty("inputDirectoryCrypt");
        outputDirectory = config.getProperty("outputDirectoryCrypt");
        inFileName = config.getProperty("inFileNameCrypt");
        outFileName = config.getProperty("outFileNameCrypt");

    }

    public void checkDirectories() {
        DirectoryCheck.checkDirectory(inputDirectory);
        DirectoryCheck.checkDirectory(outputDirectory);
    }

    public void encryptFile(SecretKey secretKey, IvParameterSpec initializationVector) {

        Cipher cipher = getCipher(secretKey, initializationVector);

        try (FileInputStream fis = new FileInputStream(inputDirectory + inFileName);
             FileOutputStream fos = new FileOutputStream(outputDirectory + outFileName);
             CipherOutputStream cos = new CipherOutputStream(fos, cipher)) {

            fos.write(initializationVector.getIV());

            byte[] buffer = new byte[2048];
            int bytesRead;
            while ((bytesRead = fis.read(buffer)) != -1) {
                cos.write(buffer, 0, bytesRead);
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public Cipher getCipher(SecretKey secretKey, IvParameterSpec initializationVector) {
        try {
            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, initializationVector);
            checkDirectories();
            return cipher;

        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | InvalidAlgorithmParameterException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public void decryptFile(SecretKey secretKey) throws IllegalArgumentException {

        checkDirectories();

        try (FileInputStream fis = new FileInputStream(outputDirectory + outFileName);
             FileOutputStream fos = new FileOutputStream(outputDirectory + inFileName)) {

            IvParameterSpec iv = readInitializationVector(fis);
            Cipher cipher = initCipher(secretKey, iv);
            readEncryptedFile(fis, fos, cipher);

        } catch (NoSuchPaddingException | InvalidAlgorithmParameterException | IOException | InvalidKeyException |
                 NoSuchAlgorithmException e) {
            System.out.println(e.getMessage());
        }
    }

    private Cipher initCipher(SecretKey key, IvParameterSpec iv)
            throws NoSuchPaddingException, NoSuchAlgorithmException,
            InvalidKeyException, InvalidAlgorithmParameterException {

        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        cipher.init(Cipher.DECRYPT_MODE, key, iv);
        return cipher;
    }

    public void readEncryptedFile (FileInputStream fis, FileOutputStream fos, Cipher cipher) throws IllegalArgumentException {
        try (CipherInputStream cis = new CipherInputStream(fis, cipher)) {
            byte[] buffer = new byte[2048];
            int bytesRead;
            while ((bytesRead = cis.read(buffer)) != -1) {
                fos.write(buffer, 0, bytesRead);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private IvParameterSpec readInitializationVector(FileInputStream fis) throws IOException {
        byte[] ivBytes = new byte[16];
        if (fis.read(ivBytes) != 16) {
            throw new IllegalArgumentException("Invalid encrypted file: missing or incomplete IV.");
        }
        return new IvParameterSpec(ivBytes);
    }

    public SecretKey generateAESKey () throws NoSuchAlgorithmException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance(ALGORITHM);
        keyGenerator.init(256);
        return keyGenerator.generateKey();
    }
}
