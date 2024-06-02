import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.List;

/**
 * Node class representing a node in a graph.
 */
public class Node implements Comparable<Node> {

    private final String name;
    private Integer distance = Integer.MAX_VALUE;
    private Map<Node, Integer> adjacentNodes = new HashMap<>();
    private List<Node> shortestPath = new LinkedList<>();

    /**
     * Constructor for Node class.
     *
     * @param n The name of the node.
     */
    public Node(String n) {
        this.name = n;
    }

    /**
     * Getter method for the node's name.
     *
     * @return The name of the node.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Getter method for the distance of the node.
     *
     * @return The distance of the node.
     */
    public Integer getDistance() {
        return this.distance;
    }

    /**
     * Setter method to set the distance of the node.
     *
     * @param dist The distance to set.
     */
    public void setDistance(Integer dist) {
        this.distance = dist;
    }

    /**
     * Getter method for the adjacent nodes of the current node.
     *
     * @return Map of adjacent nodes and their weights.
     */
    public Map<Node, Integer> getAdjacentNodes() {
        return this.adjacentNodes;
    }

    /**
     * Method to add an adjacent node with its corresponding weight.
     *
     * @param node   The adjacent node to add.
     * @param weight The weight of the edge to the adjacent node.
     */
    public void add_adjacent_node(Node node, int weight) {
        adjacentNodes.put(node, weight);
    }

    /**
     * Getter method for the shortest path to the node.
     *
     * @return The list representing the shortest path.
     */
    public List<Node> getShortestPath() {
        return this.shortestPath;
    }

    /**
     * Setter method to set the shortest path to the node.
     *
     * @param shortestPath The list representing the shortest path.
     */
    public void setShortestPath(List<Node> shortestPath) {
        this.shortestPath = shortestPath;
    }

    /**
     * Comparison method to compare nodes based on their distance.
     *
     * @param node The node to compare distances with.
     * @return Integer result of the comparison.
     */
    @Override
    public int compareTo(Node node) {
        return Integer.compare(this.distance, node.getDistance());
    }
}
