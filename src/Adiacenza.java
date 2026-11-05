public class Adiacenza {
    private Nodo nodo;
    private float peso;

    public Adiacenza(Nodo n, float peso) {
        this.nodo = n;
        this.peso = peso;
    }

    public Nodo getNodo() {
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