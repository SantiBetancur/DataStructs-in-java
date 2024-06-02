import java.util.Arrays;
public class Reto1 {

    public static int[][] multiply(int[][] A, int[][] B){
        int n = A.length;

        // Base case of the recursive method
        if (n == 1){
            int[][] C = new int[1][1];
            C[0][0] = A[0][0] * B[0][0];
            return C;
        } else {
            /*
            Here we create each partition of both matrix, A and B.
            */ 
            int[][] A11 = new int[n / 2][n / 2];
            int[][] A12 = new int[n / 2][n / 2];
            int[][] A21 = new int[n / 2][n / 2];
            int[][] A22 = new int[n / 2][n / 2];
            int[][] B11 = new int[n / 2][n / 2];
            int[][] B12 = new int[n / 2][n / 2];
            int[][] B21 = new int[n / 2][n / 2];
            int[][] B22 = new int[n / 2][n / 2];

            split(A, A11, A12, A21, A22);
            split(B, B11, B12, B21, B22);

            // Now recursively multiply the sort pairs

            int[][] P01 = multiply(A11, B11);
            int[][] P02 = multiply(A12, B21);
            int[][] P03 = multiply(A11, B12);
            int[][] P04 = multiply(A12, B22);
            int[][] P05 = multiply(A21, B11);
            int[][] P06 = multiply(A22, B21);
            int[][] P07 = multiply(A21, B12);
            int[][] P08 = multiply(A22, B22);

            // And create the final matrix 
            int[][] C11 = add(P01, P02);
            int[][] C12 = add(P03, P04);
            int[][] C21 = add(P05, P06);
            int[][] C22 = add(P07, P08);

            int[][] C = new int[n][n];
            join(C11, C12, C21, C22, C);

            return C;
        }
    }    

    private static int[][] subtract(int[][] A, int[][] B){
        int n = A.length;
        int[][] C = new int[n][n];
        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
                C[i][j] = A[i][j] - B[i][j];
            }
        }
        return C;
    }

    private static void split(int[][] A, int[][] A11, int[][] A12, int[][] A21, int[][] A22){
        int n = A.length / 2;

        for (int i = 0; i < n; i++){
            System.arraycopy(A[i], 0, A11[i], 0, n);
            System.arraycopy(A[i], n, A12[i], 0, n);
            System.arraycopy(A[i + n], 0, A21[i], 0, n);
            System.arraycopy(A[i + n], n, A22[i], 0, n);
        }
    }

    private static void join(int[][] C11, int[][] C12, int[][] C21, int[][] C22, int[][] C) {
        int n = C11.length;

        for (int i = 0; i < n; i++) {
            System.arraycopy(C11[i], 0, C[i], 0, n);
            System.arraycopy(C12[i], 0, C[i], n, n);
            System.arraycopy(C21[i], 0, C[i + n], 0, n);
            System.arraycopy(C22[i], 0, C[i + n], n, n);
        }
    }

    private static int[][] add(int[][] A, int[][] B) {
        int n = A.length;
        int[][] C = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                C[i][j] = A[i][j] + B[i][j];
            }
        }

        return C;
    }
    
    //Traditional Algorithm, it needs that the matrix A and B must be square
    public static int[][] normalMultiplication(int[][] a, int[][] b) {
        int n = a.length;
        int[][] c = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int acum = 0;
                for (int k = 0; k < n; k++) {
                    acum += a[i][k] * b[k][j];
                }
                c[i][j] = acum;
            }
        }
        return c;
    }

    public static int nextPowerOfTwo(int n) {
        int m = (int) (Math.ceil(Math.log(n) / Math.log(2)));
        return (int) (Math.pow(2, m));
    }

    public static int[][] generateMatrix(int n) {
        int m = nextPowerOfTwo(n);
        int[][] M = new int[m][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                M[i][j] = (int) (Math.random() * 10);
            }
        }
        return M;
    }


    //Strassen's Method
    public static int[][] st_multiply(int[][] A, int[][] B){
        int n = A.length;

        if(n == 1){
            int[][] C = new int[1][1];
            C[0][0] = A[0][0] * B[0][0];
            return C;
        }else{

            int[][] A11 = new int[n / 2][n / 2];
            int[][] A12 = new int[n / 2][n / 2];
            int[][] A21 = new int[n / 2][n / 2];
            int[][] A22 = new int[n / 2][n / 2];
            int[][] B11 = new int[n / 2][n / 2];
            int[][] B12 = new int[n / 2][n / 2];
            int[][] B21 = new int[n / 2][n / 2];
            int[][] B22 = new int[n / 2][n / 2];

            split(A, A11, A12, A21, A22);
            split(B, B11, B12, B21, B22);


            //First i'm going to create each part of the matrix, adding and subtracting acording the Strassen's method
            int[][] S01 = add(A11, A22);
            int[][] S02 = add(B11, B22);
            int[][] S03 = add(A21, A22);
            int[][] S04 = subtract(B12, B22);
            int[][] S05 = subtract(B21, B11);
            int[][] S06 = add(A11, A12);
            int[][] S07 = subtract(A21, A11);
            int[][] S08 = add(B11, B12);
            int[][] S09 = subtract(A12, A22);
            int[][] S010 = add(B21, B22);
            //Then i'm doing the multiplications

            int[][] P01 = st_multiply(S01, S02);
            int[][] P02 = st_multiply(S03, B11);
            int[][] P03 = st_multiply(A11, S04);
            int[][] P04 = st_multiply(A22, S05);
            int[][] P05 = st_multiply(S06, B22);
            int[][] P06 = st_multiply(S06, S08);
            int[][] P07 = st_multiply(S09, S010);
            //Now i have 7 products of the matrix, then i'm going to join all parts to create the final matrix

            int[][] C11 = subtract(add(add(P01, P04), P07), P05);
            int[][] C12 = add(P03, P05);
            int[][] C21 = add(P02, P04);
            int[][] C22 = add(subtract(P01, P02), add(P03, P06));
            
            int[][] C = new int[n][n];
            join(C11, C12, C21, C22, C);

            return C;
        }

    }


    public static void main(String[] args) {
        /*
            * for(int i = 1; i <= 2048; i++) {
            * System.out.println(i + " " + nextPowerOfTwo(i));
            * }
            */

        int[][] A = { { 2, 3, 4, 5 }, { 4, 5, 6, 7 }, { 7, 8, 9, 10 }, { 1, 2, 3, 4 } };
        int[][] B = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 }, { 1, 1, 1, 1 } };
        System.out.println("Naive Result: ");        
        int[][] C = multiply(A, B);
        System.out.println(Arrays.deepToString(C));

        System.out.println("Strassen Result: ");         
        int[][] C1 = st_multiply(A, B);
        System.out.println(Arrays.deepToString(C1));
        
        System.out.println("Normal Result: ");         
        int[][] d = normalMultiplication(A, B);
        System.out.println(Arrays.deepToString(d));

        //Normal Multiplication
        System.out.println("Normal Algorithm");
        for (int n = 100; n <= 500; n += 100) {
            A = generateMatrix(n);
            B = generateMatrix(n);
            // System.out.println("Starting");
            long startTime = System.currentTimeMillis();
            int[][] C2 = normalMultiplication(A, B);
            long endTime = System.currentTimeMillis();
            long elapsedTime = endTime - startTime;
            // System.out.println("Finished");
            System.out.println("Size: " + n + " time: " + elapsedTime);
        }

        // Naïve
        System.out.println("Naive");
        for (int n = 100; n <= 500; n += 100) {
            A = generateMatrix(n);
            B = generateMatrix(n);
            // System.out.println("Starting");
            long startTime = System.currentTimeMillis();
            int[][] C3 = multiply(A, B);
            long endTime = System.currentTimeMillis();
            long elapsedTime = endTime - startTime;
            // System.out.println("Finished");
            System.out.println("Size: " + n + " time: " + elapsedTime);
        }

        // Strassen
        System.out.println("Strassen");
        for (int n = 100; n <= 500; n += 100) {
            A = generateMatrix(n);
            B = generateMatrix(n);
            // System.out.println("Starting");
            long startTime = System.currentTimeMillis();
            int[][] C4 = st_multiply(A, B);
            long endTime = System.currentTimeMillis();
            long elapsedTime = endTime - startTime;
            // System.out.println("Finished");
            System.out.println("Size: " + n + " time: " + elapsedTime);
        }
    }





}

