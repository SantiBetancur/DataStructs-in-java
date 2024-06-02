import java.util.Scanner;

public class Laberinto{
    static String[] laberintoStrings = {
        "* ******************",
        "* ******** *********",
        "*       **        **",
        "* ***** ** ****** **",
        "* *****       *** **",
        "* ***** ***** *** **",
        "*       ***** *** **",
        "* ********    *** **",
        "*    ************   ",
        "********************",
    };

    static Coordenada entrada = new Coordenada(0, 1); //Start
    static Coordenada salida  = new Coordenada(8, 19); //Exit

    

    static Coordenada[] ordenBusqueda = {
        new Coordenada(0, -1), //Left
        new Coordenada(1, 0),//bottom
        new Coordenada(0, 1),//Right
        new Coordenada(-1, 0), //top
    };

    static Scanner in = new Scanner(System.in);
    static final boolean SBS = false; //Flag

    //Convert labyrinth from String to Char

    static char[][] string_to_char(){
        char[][] laberinto;
        int m = laberintoStrings.length;
        int n = laberintoStrings[0].length();
        laberinto = new char[m][n];
        for (int fila = 0; fila < m; fila++){
            for (int col = 0; col < n; col++){
                laberinto[fila][col] = laberintoStrings[fila].charAt(col);
            }
        }
        return laberinto;
    }

    static char[][] laberinto_actual = string_to_char();

    static void imprimirLaberinto() {
        int m = laberinto_actual.length;
        int n = laberinto_actual[0].length;
        for (int fila = 0; fila < m; fila++) {
            for (int col = 0; col < n; col++) {
                System.out.print(laberinto_actual[fila][col]);
            }
            System.out.println();
        }
        System.out.println();
    }

    

    static boolean llenarLaberinto(int fila, int col){

        //Mark if a cell was visited.
        laberinto_actual[fila][col] = '.';
        if (SBS){
            imprimirLaberinto();
        }

        //Found the exit.
        if(salida.equals(fila, col)){
            System.out.println("___|LLEGUÉ|___");
            laberinto_actual[fila][col] = 'X';
            return true;
        }

        //Try in all directions
        for (Coordenada ob : ordenBusqueda){
            int proxFila = fila + ob.fila;
            int proxCol = col + ob.col;

            if(!enRango(proxFila, proxCol)){
                continue;
            }
            if (laberinto_actual[proxFila][proxCol] == ' '){
                //Available cell
                if(llenarLaberinto(proxFila, proxCol)){
                    return true;
                }
            }
            
        }
        //Visited, but not part of the solution
        laberinto_actual[fila][col] = ';';
        return false;
    }
    static boolean enRango(int fila, int col){

    return (0 <= fila && fila < laberintoStrings.length && 0 <= col && col < laberintoStrings[0].length());
    }

    public static void main(String[] args){
        imprimirLaberinto();
        if(llenarLaberinto(entrada.fila, entrada.col)){
            System.out.println("___|Exito|___\n");
        }else{
            System.out.println("No se encontró una salida\n");
        }
    }


}

