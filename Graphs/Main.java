import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/*
 * @author Santiago Betancur
 * Credits to Chat GPT
 */

public class Main {

    //Vertices and edges of the graph
    static List<int[]> pairs = new ArrayList<>();

    public static void ExtractInfo (String filename) {


        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" ");
                if (parts.length == 2) {
                    int[] pair = new int[2];
                    pair[0] = Integer.parseInt(parts[0]);
                    pair[1] = Integer.parseInt(parts[1]);
                    pairs.add(pair);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
           
        }
    }

    

    public static void main(String[] args) {
        
        Graph graph = new Graph();
        ExtractInfo("file.txt");

        //To delete the repeated vertices
        ArrayList<int[]> aux_pairs = new ArrayList<>();
        int prev = 0;
        for (int[] i : pairs){
            if (i[0] != prev){
                aux_pairs.add(i);
                prev = i[0];
            }
            
        }
        
        //Adjacency List Representation
        for (int[] i : aux_pairs){
            Vertex v = new Vertex(i[0]);
            Vertex vertex = graph.fill_vertex(v, pairs);
            graph.addVertex(vertex);
        }
        System.out.println();
        System.out.println("Number of vertices: " + aux_pairs.size() + " Number of edges: " + pairs.size());
        graph.printAdjacencyList(graph);

        //Adjacency Matrix Representation
        System.out.println();
        graph.printMatrix(graph.adjacencyMatrix(pairs));
    }
    
}
