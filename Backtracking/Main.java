public class Main {
    public static void main(String [] args) {
        Tree tree = new Tree(null);
        tree.readTree("treeInt.txt");
        tree.printTree();
        System.out.println("\n__________\n");
        tree.find(50);
        System.out.println("\n_______________________________\n");

        TreeStr treeString = new TreeStr(null);
        treeString.readTree("treeStr.txt");
        treeString.printTree();
        System.out.println("\n__________\n");
        treeString.find("ee");
        System.out.println("\n__________\n");
    }
}
