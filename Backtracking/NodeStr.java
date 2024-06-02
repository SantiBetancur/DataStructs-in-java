import java.util.ArrayList;

public class NodeStr {
    String data;
    NodeStr parent;
    ArrayList<NodeStr> children;
    
    /**
     * Constructor
     * @param data datum to store in the node
     * @param parent reference to the node's parent
     */
    public NodeStr(String data, NodeStr parent) {
        this.data = data;
        this.parent = parent;
        this.children = new ArrayList<>();
    }
}           
   