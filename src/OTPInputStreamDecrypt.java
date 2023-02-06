import java.io.IOException;
import java.io.InputStream;

public class OTPInputStreamDecrypt extends InputStream {
    private InputStream input;
    private InputStream key;
    private boolean xor;

    public OTPInputStreamDecrypt(InputStream input, InputStream key, boolean xor) {
        this.input = input;
        this.key = key;
        this.xor = xor;
    }

    @Override
    public int read() throws IOException {
        int inputByte = input.read();
        int keyByte = key.read();

        if (xor) {
            return ((inputByte-'A') & 0xff) ^ ((keyByte-'A') & 0xff);
        } else {
            if (inputByte == -1 || keyByte == -1) {
                return -1;
            }

            int test = inputByte - keyByte;

            int testTwo =  Math.floorMod(test , 26);

            return testTwo;
        }
    }

    @Override
    public void close() throws IOException {
        input.close();
        key.close();
    }
}
