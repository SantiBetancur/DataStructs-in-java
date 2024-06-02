import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Main class that performs various operations on a dataset of city connections and distances.
 *
 * @author Juan Pablo Jimenez Quiroz
 * @author Santiago Betancur 
 */
public class Main {

    /**
     * Extracts information from a dataset file and organizes it into a HashMap.
     *
     * @param filename The name of the file containing the dataset.
     * @return A HashMap containing city information and connected cities with distances.
     */
    public static HashMap<String, HashSet<String[]>> extractInfo(String filename) {
        HashMap<String, HashSet<String[]>> cityInfo = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" -> |: ");
                if (parts.length == 3) {
                    String cityName = parts[0];
                    String connectedCity = parts[1];
                    int distance = Integer.parseInt(parts[2]);

                    cityInfo.putIfAbsent(cityName, new HashSet<>());

                    String[] cityDistancePair = {connectedCity, String.valueOf(distance)};
                    cityInfo.get(cityName).add(cityDistancePair);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return cityInfo;
    }

    /**
     * Initializes the Dijkstra algorithm to find the shortest path in a given dataset.
     *
     * @param dataset The dataset containing city information and connections.
     */
    private static void initializeDijkstra(HashMap<String, HashSet<String[]>> dataset) {
        ArrayList<Node> graph = new ArrayList<>();
        Dijikstra d_algorithm = new Dijikstra();

        for (String city : dataset.keySet()) {
            Node new_node = new Node(city);

            for (String[] connections : dataset.get(city)) {
                Node adjacent_node = new Node(connections[0]);
                adjacent_node.setDistance(Integer.valueOf(connections[1]));
                new_node.add_adjacent_node(adjacent_node, Integer.valueOf(connections[1]));
            }

            graph.add(new_node);
        }

        long start_time = System.currentTimeMillis();
        d_algorithm.calculate_shortest_path(graph.get(0));
        long end_time = System.currentTimeMillis();
        System.out.println("Dijikstra time ms: " + (end_time - start_time));
    }

    /**
     * Creates an adjacency matrix from the provided city information.
     *
     * @param cityInfo The city information dataset.
     * @return An adjacency matrix representing the connections and distances between cities.
     */
    
    private static int[][] createAdjacencyMatrix(HashMap<String, HashSet<String[]>> cityInfo) {
        int numCities = cityInfo.size();
        int[][] adjacencyMatrix = new int[numCities][numCities];

        // Initialize the matrix with "infinity" values
        for (int i = 0; i < numCities; i++) {
            for (int j = 0; j < numCities; j++) {
                adjacencyMatrix[i][j] = Integer.MAX_VALUE;
            }
        }

        // Fill in the matrix with actual distances
        HashMap<String, Integer> cityIndices = new HashMap<>();
        int index = 0;

        for (String city : cityInfo.keySet()) {
            cityIndices.put(city, index);
            index++;
        }

        for (String city : cityInfo.keySet()) {
            int fromIndex = cityIndices.get(city);

            HashSet<String[]> connections = cityInfo.get(city);
            for (String[] connection : connections) {
                String connectedCity = connection[0];
            
                int distance = Integer.parseInt(connection[1]);
                if (cityIndices.get(connectedCity) != null){
                    int toIndex = cityIndices.get(connectedCity);
                    adjacencyMatrix[fromIndex][toIndex] = distance;
                }
            }
        }

        return adjacencyMatrix;
    }

    /**
     * Initializes the Floyd-Warshall algorithm to find all shortest paths in a given dataset.
     *
     * @param dataset The dataset containing city information and connections.
     */
    private static void initializeFloydWarshall(HashMap<String, HashSet<String[]>> dataset) {
        int[][] graph = createAdjacencyMatrix(dataset);
        int nodes = graph.length;
        FloydWarshall f_algorithm = new FloydWarshall();
       
        long start_time = System.currentTimeMillis();
        f_algorithm.floydWarshall(graph, nodes);
        long end_time = System.currentTimeMillis();
        System.out.println("FloydWarshall time ms: " + (end_time - start_time));
    }

    /**
     * Extracts edges from the dataset to represent connections between cities.
     *
     * @param dataset The dataset containing city information and connections.
     * @return An array of edges representing connections between cities.
     */
    private static Edge[] extractEdges(HashMap<String, HashSet<String[]>> dataset) {
        int numEdges = countEdges(dataset);
        Edge[] edges = new Edge[numEdges];
        int edgeIndex = 0;
        int scr = 0;
        int dest = 0;

        for (String city : dataset.keySet()) {
            scr++;
            HashSet<String[]> connections = dataset.get(city);
            for (String[] connection : connections) {
                dest++;
                Edge edge = new Edge();
                edge.src = scr;
                edge.dest = dest;
                edge.weight = Integer.parseInt(connection[1]);
                edges[edgeIndex++] = edge;
            }
        }

        return edges;
    }

    /**
     * Counts the total number of edges in the city information dataset.
     *
     * @param cityInfo The city information dataset.
     * @return The total number of edges in the dataset.
     */
    private static int countEdges(HashMap<String, HashSet<String[]>> cityInfo) {
        int count = 0;
        for (HashSet<String[]> connections : cityInfo.values()) {
            count += connections.size();
        }
        return count;
    }

    /**
     * Calculates the number of vertices from the given array of edges.
     *
     * @param edges An array of edges representing connections between cities.
     * @return The total number of vertices.
     */
    private static int calculateNumVertices(Edge[] edges) {
        ArrayList<Integer> vertices = new ArrayList<>();
        for (Edge edge : edges) {
            vertices.add(edge.src);
            vertices.add(edge.dest);
        }
        return vertices.size();
    }

    /**
     * Initializes the Bellman-Ford algorithm to find the shortest path in a given dataset.
     *
     * @param dataset The dataset containing city information and connections.
     */
    private static void initializeBellmanFord(HashMap<String, HashSet<String[]>> dataset) {
        Edge[] edges = extractEdges(dataset);
        int V = calculateNumVertices(edges);
        int E = countEdges(dataset);    
        BellmanFord b_algorithm = new BellmanFord();
        long start_time = System.currentTimeMillis(); 
        b_algorithm.bellmanFord(edges, V, E, 0);
        long end_time = System.currentTimeMillis();
        System.out.println("BellmanFord time ms: " + (end_time - start_time));
    }

    /**
     * Main method to demonstrate
     * Main method to demonstrate the usage of the implemented algorithms on a dataset.
     *
     * @param arg Command line arguments (not used in this implementation).
     */
    public static void main(String[] arg) {
        // Example to insert data
        HashMap<String, HashSet<String[]>> dataset = extractInfo("dataSet.txt");
        initializeDijkstra(dataset);
        initializeFloydWarshall(dataset);
        initializeBellmanFord(dataset);
    }
}
