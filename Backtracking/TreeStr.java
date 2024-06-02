import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

/**
 * Class to implement a tree with any number of nodes
 * 
 * @author htrefftz
 */
public class TreeStr {
    NodeStr root;
    
    public TreeStr(NodeStr node) {
        this.root = node;
    }
    
    /**
     * Read the tree from a text file
     * 
     * Format:
     * Each line contains a path and a datum
     * The path and the datum are separated by a dot
     * The path is the sequence of siblings taken at each level
     * If the path is empty, the line describes the data to be stored 
     * at the root node.
     * 
     * @param fileName filename to read the tree from
     */
    public void readTree(String fileName) {
        try {
            Scanner in = new Scanner(new File(fileName));
            while(in.hasNextLine()) {
                String line = in.nextLine();
                if(!line.contains(".")) continue;
                String [] arr = line.split("[.]");
                String location = arr[0];
                String value = arr[1];
                Scanner locIn = new Scanner(location);
                Scanner valIn = new Scanner(value);
                ArrayList<Integer> locationAL = new ArrayList<>();
                String newValue = valIn.next();
                while(locIn.hasNextInt()) {
                    locationAL.add(locIn.nextInt());
                }
                if(locationAL.isEmpty()) {        // root
                    root = new NodeStr(newValue, null);
                } else {                            // other nodes
                    NodeStr n = root;
                    int childNumber;                // follow the path
                    for(int i = 0; i < locationAL.size() - 1; i++) {
                        childNumber = locationAL.get(i);
                        n = n.children.get(childNumber);
                    }
                    // Last number in the location array: position to add the new node
                    NodeStr newNode = new NodeStr(newValue, n);                   // node to add
                    childNumber = locationAL.get(locationAL.size() - 1);    // where to add it
                    n.children.add(childNumber, newNode);
                }
                //System.out.println(location);
                //System.out.println(value);
                locIn.close();
                valIn.close();
                
            }
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
    }
    
    /**
     * Helper function. It calls printTree(Node node, int level)
     * 
     */
    public void printTree() {
        printTree(root, "");
    }
    
    /**
     * Prints the tree, starting at node n.
     * The branch parameter is used to display how the tree extends from the current node.
     *
     * @param n       node to start printing the tree. Usually the root.
     * @param branch  string indicating how the tree extends from the current node.
     */
    public void printTree(NodeStr n, String branch) {

        System.out.println(branch + n.data);
        
        int childCount = n.children.size();
        for (int i = 0; i < childCount; i++) {
            String childBranch = branch + (i == childCount - 1 ? "└── " : "├── ");
            printTree(n.children.get(i), childBranch);
        }
    }
    
    
    /**
     * Print the path to the given node,
     * @param node node to print the path to
     */
    public void printPath(NodeStr node) {
        System.out.println("Lo encontré: ");
        List<String> list = new ArrayList<>();
        while(node != null) {
            list.add(node.data);
            node = node.parent;
        }
        Collections.reverse(list);
        for (String i: list){
            System.out.print(" -> "+i);
        }
        System.out.println("\n");
        
    }
    
    /**
     * Helper function
     * @param n datum to find in the tree
     * @return true if the value is found, else otherwise
     */
    public boolean find(String n) {
        return find_data(n, root, "");
    }
    
    /**
     * Find datum "n" within tree starting at node "node"
     * @param n datum to be found
     * @param node starting point of the tree 
     * @return 
     */
    public boolean find_data(String n, NodeStr node, String branch) {
        
        if(node == null){
            return false;
        }

        if(node.data.equals(n)){
            printPath(node);
            return true;
        }

        for (NodeStr child: node.children){
            
            branch = ("└── ") + child.data;
            
            find_data(n, child, branch);
        }
        System.out.println(branch);
        

        
        return false;
    }
}
