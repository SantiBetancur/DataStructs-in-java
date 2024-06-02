import java.util.ArrayList;

public class Node {
    int data;
    Node parent;
    ArrayList<Node> children;
    
    /**
     * Constructor
     * @param data datum to store in the node
     * @param parent reference to the node's parent
     */
    public Node(int data, Node parent) {
        this.data = data;
        this.parent = parent;
        this.children = new ArrayList<>();
    }
    
}
