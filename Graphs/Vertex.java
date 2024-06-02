import java.util.ArrayList;
import java.util.List;

public class Vertex {
    int id;
    List<Vertex> neighbors;

    public int get_id (){
        return this.id;
    }
    public List<Vertex> get_neigList(){
        return this.neighbors;
    }

    public Vertex(int id){
        this.id = id;
        this.neighbors = new ArrayList<>();
    }

    public void addNeighbor(Vertex n){
        this.neighbors.add(n);
    }

   
}
