import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        GraphOriented<String> g = new GraphOriented<>();

        g.addEdge("A", "B", 20);
        g.addEdge("C", "D", 30);
        g.addEdge("A", "C", 2);

        System.out.println(g);

        HashMap<String, Distance<String>> bf = g.bellman_ford("A");
        System.out.println("Bellman-Ford da A: " + bf);

        HashMap<String, Distance<String>> dj = g.dijkstra("A");
        System.out.println("Dijkstra da A: " + dj);

        GraphNotOriented<String> h = new GraphNotOriented<>();
        h.addEdge("A", "B", 4);
        h.addEdge("A", "C", 1);
        h.addEdge("B", "C", 2);
        h.addEdge("B", "D", 5);
        h.addEdge("C", "D", 8);

        System.out.println("Kruskal:" + h.kruskal());
        System.out.println("Prim da A:" + h.prim("A"));
    }
}