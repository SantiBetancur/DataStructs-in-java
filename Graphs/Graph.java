import java.util.ArrayList;
import java.util.List;

class Graph {

    private List<Vertex> vertices;

    public Graph() {
        this.vertices = new ArrayList<>();
    }

    public void addVertex(Vertex vertex) {
        this.vertices.add(vertex);
    }

    public void printAdjacencyList(Graph graph){
        for(Vertex v : graph.vertices){
            System.out.print("Vertex " + v.id + " -> ");
            for (Vertex neig : v.neighbors){
                System.out.print(neig.id + " ");
            }
            System.out.println();
        }
    }

    public  Vertex fill_vertex(Vertex v, List<int[]> pairs){
        for (int[] i : pairs){
         if (v.id == i[0]){
             Vertex neigVertex = new Vertex(i[1]);
             v.neighbors.add(neigVertex);
         }
        }
        return v;
     }
    
    public int[][] adjacencyMatrix(List<int[]> p){
        int size = this.vertices.size();
        int[][] matrix = new int[size][size]; 
        for (int[] i : p){
            addEdge(matrix, i[0] - 1, i[1] - 1);
        }
        return matrix; 
    }

    public void printMatrix(int[][] matrix){
        for (int i = 0; i < matrix.length; i++){
            System.out.print((i + 1) + ": ");
            for (int j = 0; j < matrix[i].length; j++){
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    //Applicable to undirected graph    
    public void addEdge(int[][] matrix, int source, int dest){
        
        matrix[source][dest] = 1;
        matrix[dest][source] = 1;
    }

    
}

