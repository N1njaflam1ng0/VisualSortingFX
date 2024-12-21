public class BubbleSort {
    public static int[] oneIterationBubbleSort(int[] array, int index) {
        int temp;
        for (int i = 0; i < array.length; i++) {
            System.out.print("1: " + array[i] + " ");
        }
        System.out.println();
        try {
            if (array[index] > array[index + 1]) {
                temp = array[index];
                array[index] = array[index + 1];
                array[index + 1] = temp;
            }
            for (int i = 0; i < array.length; i++) {
                System.out.print("2: "+array[i] + " ");
            }        
        } catch (Exception e) {
            
        }
        System.out.println();
        return array;
    }
}
