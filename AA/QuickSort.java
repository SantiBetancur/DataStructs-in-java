public class QuickSort {

    public static void quick_sort(int[] arr, int start, int end){
        if (end <= start) return; //Base case

        int pivot = partition(arr, start, end);

        quick_sort(arr, start, pivot -1);
        quick_sort(arr, pivot +1, end);

    }

    public static int partition(int[] arr, int start, int end){

        int pivot = arr[end];
        int i = start-1; //Index than i use to compare and left the lesser numbers that the pivot at left.

        for (int j = start; j <= end - 1; j++){
            if(arr[j] < pivot){
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }

        }
        i++;
        int temp = arr[i];
        arr[i] = arr[end];
        arr[end] = temp;

        return i; //Location of the pivot
    }


    public static void printArr(int[] arr){
        for (int i = 0; i < arr.length; i++ ){
            System.out.print(arr[i] + " | ");
        }
        System.out.println();
    }

    public static void main(String[] args){

        int[] arr = {34, 27, 5, 99, 22, 7, 2, 90, 5, 78, 98, 80, 23};

        System.out.println("Unsorted Array: ");
        printArr(arr);

        System.out.println("Sorted Array: ");
        quick_sort(arr, 0, arr.length - 1);
        printArr(arr);
    }
    
}
