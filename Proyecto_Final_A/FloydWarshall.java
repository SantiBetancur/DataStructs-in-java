/**
 * Class implementing the Floyd-Warshall algorithm for finding all-pairs shortest paths in a weighted graph.
 */
public class FloydWarshall {

    /**
     * Default constructor for FloydWarshall class.
     */
    public FloydWarshall() {
        // Constructor
    }

    /**
     * Function to perform the Floyd-Warshall algorithm.
     *
     * @param graph The adjacency matrix representing the graph.
     * @param V     The number of vertices in the graph.
     */
    public void floydWarshall(int[][] graph, int V) {
        int[][] dist = new int[V][V];

        // Initialize the distance matrix with the graph values
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                dist[i][j] = graph[i][j];
            }
        }

        // Apply Floyd-Warshall algorithm
        for (int k = 0; k < V; k++) {
            for (int i = 0; i < V; i++) {
                for (int j = 0; j < V; j++) {
                    if (dist[i][k] != Integer.MAX_VALUE && dist[k][j] != Integer.MAX_VALUE &&
                            dist[i][k] + dist[k][j] < dist[i][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }
    }

    /**
     * Function to print the solution matrix.
     *
     * @param dist The matrix representing the shortest distances between vertices.
     */
    public void printSolution(int[][] dist) {
        int V = dist.length;
        System.out.println("The following matrix shows the shortest distances between every pair of vertices:");

        for (int i = 0; i < V; ++i) {
            for (int j = 0; j < V; ++j) {
                if (dist[i][j] == Integer.MAX_VALUE) {
                    System.out.print("INF ");
                } else {
                    System.out.print(dist[i][j] + " ");
                }
            }
            System.out.println();
        }
    }
}
