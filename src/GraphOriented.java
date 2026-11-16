import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class GraphOriented<T> extends Graph<T> {

    @Override
    public void addEdge(T source, T dest, float weight) {
        addNode(source);
        addNode(dest);
        nodes.get(source).put(dest, new Adiacenza<>(dest, weight));
    }

    // Distanze minime da source verso tutti i nodi (pesi negativi ammessi).
    // Nodi non raggiungibili: distance = infinito, prev = null.
    public HashMap<T, Distance<T>> bellman_ford(T source) {
        if (!nodes.containsKey(source)) {
            throw new IllegalArgumentException("Nodo sorgente inesistente: " + source);
        }

        ArrayList<T> keys = new ArrayList<>(nodes.keySet());

        HashMap<T, Distance<T>> dist = new HashMap<>();
        for (int i = 0; i < keys.size(); i++) {
            dist.put(keys.get(i), new Distance<>(null, Float.POSITIVE_INFINITY));
        }
        dist.get(source).setDistance(0);

        // |V| - 1 rilassamenti di tutti gli archi
        for (int i = 1; i < keys.size(); i++) {
            boolean modificato = false;
            for (int j = 0; j < keys.size(); j++) {
                T u = keys.get(j);
                if (dist.get(u).getDistance() == Float.POSITIVE_INFINITY) continue;
                ArrayList<Adiacenza<T>> adj = new ArrayList<>(nodes.get(u).values());
                for (int k = 0; k < adj.size(); k++) {
                    Adiacenza<T> a = adj.get(k);
                    float nd = dist.get(u).getDistance() + a.getPeso();
                    if (nd < dist.get(a.getNodo()).getDistance()) {
                        dist.get(a.getNodo()).setDistance(nd);
                        dist.get(a.getNodo()).setPrev(u);
                        modificato = true;
                    }
                }
            }
            if (!modificato) break;
        }

        // un ulteriore rilassamento possibile => ciclo negativo
        for (int j = 0; j < keys.size(); j++) {
            T u = keys.get(j);
            if (dist.get(u).getDistance() == Float.POSITIVE_INFINITY) continue;
            ArrayList<Adiacenza<T>> adj = new ArrayList<>(nodes.get(u).values());
            for (int k = 0; k < adj.size(); k++) {
                Adiacenza<T> a = adj.get(k);
                if (dist.get(u).getDistance() + a.getPeso() < dist.get(a.getNodo()).getDistance()) {
                    throw new IllegalStateException("Il grafo contiene un ciclo negativo");
                }
            }
        }

        return dist;
    }

    // Distanze minime da source verso tutti i nodi (solo pesi non negativi).
    public HashMap<T, Distance<T>> dijkstra(T source) {
        if (!nodes.containsKey(source)) {
            throw new IllegalArgumentException("Nodo sorgente inesistente: " + source);
        }

        ArrayList<T> keys = new ArrayList<>(nodes.keySet());

        HashMap<T, Distance<T>> dist = new HashMap<>();
        for (int i = 0; i < keys.size(); i++) {
            dist.put(keys.get(i), new Distance<>(null, Float.POSITIVE_INFINITY));
        }
        dist.get(source).setDistance(0);

        HashSet<T> visitati = new HashSet<>();

        while (visitati.size() < keys.size()) {
            // nodo non visitato con distanza minima
            T u = null;
            for (int i = 0; i < keys.size(); i++) {
                T n = keys.get(i);
                if (!visitati.contains(n)
                        && (u == null || dist.get(n).getDistance() < dist.get(u).getDistance())) {
                    u = n;
                }
            }

            // i nodi rimasti non sono raggiungibili
            if (dist.get(u).getDistance() == Float.POSITIVE_INFINITY) break;

            visitati.add(u);

            ArrayList<Adiacenza<T>> adj = new ArrayList<>(nodes.get(u).values());
            for (int k = 0; k < adj.size(); k++) {
                Adiacenza<T> a = adj.get(k);
                if (a.getPeso() < 0) {
                    throw new IllegalStateException("Dijkstra non ammette pesi negativi");
                }
                float nd = dist.get(u).getDistance() + a.getPeso();
                if (nd < dist.get(a.getNodo()).getDistance()) {
                    dist.get(a.getNodo()).setDistance(nd);
                    dist.get(a.getNodo()).setPrev(u);
                }
            }
        }

        return dist;
    }
}