import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.OAEPParameterSpec;
import javax.crypto.spec.PSource;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Base64;
import java.math.BigInteger;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.spec.OAEPParameterSpec;
import javax.crypto.spec.PSource;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

public class RSANew {
    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeyException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(2048);
        KeyPair keyPair = keyGen.generateKeyPair();
        PublicKey pubKey = keyPair.getPublic();
        PrivateKey privKey = keyPair.getPrivate();

        RSAPrivateKey rsaPriv = (RSAPrivateKey) privKey;
        BigInteger d = rsaPriv.getPrivateExponent();
        BigInteger n = rsaPriv.getModulus();

        //Get the message to be encrypted
        String message = "This is a test message";
        byte[] messageBytes = message.getBytes();

        //Encrypt the message using the public key
        Cipher cipher = Cipher.getInstance("RSA/ECB/NoPadding");
        cipher.init(Cipher.ENCRYPT_MODE, pubKey);
        byte[] encryptedMessageBytes = cipher.doFinal(messageBytes);

        BigInteger encryptedMessage = new BigInteger(encryptedMessageBytes);
        BigInteger decryptedMessage = encryptedMessage.modPow(d, n);
        byte[] decryptedMessageBytes = decryptedMessage.toByteArray();
        String decryptedMessageString = new String(decryptedMessageBytes, StandardCharsets.UTF_8);

        System.out.println("Original Message: " + message);
        System.out.println("Decrypted Message: " + decryptedMessageString);
    }
}
