import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;

public class GraphNotOriented<T> extends Graph<T> {

    // arco di supporto per kruskal
    private static class Edge<T> implements Comparable<Edge<T>> {
        T source;
        T dest;
        float weight;

        Edge(T source, T dest, float weight) {
            this.source = source;
            this.dest = dest;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge<T> altro) {
            return Float.compare(weight, altro.weight);
        }
    }

    @Override
    public void addEdge(T source, T dest, float weight) {
        addNode(source);
        addNode(dest);
        nodes.get(source).put(dest, new Adiacenza<>(dest, weight));
        nodes.get(dest).put(source, new Adiacenza<>(source, weight));
    }

    // union-find: rappresentante dell'insieme di n
    private T find(HashMap<T, T> parent, T n) {
        while (!parent.get(n).equals(n)) {
            parent.put(n, parent.get(parent.get(n))); 
            n = parent.get(n);
        }
        return n;
    }

    // Albero di copertura minimo (foresta se il grafo non e' connesso)
    public GraphNotOriented<T> kruskal() {
        ArrayList<T> keys = new ArrayList<>(nodes.keySet());

        // lista degli archi, ognuno una sola volta
        ArrayList<Edge<T>> edges = new ArrayList<>();
        HashSet<T> elaborati = new HashSet<>();
        for (int i = 0; i < keys.size(); i++) {
            T u = keys.get(i);
            ArrayList<Adiacenza<T>> adj = new ArrayList<>(nodes.get(u).values());
            for (int j = 0; j < adj.size(); j++) {
                Adiacenza<T> a = adj.get(j);
                if (!elaborati.contains(a.getNodo())) {
                    edges.add(new Edge<>(u, a.getNodo(), a.getPeso()));
                }
            }
            elaborati.add(u);
        }
        Collections.sort(edges);

        HashMap<T, T> parent = new HashMap<>();
        GraphNotOriented<T> mst = new GraphNotOriented<>();
        for (int i = 0; i < keys.size(); i++) {
            parent.put(keys.get(i), keys.get(i));
            mst.addNode(keys.get(i));
        }

        for (int i = 0; i < edges.size(); i++) {
            Edge<T> e = edges.get(i);
            T ru = find(parent, e.source);
            T rv = find(parent, e.dest);
            if (!ru.equals(rv)) {
                parent.put(ru, rv);
                mst.addEdge(e.source, e.dest, e.weight);
            }
        }

        return mst;
    }

    // Albero di copertura minimo della componente connessa che contiene radix
    public GraphNotOriented<T> prim(T radix) {
        if (!nodes.containsKey(radix)) {
            throw new IllegalArgumentException("Nodo radice inesistente: " + radix);
        }

        GraphNotOriented<T> mst = new GraphNotOriented<>();
        HashSet<T> inseriti = new HashSet<>();
        mst.addNode(radix);
        inseriti.add(radix);

        while (true) {
            // arco di peso minimo che esce dall'albero verso un nodo non ancora inserito
            T from = null;
            Adiacenza<T> migliore = null;
            ArrayList<T> ins = new ArrayList<>(inseriti);
            for (int i = 0; i < ins.size(); i++) {
                T u = ins.get(i);
                ArrayList<Adiacenza<T>> adj = new ArrayList<>(nodes.get(u).values());
                for (int j = 0; j < adj.size(); j++) {
                    Adiacenza<T> a = adj.get(j);
                    if (!inseriti.contains(a.getNodo())
                            && (migliore == null || a.getPeso() < migliore.getPeso())) {
                        from = u;
                        migliore = a;
                    }
                }
            }

            if (migliore == null) break;

            mst.addEdge(from, migliore.getNodo(), migliore.getPeso());
            inseriti.add(migliore.getNodo());
        }

        return mst;
    }
}