import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Dijkstra's algorithm implementation to find the shortest paths from a single source to all other nodes.
 */
public class Dijikstra {

    /**
     * Calculates the shortest paths from a source node to all other nodes using Dijkstra's algorithm.
     *
     * @param source The source node for the shortest path calculation.
     */
    public void calculate_shortest_path(Node source) {
        // Start node
        source.setDistance(0);

        // Initialize the set of visited and unvisited nodes
        HashSet<Node> settledNodes = new HashSet<>();
        PriorityQueue<Node> unsettledNodes = new PriorityQueue<>();
        unsettledNodes.add(source);

        // Traverse the graph visiting each node
        while (!unsettledNodes.isEmpty()) {
            Node currentNode = unsettledNodes.poll();

            currentNode.getAdjacentNodes().entrySet().stream()
                .filter(entry -> !settledNodes.contains(entry.getKey()))
                .forEach(entry -> {
                    evaluateDistanceAndPath(entry.getKey(), entry.getValue(), currentNode);
                    unsettledNodes.add(entry.getKey());
                });

            settledNodes.add(currentNode);
        }
    }

    /**
     * Evaluates the distance and shortest path for the adjacent node.
     *
     * @param adjacentNode The adjacent node being evaluated.
     * @param edgeWeight   The weight of the edge to the adjacent node.
     * @param sourceNode   The source node for the evaluation.
     */
    public void evaluateDistanceAndPath(Node adjacentNode, Integer edgeWeight, Node sourceNode) {
        Integer newDistance = sourceNode.getDistance() + edgeWeight;
        if (newDistance < adjacentNode.getDistance()) {
            adjacentNode.setDistance(newDistance);
            adjacentNode.setShortestPath(
                Stream.concat(sourceNode.getShortestPath().stream(), Stream.of(sourceNode)).toList()
            );
        }
    }

    //This method is to make tests

    /**
     * Prints the shortest paths and distances for the provided nodes.
     *
     * @param nodes The list of nodes to print their shortest paths and distances.
     */
    public void printPaths(List<Node> nodes) {
        nodes.forEach(node -> {
            String path = node.getShortestPath().stream()
                    .map(Node::getName)
                    .collect(Collectors.joining(" -> "));
            System.out.println((path.isBlank()
                    ? "%s : %s".formatted(node.getName(), node.getDistance())
                    : "%s -> %s : %s".formatted(path, node.getName(), node.getDistance()))
            );
        });
    }
}
