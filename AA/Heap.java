

 class Heap {
    int [] A;
    int heapSize;

    Heap(int [] A, int n) {
        this.A = A;
        if(n >= A.length) {
            System.out.println("Error: n is too large");
            return;
        }
        this.heapSize = n;
    }

    int Left(int i) {
        return 2 * i;
    }   

    int Right(int i) {
        return 2 * i + 1;
    }   

    int Parent(int i) {
        return i / 2;
    }

    void maxHeapify(int i) {
        // Poner aquí el código que falta
    }
    
    void buildMaxHeap() {
        for (int i = heapSize / 2; i >= 1; i--) {
            maxHeapify(i);
        }
    }

    void heapSort() {
        buildMaxHeap();
        int n = A.length - 1;
        for (int i = n; i >= 2; i--) {
            swap(A, 1, i); // swap(A[0], A[i]
            heapSize--;
            maxHeapify(1);
        }
    }

    void swap(int [] A, int i, int j) {
        int temp = A[i];
        A[i] = A[j];
        A[j] = temp; 
    }

    void printArray() {
        System.out.print("A = " + A[1]);
        for (int i = 2; i < A.length; i++) {
            System.out.print(", " + A[i]);
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int [] A = {Integer.MAX_VALUE, 1, 4, 2, 3};
        Heap heap = new Heap(A, 4);
        heap.heapSort();
        heap.printArray();
    }    
    
}
