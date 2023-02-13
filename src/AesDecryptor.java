import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HexFormat;


public class AesDecryptor {

    public AesDecryptor(String key, String iv, String encryptedData) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, ShortBufferException, IOException, InvalidAlgorithmParameterException, InterruptedException {
        byte[] decodedEncryptedData = HexFormat.of().parseHex(encryptedData);
        SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "AES");
        IvParameterSpec ivSpec = new IvParameterSpec(iv.getBytes(StandardCharsets.UTF_8));

        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, secretKey, ivSpec);

        byte[] decryptedData = cipher.doFinal(decodedEncryptedData);
        System.out.println(new String(decryptedData, StandardCharsets.UTF_8));
    }

    public static void main(String[] args) throws NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException, ShortBufferException, InvalidAlgorithmParameterException, IOException, InterruptedException {
        AesDecryptor aesDecryptorTestOne = new AesDecryptor("0123456789abcdef","0000000000000000","1ff4ec7cef0e00d81b2d55a4bfdad4ba");
        AesDecryptor aesDecryptorTestTwo = new AesDecryptor("0011223344556677","0123456789abcdef","9e4816cc13810b8424d788fbcd4b006b31bf45f5f9191072820ae0a545500c966cf22afda1002466a78b7e4ddf02587f");
    }



}
