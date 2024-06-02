public class BubbleSort {

    public static void bubble_sort(int[] array){
        for (int i = 0; i < array.length - 1; i++){
            for (int j = 0; j < array.length - i - 1; j++){
                if (array[j] > array[j + 1]){
                    int temp = array[j];
                    array[j] = array[j +1];
                    array[j + 1] = temp;
                }
            }
        }
    }

    private static void printArray(int[] arr){
        for (int p : arr){
            System.out.print("| "+p+" |");
        }
        System.out.println("\n");

    }

    public static void main(String[] args){
        int[] array = {12, 3, 4, 65, 12, 43, 12, 44, 65, 123, 34, 15, 26, 5, 6, 2, 1, 46, 67};
        System.out.println("Unsorted");
        printArray(array);
        System.out.println("Sorted");
        bubble_sort(array);
        printArray(array);
    }
    
}
