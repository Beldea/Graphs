
import java.util.HashMap;

public class GraphOriented<T> extends Graph<T> {


    @Override
    public void addEdge(T source, T dest, float weight) {
        addNode(source);
        addNode(dest);
        nodes.get(source).put(dest, new Adiacenza( dest, weight));
    }

    public HashMap<T, Distance<T>> bellman_ford(T source) {
        System.out.println();
        return null;
    }

    public HashMap<T, Distance<T>> dijkstra(T source) {
        return null;
    }
}
