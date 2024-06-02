import java.util.Arrays;

/**
 * Bellman-Ford algorithm implementation to find the shortest path from a single source.
 */
public class BellmanFord {

    /**
     * Function to perform Bellman-Ford algorithm.
     *
     * @param edges An array of edges representing connections between nodes.
     * @param V     The number of vertices in the graph.
     * @param E     The number of edges in the graph.
     * @param src   The source vertex.
     */
    public void bellmanFord(Edge[] edges, int V, int E, int src) {
        int[] dist = new int[V];

        // Initialize distances from source to all other vertices as INFINITE
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;

        // Relax all edges |V| - 1 times, where |V| is the number of vertices
        for (int i = 1; i < V; ++i) {
            for (int j = 0; j < E; ++j) {
                int u = edges[j].src;
                int v = edges[j].dest;
                int weight = edges[j].weight;
                if (dist[u] != Integer.MAX_VALUE && dist[u] + weight < dist[v]) {
                    dist[v] = dist[u] + weight;
                }
            }
        }

        // Check for negative-weight cycles
        for (int j = 0; j < E; ++j) {
            int u = edges[j].src;
            int v = edges[j].dest;
            int weight = edges[j].weight;
            if (dist[u] != Integer.MAX_VALUE && dist[u] + weight < dist[v]) {
                System.out.println("Graph contains negative weight cycle. Bellman-Ford does not work.");
                return;
            }
        }
    }
}
