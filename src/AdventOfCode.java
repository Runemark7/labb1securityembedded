public class AdventOfCode {

    int doorKey = 10441485;
    int cardKey = 1004920;

    public AdventOfCode(){
        int i = 0;
        int transNum = 1;
        int val = 1;

        while (doorKey != transNum){
            val *= 7;
            val %= 20201227;
            transNum = val;
            i++;
        }

        long encryptionVal = 1;
        for (int j = 0; j < i; j++) {
            encryptionVal *= cardKey;
            encryptionVal %= 20201227;
        }

        System.out.println(encryptionVal);
    }

    public static void main(String[] args) {
        AdventOfCode code = new AdventOfCode();
    }
}
