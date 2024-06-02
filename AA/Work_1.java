

public class Work_1 {

    public static void selectionSort(int[] arr){


        for (int i = 0; i < arr.length - 1; i++){
            int smallest = i;
            for (int j = i + 1; j < arr.length - 1; j++){
                if(arr[j] < arr[smallest]){
                    smallest = j;
                }
            }

            //swaping
            swap(arr, smallest, i);
        }
    }

    public static void swap(int[] arr, int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    /**
     * QuickSort
     */
    public static void quickSort(int[] arr, int first_el, int last_el){
        if (first_el < last_el){
            int q = partition(arr, first_el, last_el);
            quickSort(arr, first_el, q - 1);//Sort the right side
            quickSort(arr, q + 1, last_el );//Sort the left side
        }

    }

    public static int partition(int[] arr, int p, int r){
        int pivot = arr[r];
        int index = p - 1; //Index of the elements that are lesser that the pivot
        for(int j = p; j < r; j++){
            if (arr[j] <= pivot){
                index++;
                swap(arr, index, j);
            }
        }

        swap(arr, index +1, r);
        return index +1;
    }

    /**
     * MergeSort
     */
    public static void merge_sort(int[] array){ // Merge_Sort
        int length = array.length;

        if (length <= 1) return; // Base Case

        int mid = length / 2;
        int[] left = new int[mid];
        int[] right = new int[length - mid];
        int l = 0; // to fill left array;
        int r = 0; // to fill rigth array;

        // fill each side -> DIVIDE
        for (; l < length; l ++){
            if(l < mid){
                left[l] = array[l];
            }else{
                right[r] = array[l];
                r++;
            }
        }
        // Recursively divide each part in 2  until to reach to the base case.
        merge_sort(left);
        merge_sort(right);
        // And merge each part -> CONQUER
        merge(left, right, array);
    }

    private static void merge(int[] left, int[] right, int[] array){

        int leftSize = array.length / 2;
        int rightSize = array.length - leftSize;
        //To keep track of the currently position in the three arrays
        int i = 0; 
        int l = 0;
        int r = 0;

        // Conditions of merging
        //In this while will check if is possible add more elements to the original array.
        //Re-combine the original array
        while ( l < leftSize && r < rightSize ){

            if(left[l] <= right[r]){
                array[i] = left[l];
                i++;
                l++;
            }else{
                array[i] = right[r];
                i++;
                r++;
            }
        }    
        //In this while will check if there is one element remaining for add into the array.
        while (l < leftSize){
            array[i] = left[l];
            i++;
            l++;
        }
        while (r < rightSize){
            array[i] = right[r];
            i++;
            r++;
        }      
    }

    //Bubble Sort

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
    /*
     * Populate the array
     */
    static int[] populate(int maxNumbers) {
        int[] numbers = new int[maxNumbers];
        for (int i = 0; i < maxNumbers; i++) {
            numbers[i] = (int) (Math.random() * maxNumbers);
        }
        return numbers;
    }
    
    /**
     * Iterate for array size from 100000 to 1000000
     */
    public static void sortAndTime() {
        int count = 0;
        for (int n = 100000; n <= 1000000; n += 100000) {
            count++;
            int[] numbers = populate(n);
            int r = numbers.length - 1;

            System.out.println("\n____________"+count+"___________\n");
            long start = System.currentTimeMillis();
            quickSort(numbers, 0, r);
            long end = System.currentTimeMillis();
            System.out.println("Time to sort (Quick):     " + (end - start) + " milliseconds");

            start = System.currentTimeMillis();
            merge_sort(numbers);
            end = System.currentTimeMillis();
            System.out.println("Time to sort (Merge):     " + (end - start) + " milliseconds");

            start = System.currentTimeMillis();
            selectionSort(numbers);
            end = System.currentTimeMillis();
            System.out.println("Time to sort (Selection): " + (end - start) + " milliseconds");
        

            start = System.currentTimeMillis();
            bubble_sort(numbers);
            end = System.currentTimeMillis();
            System.out.println("Time to sort (Bubble):    " + (end - start) + " milliseconds");
            System.out.println("________________________");

        }
    }

    /**
     * Main program
     */
    public static void main(String[] args) {
        sortAndTime();
    }
    
}
