
public class Adiacenza<T> {

    private T nodo;
    private float peso;

    public Adiacenza(T n, float peso) {
        this.nodo = n;
        this.peso = peso;
    }

    public T getNodo() {
        return nodo;
    }

    public float getPeso() {
        return peso;
    }

    @Override
    public String toString() {
        return nodo + "(peso = " + peso + ")";
    }
}
