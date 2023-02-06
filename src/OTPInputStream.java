import java.io.InputStream;
import java.io.IOException;

public class OTPInputStream extends InputStream {
    private InputStream input;
    private InputStream key;
    private boolean xor;

    public OTPInputStream(InputStream input, InputStream key, boolean xor) {
        this.input = input;
        this.key = key;
        this.xor = xor;
    }

    @Override
    public int read() throws IOException {
        int inputByte = input.read();
        int keyByte = key.read();

        if (xor) {
            return ((inputByte-'A') ^ (keyByte-'A'));
        } else {
            if (inputByte == -1 || keyByte == -1) {
                return -1;
            }
            return (inputByte + keyByte) % 26 + 'A';
        }
    }

    @Override
    public void close() throws IOException {
        input.close();
        key.close();
    }
}
