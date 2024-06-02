

public class DepthFirst {
    public static final int WHITE = 0;
    public static final int GRAY  = 1;
    public static final int BLACK = 2;

    AdjacencyList al;

    DFNode[] vertices;
    
    int time;


    public void DFS(AdjacencyList al) {
        this.al = al;
        vertices = new DFNode[al.V + 1];
        // Initialize the vertices
        for (int i = 1; i <= al.V; i++) {
            DFNode node = new DFNode(i);
            node.color = WHITE;
            node.pi = -1;
            vertices[i] = node;
        }
        this.time = 0;
        // Visit the trees
        for (int i = 1; i <= al.V; i++) {
            if (vertices[i].color == WHITE) {
                DFSVISIT(al, vertices[i]);
            }
        }

    }

    public void DFSVISIT(AdjacencyList al, DFNode u) {
        // Your code here
        //Traverse all Vertices    
        this.time += 1 ;
        u.d = time;
        u.color = GRAY;
        for (Edge e : al.graph[u.number]){
            DFNode v = vertices[e.to];
            if (v.color == WHITE){
                v.pi = u.number;
                DFSVISIT(al, v);
            }
        }
        this.time += 1;
        u.f =  time;
        u.color = BLACK;
        

    }

    // toString method
    public String toString() {
        String s = "";
        for (int i = 1; i <= al.V; i++) {
            s += vertices[i] + "\n";
        }
        return s;
    }    

    public static void main(String[] args) {
        DepthFirst bf = new DepthFirst();
        AdjacencyList al = new AdjacencyList("graph.txt");
        bf.DFS(al);
        //System.out.println(bf.al);
        System.out.println(bf);
    }


}