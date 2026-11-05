import java.util.HashMap;
public class Graph<T> {
    protected HashMap<T, Float> nodes;

    public void addNode(T value) {
        if (!nodes.containsKey(value)) {
            nodes.put(value, value.);
            System.out.println("Aggiunto nodo: " + value);
        }
    }

    public void addEdge(T source, T dest, float weight) {
        addNode(source);
        addNode(dest);
        nodes.get(source).put(dest, new Adiacenza(dest, weight));
        System.out.println("Aggiunto arco: " + source + " - " + dest + " (peso " + weight + ")");
    }
}
