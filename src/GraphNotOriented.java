
public class GraphNotOriented<T> extends Graph<T> {

    @Override
    public void addEdge(T source, T dest, float weight) {
        addNode(source);
        addNode(dest);
        nodes.get(source).put(dest, new Adiacenza(dest, weight));
        nodes.get(dest).put(source, new Adiacenza(source, weight));
    }

    public GraphNotOriented<T> kruscal() {
        return null;
    }

    public GraphNotOriented<T> prim(T radix) {
        return null;
    }
}
