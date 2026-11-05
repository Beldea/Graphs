//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
    GraphOriented g = new GraphOriented();

    g.addEdge("A", "B", 20);
    g.addEdge("C","D", 30);
    g.addEdge("A","C",2);

    System.out.println(g);
    g.bellman_ford("A");
    }
}