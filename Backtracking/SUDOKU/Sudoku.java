import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Sudoku{
    public static boolean DEBUG = false;

    int [][] input = new int[9][9];
    int [][] current = new int [9][9];

    int backtracks = 0;

    void printMatrix(int [][] m){
        System.out.println("Table: ");
        System.out.println("_________________");
        for(int row = 0; row < 9; row++){
            for (int col = 0; col < 9; col++){
                System.out.print(m[row][col] + "|");
            }
            System.out.println();
        }
        System.out.println("_________________");
        

    }

    boolean testRow(int row, int n){
        for (int col = 0; col < 9; col++){
            if (current[row][col] == n){
                return false;
            }
        }
        return true;
    }

    boolean testCol(int col, int n){
        for (int row = 0; row < 9; row++){
            if(current[row][col] == n){
                return false;
            }
        }
        return true;
    }

   
    boolean testBlock(int row, int col, int n){
        int initialRow = (row / 3) * 3;
        int initialCol = (col / 3) * 3;
        for (int r = initialRow; r < initialRow + 3; r++){
            for(int c = initialCol; c < initialCol + 3; c++){
                if(current[r][c] == n){
                    return false;
                }

            }
        }
        return true;
    }

    boolean test(int row, int col){
        //There is a solution, print it.
        if(row == 9){
            printMatrix(current);
            return true;
        }


        if(input[row][col] != 0){
            Position pos = new Position(row, col);
            pos.next();
            return(test(pos.row, pos.col));
        }

        for (int n = 1; n <= 9; n++){
            if(testRow(row, n)){
                if(testCol(col, n)){
                    if(testBlock(row, col, n)){
                            current[row][col] = n;
                            Position pos = new  Position(row, col);
                            pos.next();
                            if(test(pos.row, pos.col)) return true;
                            //Will search many solutions recursively

                    }
                }
            }
        }
        // If exit to the for, it found an inconsistency, no number worked;

        //Fill the current matrix to do the backtrack;

        current[row][col] = 0;
        return false;
    }

    void init(){
        for (int row = 0; row < 9; row++){
            for (int col = 0; col < 9; col ++){
                current[row][col] = input[row][col];
            }
        }
    }

    public void readFile(String name){
        File f = new File(name);
        try{
            Scanner in = new Scanner(f);
            int row = 0;
            while(in.hasNextLine()){
                String line = in.nextLine();
                for (int pos = 0; pos < 9; pos++){
                    this.input[row][pos] = Character.getNumericValue(line.charAt(pos));
                }
                row++;    
            }
            
            in.close();
        }catch (FileNotFoundException ex){
            System.out.println("Must be a file name sudoku.txt");

        }
        
    }

    private class Position {
        int row;
        int col;

        public Position(int row, int col){
            this.row = row;
            this.col = col;
        }

        public void next(){
            this.col ++;
            if(this.col == 9){
                this.col = 0;
                this.row++;
            }
        }
    }

    public static void main(String [] args) {
        Sudoku s = new Sudoku();
        s.readFile("Sudoku.txt");
        s.init();
        s.printMatrix(s.current);
        // Ensayar con la primera celda
        s.test(0, 0);
        System.out.println("Backtracks: " + s.backtracks);
    }


}   

