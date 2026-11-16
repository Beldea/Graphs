import java.util.ArrayList;
import java.util.HashMap;

public abstract class Graph<T> {

    protected HashMap<T, HashMap<T, Adiacenza<T>>> nodes;

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

        ArrayList<T> keys = new ArrayList<>(nodes.keySet());
        for (int i = 0; i < keys.size(); i++) {
            T u = keys.get(i);
            res += u + " - [ ";
            ArrayList<Adiacenza<T>> adj = new ArrayList<>(nodes.get(u).values());
            for (int j = 0; j < adj.size(); j++) {
                res += adj.get(j) + " ";
            }
            res += "]\n";
        }

        return res;
    }
}