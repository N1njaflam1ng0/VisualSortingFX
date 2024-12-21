import java.util.Random;

public class NumberGenerator {
    public static int[] generateNumbers(int x) {
        if (x > 50) {
            x = 50;
        }
        Random random = new Random();
        int[] finalArray = new int[x];        

        for (int i = 0; i < x; i++) {
            finalArray[i] = random.nextInt(10, 350);
        }

        return finalArray;
    }
}
