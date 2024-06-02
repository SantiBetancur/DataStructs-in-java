public class SelectSort {

    public static void selectionSort(int[] arr){


        for (int i = 0; i < arr.length - 1; i++){
            int smallest = i;
            for (int j = i + 1; j < arr.length - 1; j++){
                if(arr[j] < arr[smallest]){
                    smallest = j;
                }
            }

            //swaping
            int temp = arr[smallest];
            arr[smallest] = arr[i];
            arr[i] = temp;
        }
    }
    public static void printArray(int[] arr){
        for (int i : arr){
            System.out.print(" |"+i+"| ");
        }
    }

    public static void main(String[] args){
        int[] arr = {23, 12, 32, 2, 40, 1, 98};
        selectionSort(arr);
        printArray(arr);
        
    }
    
}
