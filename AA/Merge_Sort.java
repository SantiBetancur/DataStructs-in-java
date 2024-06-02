public class Merge_Sort {

    private static void merge_sort(int[] array){ // Merge_Sort
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
        merge_sort(array);
        printArray(array);
    }
  
    
}
