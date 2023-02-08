import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.OAEPParameterSpec;
import javax.crypto.spec.PSource;
import java.math.BigInteger;
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

        RSAPublicKey rsaPub  = (RSAPublicKey) (pubKey);
        RSAPrivateKey rsaPriv  = (RSAPrivateKey) (privKey);
        BigInteger publicKeyModulus = rsaPub.getModulus();
        BigInteger privateKeyModulus  = rsaPriv.getModulus();

        System.out.println("publicKeyModulus: " + publicKeyModulus);
        System.out.println("privateKeyModulus: " + privateKeyModulus);

        RSA rsa = new RSA(publicKeyModulus.toString(), privateKeyModulus.toString(), rsaPub.getPublicExponent().toString());
        BigInteger c = rsa.encrypt(new BigInteger("65"));
        System.out.println("dec(c)  = " + rsa.decrypt(c));
/*
        System.out.println(c);

        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, privKey);
        byte[] decryptedMessage = cipher.doFinal(c.toByteArray());
        BigInteger decryptedMessageInt = new BigInteger(decryptedMessage);

        System.out.println(decryptedMessageInt);
*/




        /*
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, pubKey);
        byte[] message = new BigInteger("65").toByteArray();
        byte[] encryptedMessage = cipher.doFinal(message);

        cipher.init(Cipher.DECRYPT_MODE, privKey);
        byte[] decryptedMessage = cipher.doFinal(encryptedMessage);
        BigInteger decryptedMessageInt = new BigInteger(decryptedMessage);

        System.out.println("Original message: " + new BigInteger("65"));
        System.out.println("Encrypted message: " + new BigInteger(encryptedMessage));
        System.out.println("Decrypted message: " + decryptedMessageInt);
         */
    }

}
