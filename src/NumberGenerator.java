import java.util.Random;

public class NumberGenerator {
    public static int[] generateNumbers(int x) {
        Random random = new Random();
        int[] finalArray = new int[x];        

        for (int i = 0; i < x; i++) {
            finalArray[i] = random.nextInt(350);
        }

        return finalArray;
    }
}
