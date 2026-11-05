
import java.util.HashMap;
import java.util.Map;

public abstract class Graph<T> {

    protected HashMap<T, HashMap<T, Adiacenza>> nodes;

    public Graph() {
        nodes = new HashMap<>();
    }

    public void addNode(T value) {
        if (!nodes.containsKey(value)) {
            nodes.put(value, new HashMap<>());
        }
    }

    public abstract void addEdge(T source, T dest, float weight);

    @Override
    public String toString() {
        String res = "";

        for (Map.Entry<T, HashMap<T, Adiacenza>> entry
                : nodes.entrySet()) {

            res += entry.getKey() + " - [ ";

            for (Adiacenza a : entry.getValue().values()) {
                res += a + " ";
            }

            res += "]\n";
        }

        return res;
    }
}
