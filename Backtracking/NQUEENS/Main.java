    
public class Main {
    static final int N = 4;
    
    static int [][] tablero = new int[N][N];
    
    static int solucion = 0;
    static boolean ponerReina(int fila) {
        if(fila == N) {
            //Hemos llegado a la ultima fila
            solucion++;
            System.out.println("Solucion: " + solucion);
            imprimirTablero();
            return true;
        }

        
    boolean solucionEncontrada = false;

    for (int col = 0; col < N; col++) {
        if (validar(fila, col)) {
            // Puede colocar una reina en esta posición
            tablero[fila][col] = 1;  // Coloca la reina en el tablero
            solucionEncontrada = ponerReina(fila + 1) || solucionEncontrada;  // Llama recursivamente con la siguiente fila
            tablero[fila][col] = 0;  // Retrocede (backtrack) eliminando la reina de esta posición
            }
        }


        
        return solucionEncontrada;
    }
    
    static boolean validar(int fila, int col) {
        // Validar la columna
        for(int f = 0; f < fila; f++) {
            if(tablero[f][col] == 1) return false;
        }
        
        // Validar diagonal 1
        int f = fila - 1; int c = col - 1;
        while(f >= 0 && c >= 0) {
            if(tablero[f][c] == 1) return false;
            f--;
            c--;
        }
        // Validar diagonal 2
        f = fila - 1; c = col + 1;
        while(f >= 0 && c < N) {
            if(tablero[f][c] == 1) return false;
            f--;
            c++;
        }
        return true;
    }
    
    static void imprimirTablero() {
        for(int fila = 0; fila < N; fila++) {
            for(int col = 0; col < N; col++) {
                System.out.print(tablero[fila][col] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
    
    public static void main(String [] args) {
        imprimirTablero();
        ponerReina(0);
    }

}