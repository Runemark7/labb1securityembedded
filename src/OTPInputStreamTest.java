import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.IOException;

public class OTPInputStreamTest {
    public static void main(String[] args) throws IOException {
        byte[] inputData = {'H', 'E', 'L', 'L', 'O'};
        byte[] keyData = {'X', 'M', 'C', 'K', 'L'};
        boolean xor = true;

        InputStream input = new ByteArrayInputStream(inputData);
        InputStream key = new ByteArrayInputStream(keyData);
        InputStream encrypted = new OTPInputStream(input, key, xor);

        System.out.println("ENCRYPTED:");
        byte[] deCrypterArr = new byte[5];
        int b;
        int index = 0;
        while (((b = encrypted.read()) != -1) && index < 5) {
            if (xor){
                System.out.println((char)(b+65));
                deCrypterArr[index] = (byte) (b+65);
            }else{
                System.out.println((char)b);
                deCrypterArr[index] = (byte)b;
            }
            index++;
        }
        encrypted.close();

        System.out.println("DECRYPTED:");
        InputStream deCrpytedStream =  new ByteArrayInputStream(deCrypterArr);
        InputStream keyTwo = new ByteArrayInputStream(keyData);
        InputStream deCrypted = new OTPInputStreamDecrypt(deCrpytedStream, keyTwo, xor);

        int c;
        int indexTwo = 0;
        while (((c = deCrypted.read()) != -1) && indexTwo < 5 ) {
            System.out.println((char)(c+65));
            indexTwo++;
        }
        deCrypted.close();
    }
}



