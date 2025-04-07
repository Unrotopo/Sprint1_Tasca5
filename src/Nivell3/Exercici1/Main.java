package Nivell3.Exercici1;

import javax.crypto.NoSuchPaddingException;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class Main {
    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidAlgorithmParameterException, NoSuchPaddingException, IOException, InvalidKeyException {

        RunCrypto.runEncryption();

        RunCrypto.runDecryption();
    }
}
