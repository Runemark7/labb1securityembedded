import javax.crypto.*;
import javax.crypto.spec.OAEPParameterSpec;
import javax.crypto.spec.PSource;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.interfaces.*;
import java.util.Arrays;
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
import java.util.HexFormat;

public class RSANew {
    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeyException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, IOException {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(2048);
        KeyPair keyPair = keyGen.generateKeyPair();
        PublicKey pubKey = keyPair.getPublic();
        PrivateKey privKey = keyPair.getPrivate();

        RSAPublicKey rsaPub  = (RSAPublicKey) (pubKey);
        RSAPrivateKey rsaPriv  = (RSAPrivateKey) (privKey);
        BigInteger privateKeyExponent  = rsaPriv.getPrivateExponent();
        BigInteger privateKeyMod = rsaPriv.getModulus();

        Cipher cipher = Cipher.getInstance("RSA/ECB/NoPadding");
        cipher.init(Cipher.ENCRYPT_MODE, rsaPub);
        String message = "test";
        byte[] encryptedMessage = cipher.doFinal(message.getBytes());

        var messageToDecrypt = new BigInteger(1, encryptedMessage);
        BigInteger decryptedMessage = messageToDecrypt.modPow(privateKeyExponent, privateKeyMod);

        byte[] bytes = decryptedMessage.toByteArray();
        String str = new String(bytes, StandardCharsets.UTF_8);

        System.out.println("Decrypted message: " + str);
    }
}
