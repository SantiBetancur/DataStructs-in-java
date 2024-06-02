public class Coordenada {
    int fila;
    int col;

    public Coordenada(int f, int c){
        this.fila = f;
        this.col = c;
    }

    //Validate if the coordinates are the same

    boolean equals(int fila, int col){
        return this.fila == fila && this.col == col;
    }
    
}
