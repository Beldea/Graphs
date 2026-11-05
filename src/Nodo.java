public class Nodo {
    private String nome;

    public Nodo(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }


    @Override
    public String toString() {
        return nome;
    }

    @Override
    public boolean equals(Object key) {
        if (this == key) return true;
        if (!(key instanceof Nodo)){
            return false;
        }

        Nodo altro = (Nodo) key;
        return nome.equals(altro.nome);
    }

    @Override
    public int hashCode() {
        return nome.hashCode();
    }
}