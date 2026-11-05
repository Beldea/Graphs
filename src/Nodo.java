import java.util.HashMap;

public class Nodo<T> {
    private T value;
    private HashMap<T, Float> adjacencies;

    public Nodo(T v) {
        this.value = v;
    }

    public T getValue() {
        return value;
    }

    @Override
    public String toString() {
        String res = "";
        res += T;
        return res;
    }

    @Override
    public boolean equals(Object key) {
        if (this == key) return true;
        if (!(key instanceof Nodo)){
            return false;
        }
        Nodo altro = (Nodo) key;
        return value.equals(altro.value);
    }

}