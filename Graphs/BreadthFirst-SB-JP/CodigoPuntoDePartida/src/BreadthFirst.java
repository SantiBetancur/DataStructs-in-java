import java.util.Queue;
import java.util.LinkedList;

public class BreadthFirst {
    public static final int WHITE = 0;
    public static final int GRAY  = 1;
    public static final int BLACK = 2;

    AdjacencyList al;

    Queue<BFNode> queue;        // Queue to store the "gray" nodes
    BFNode[] vertices;          // The result of the algorithm is stored in this DS

    public BreadthFirst(String fileName) {
        al = new AdjacencyList(fileName);
        vertices = new BFNode[al.V + 1];
    }

    public String toString() {
        String s = "";
        for (int i = 1; i <= al.V; i++) {
            if(vertices[i].distance != Integer.MAX_VALUE) {
            s += "Vertex: " + vertices[i].number + " "
                    + "distance: " + vertices[i].distance
                    + "\n";
            } else {
            s += "Vertex: " + vertices[i].number + " "
                    + "distance: " + "INF"
                    + "\n";

            }
        }
        return s;
    }
    
    public void computeDistances(int source) {
        // Initialize the vertices
        for (int i = 1; i <= al.V; i++) {
            vertices[i] = new BFNode(i);
            vertices[i].color = WHITE;
            vertices[i].distance = Integer.MAX_VALUE;
            vertices[i].pi = -1;
        }

        // Initialize the source vertex
        BFNode s = vertices[source];
        s.color = GRAY;
        s.distance = 0;
        //Nodo Previo
        s.pi = -1;

        // Initialize the queue to an empty list
        queue = new LinkedList<>();

        // Add the source vertex to the queue
        queue.add(s);

        // Visit the nodes
        // Your code goes here
        while (!queue.isEmpty()){

            BFNode u = queue.poll();

            //Access to the adjacency list of the Node u    
            for (Edge e : al.graph[u.number]){
                BFNode v = vertices[e.to];
                
                //If the Node wasn't visited
                if (v.color == WHITE){
                    v.color = GRAY;
                    v.distance = u.distance + 1;
                    v.pi = u.number;
                    queue.add(v);
                }

            u.color = BLACK;    

            }

        }
    }

    public static void main(String[] args) {
        BreadthFirst bf = new BreadthFirst("graph2.txt");
        System.out.println(bf.al);
        bf.computeDistances(1);
        System.out.println(bf);
    }

}