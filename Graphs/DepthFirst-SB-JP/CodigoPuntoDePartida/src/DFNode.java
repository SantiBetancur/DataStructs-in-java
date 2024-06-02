public class DFNode {
    public int number;
    public int color;       // WHITE, GRAY, BLACK
    public int pi;      // predecessor
    public int d;       // discovery time
    public int f;       // finish time
    
    public DFNode(int number) {
        this.number = number;
    }

    // toString method
    public String toString() {
        String s = "";
        s += "Vertex: " + number + " ";
        s += "color: " + color + " ";
        s += "pi: " + pi + " ";
        s += "d: " + d + " ";
        s += "f: " + f + " ";
        return s;
    }
    
}